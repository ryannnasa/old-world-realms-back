package com.example.restservice.controller;

import com.example.restservice.model.Player;
import com.example.restservice.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {
    @GetMapping("/player")
    public List<Player> getPlayers() {
        try {
            return PlayerRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of(); // Plus moderne que Collections.emptyList()
        }
    }

    @GetMapping("/player/{id}")
    public Player getPlayer(@PathVariable int id) {
        try {
            return PlayerRepository.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found with the Id : " + id);
        }
    }

    @PostMapping("/player")
    public @ResponseBody Map<String, Object> createPlayer(@RequestBody Player player) {
        try {
            int generatedId = PlayerRepository.create(player);
            if (generatedId > 0) {
                return Map.of(
                    "message", "Player created successfully.",
                    "idPlayer", generatedId
                );
            } else {
                return Map.of("message", "Failed to create player.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Map.of("message", "Error creating player.");
        }
    }

    @PutMapping("/player/{id}")
    public @ResponseBody Map<String, String> updatePlayer(@PathVariable int id, @RequestBody Player player) {
        try {
            int rows = PlayerRepository.update(id, player);
            return Map.of("message",
                rows > 0 ? "Player updated successfully." : "Player not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            return Map.of("message", "Error updating player.");
        }
    }

    @DeleteMapping("/player/{id}")
    public @ResponseBody Map<String, String> deletePlayer(@PathVariable int id) {
        try {
            int rows = PlayerRepository.delete(id);
            return Map.of("message",
                rows > 0 ? "Player deleted successfully." : "Player not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            return Map.of("message", "Error deleting player.");
        }
    }
}

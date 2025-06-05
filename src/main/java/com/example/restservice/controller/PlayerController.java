package com.example.restservice.controller;

import com.example.restservice.model.Player;
import com.example.restservice.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
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
            return Collections.emptyList();
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
        Map<String, Object> response = new HashMap<>();
        try {
            int generatedId = PlayerRepository.create(player); // on suppose que cette méthode renvoie l'id du joueur
            if (generatedId > 0) {
                response.put("message", "Player created successfully.");
                response.put("idPlayer", generatedId);
            } else {
                response.put("message", "Failed to create player.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.put("message", "Error creating player.");
        }
        return response;
    }


    @PutMapping("/player/{id}")
    public @ResponseBody Map<String, String> updatePlayer(@PathVariable int id, @RequestBody Player player) {
        try {
            int rows = PlayerRepository.update(id, player);
            return Collections.singletonMap("message",
                    rows > 0 ? "Player updated successfully." : "Player not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.singletonMap("message", "Error updating player.");
        }
    }

    @DeleteMapping("/player/{id}")
    public @ResponseBody Map<String, String> deletePlayer(@PathVariable int id) {
        try {
            int rows = PlayerRepository.delete(id);
            return Collections.singletonMap("message",
                    rows > 0 ? "Player deleted successfully." : "Player not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.singletonMap("message", "Error deleting player.");
        }
    }
}

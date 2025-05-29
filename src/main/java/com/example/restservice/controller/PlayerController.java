package com.example.restservice.controller;

import com.example.restservice.model.Player;
import com.example.restservice.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class PlayerController {
    @CrossOrigin(origins = "*")
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
    public String createPlayer(@RequestBody Player player) {
        try {
            int rows = PlayerRepository.create(player);
            return rows > 0 ? "Player created successfully." : "Failed to create player.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error creating player.";
        }
    }

    @PutMapping("/player/{id}")
    public String updatePlayer(@PathVariable int id, @RequestBody Player player) {
        try {
            int rows = PlayerRepository.update(id, player);
            return rows > 0 ? "Player updated successfully." : "Player not found.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating player.";
        }
    }

    @DeleteMapping("/player/{id}")
    public String deletePlayer(@PathVariable int id) {
        try {
            int rows = PlayerRepository.delete(id);
            return rows > 0 ? "Player deleted successfully." : "Player not found.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error deleting player.";
        }
    }
}

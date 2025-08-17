package com.example.restservice.controller;

import com.example.restservice.model.Player;
import com.example.restservice.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @InjectMocks
    private PlayerController playerController;

    @Test
    void testGetPlayersSuccess() throws SQLException {
        Player player1 = new Player();
        player1.setIdPlayer(1);
        player1.setPlayerName("Player1");

        Player player2 = new Player();
        player2.setIdPlayer(2);
        player2.setPlayerName("Player2");

        List<Player> expectedPlayers = Arrays.asList(player1, player2);

        try (MockedStatic<PlayerRepository> mockedRepo = mockStatic(PlayerRepository.class)) {
            mockedRepo.when(PlayerRepository::findAll).thenReturn(expectedPlayers);

            List<Player> result = playerController.getPlayers();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Player1", result.get(0).getPlayerName());
            assertEquals("Player2", result.get(1).getPlayerName());
        }
    }

    @Test
    void testGetPlayersWithSQLException() throws SQLException {
        try (MockedStatic<PlayerRepository> mockedRepo = mockStatic(PlayerRepository.class)) {
            mockedRepo.when(PlayerRepository::findAll).thenThrow(new SQLException("Database error"));

            List<Player> result = playerController.getPlayers();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Test
    void testGetPlayerByIdSuccess() throws SQLException {
        Player expectedPlayer = new Player();
        expectedPlayer.setIdPlayer(1);
        expectedPlayer.setPlayerName("TestPlayer");

        try (MockedStatic<PlayerRepository> mockedRepo = mockStatic(PlayerRepository.class)) {
            mockedRepo.when(() -> PlayerRepository.findById(1)).thenReturn(expectedPlayer);

            Player result = playerController.getPlayer(1);

            assertNotNull(result);
            assertEquals(1, result.getIdPlayer());
            assertEquals("TestPlayer", result.getPlayerName());
        }
    }

    @Test
    void testGetPlayerByIdWithSQLException() throws SQLException {
        try (MockedStatic<PlayerRepository> mockedRepo = mockStatic(PlayerRepository.class)) {
            mockedRepo.when(() -> PlayerRepository.findById(anyInt())).thenThrow(new SQLException("Database error"));

            ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
                playerController.getPlayer(1);
            });

            assertTrue(exception.getMessage().contains("Player not found with the Id : 1"));
        }
    }

    @Test
    void testCreatePlayerSuccess() throws SQLException {
        Player newPlayer = new Player();
        newPlayer.setPlayerName("NewPlayer");

        try (MockedStatic<PlayerRepository> mockedRepo = mockStatic(PlayerRepository.class)) {
            mockedRepo.when(() -> PlayerRepository.create(any(Player.class))).thenReturn(5);

            Map<String, Object> result = playerController.createPlayer(newPlayer);

            assertNotNull(result);
            assertEquals("Player created successfully.", result.get("message"));
            assertEquals(5, result.get("idPlayer"));
        }
    }

    @Test
    void testCreatePlayerFailure() throws SQLException {
        Player newPlayer = new Player();
        newPlayer.setPlayerName("NewPlayer");

        try (MockedStatic<PlayerRepository> mockedRepo = mockStatic(PlayerRepository.class)) {
            mockedRepo.when(() -> PlayerRepository.create(any(Player.class))).thenReturn(0);

            Map<String, Object> result = playerController.createPlayer(newPlayer);

            assertNotNull(result);
            assertEquals("Failed to create player.", result.get("message"));
        }
    }

    @Test
    void testCreatePlayerWithSQLException() throws SQLException {
        Player newPlayer = new Player();
        newPlayer.setPlayerName("NewPlayer");

        try (MockedStatic<PlayerRepository> mockedRepo = mockStatic(PlayerRepository.class)) {
            mockedRepo.when(() -> PlayerRepository.create(any(Player.class))).thenThrow(new SQLException("Database error"));

            Map<String, Object> result = playerController.createPlayer(newPlayer);

            assertNotNull(result);
            assertEquals("Error creating player.", result.get("message"));
        }
    }
}

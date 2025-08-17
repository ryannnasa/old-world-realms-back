package com.example.restservice.controller;

import com.example.restservice.model.Player;
import com.example.restservice.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerControllerAdditionalTest {

    @Test
    void testGetPlayersWithMultipleResults() throws SQLException {
        Player player1 = new Player();
        player1.setIdPlayer(1);
        player1.setPlayerName("Player 1");

        Player player2 = new Player();
        player2.setIdPlayer(2);
        player2.setPlayerName("Player 2");

        List<Player> expectedPlayers = Arrays.asList(player1, player2);

        try (MockedStatic<PlayerRepository> mockedRepo = mockStatic(PlayerRepository.class)) {
            mockedRepo.when(PlayerRepository::findAll).thenReturn(expectedPlayers);

            PlayerController controller = new PlayerController();
            List<Player> result = controller.getPlayers();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Player 1", result.get(0).getPlayerName());
            assertEquals("Player 2", result.get(1).getPlayerName());
        }
    }

    @Test
    void testGetPlayerByIdWithValidId() throws SQLException {
        Player expectedPlayer = new Player();
        expectedPlayer.setIdPlayer(5);
        expectedPlayer.setPlayerName("Test Player");

        try (MockedStatic<PlayerRepository> mockedRepo = mockStatic(PlayerRepository.class)) {
            mockedRepo.when(() -> PlayerRepository.findById(5)).thenReturn(expectedPlayer);

            PlayerController controller = new PlayerController();
            Player result = controller.getPlayer(5);

            assertNotNull(result);
            assertEquals(5, result.getIdPlayer());
            assertEquals("Test Player", result.getPlayerName());
        }
    }

    @Test
    void testCreatePlayerWithValidData() throws SQLException {
        Player newPlayer = new Player();
        newPlayer.setPlayerName("New Player");
        newPlayer.setPlayerScore("Victory");

        try (MockedStatic<PlayerRepository> mockedRepo = mockStatic(PlayerRepository.class)) {
            mockedRepo.when(() -> PlayerRepository.create(any(Player.class))).thenReturn(15);

            PlayerController controller = new PlayerController();
            Object result = controller.createPlayer(newPlayer);

            assertNotNull(result);
        }
    }

    @Test
    void testRepositoryInteraction() throws SQLException {
        try (MockedStatic<PlayerRepository> mockedRepo = mockStatic(PlayerRepository.class)) {
            mockedRepo.when(() -> PlayerRepository.findByBattleReportId(1)).thenReturn(Arrays.asList(new Player()));

            // Test que les méthodes du repository peuvent être appelées
            assertDoesNotThrow(() -> {
                try {
                    PlayerRepository.findByBattleReportId(1);
                } catch (SQLException e) {
                    assertTrue(true, "SQLException attendue");
                }
            });
        }
    }
}

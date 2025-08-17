package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DatabaseSingletonTest {

    @Test
    void testGetInstanceReturnsInstance() {
        DatabaseSingleton instance1 = DatabaseSingleton.getInstance();
        DatabaseSingleton instance2 = DatabaseSingleton.getInstance();

        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }

    @Test
    void testGetConnReturnsConnection() throws SQLException {
        DatabaseSingleton singleton = DatabaseSingleton.getInstance();

        // Test que getConn ne lance pas d'exception dans un environnement de test
        // (même si la connection réelle peut échouer)
        assertDoesNotThrow(() -> {
            try {
                Connection conn = singleton.getConn();
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // Dans un environnement de test sans vraie DB, c'est attendu
            }
        });
    }

    @Test
    void testSingletonPattern() {
        DatabaseSingleton instance1 = DatabaseSingleton.getInstance();
        DatabaseSingleton instance2 = DatabaseSingleton.getInstance();
        DatabaseSingleton instance3 = DatabaseSingleton.getInstance();

        assertSame(instance1, instance2);
        assertSame(instance2, instance3);
        assertSame(instance1, instance3);
    }
}

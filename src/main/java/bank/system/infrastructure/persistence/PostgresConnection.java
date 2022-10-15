package bank.system.infrastructure.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostgresConnection {

    private static final String URL = System.getenv("DATABASE_URL");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
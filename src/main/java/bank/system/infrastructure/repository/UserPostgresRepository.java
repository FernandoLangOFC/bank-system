package bank.system.infrastructure.repository;

import bank.system.domain.Identifier;
import bank.system.domain.common.Status;
import bank.system.domain.user.User;
import bank.system.infrastructure.persistence.query.Query;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.String.format;
import static bank.system.domain.common.Status.Type.ERROR;
import static bank.system.domain.common.Status.Type.SUCCESS;


@RequiredArgsConstructor
public class UserPostgresRepository implements UserRepository {

    private final Connection connection;

    private UserPostgresRepository() {
        this(null);
    }

    @Override
    public Status<?> createUser(User user) {
        final var createUserSql = Query.CREATE_USER_QUERY;

        try (final var preparedStatement = connection.prepareStatement(createUserSql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getDocumentNumber());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getFullName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on insert user: %s", e.getMessage()));
        }

        return new Status<>(SUCCESS.name(), format("User %s created successfully", user.getUsername()));
    }

    @Override
    public Status<?> updateUser(User user) {
        final var createUserSql = Query.UPDATE_USER_QUERY;

        try (final var preparedStatement = connection.prepareStatement(createUserSql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getFullName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on insert user: %s", e.getMessage()));
        }

        return new Status<>(SUCCESS.name(), format("User %s updated successfully", user.getUsername()));
    }

    @Override
    public Status<?> retrieveUser(Identifier<?> identifier) {
        
        return null;
    }

    @Override
    public Status<?> deleteUser(Identifier<?> identifier) {
        return null;
    }
}
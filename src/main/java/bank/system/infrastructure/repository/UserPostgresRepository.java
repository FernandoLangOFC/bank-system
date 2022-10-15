package bank.system.infrastructure.repository;

import bank.system.domain.user.User;
import bank.system.domain.user.UserAuth;
import bank.system.domain.user.UserIdentifier;
import bank.system.infrastructure.common.Status;
import bank.system.infrastructure.persistence.query.Query;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.Objects.isNull;

import static bank.system.infrastructure.common.Status.Type.*;


@RequiredArgsConstructor
public class UserPostgresRepository implements UserRepository {

    private final Connection connection;

    private UserPostgresRepository() {
        this(null);
    }

    @Override
    public Status<User> create(User entity) {
        final var createUserSql = Query.CREATE_USER_QUERY;

        try (final var preparedStatement = connection.prepareStatement(createUserSql.getQuery(), Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getDocumentNumber());
            preparedStatement.setString(5, entity.getPhone());
            preparedStatement.setString(6, entity.getFullName());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating entity failed, no rows affected.");
            }

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                entity.setId(resultSet.getObject("id", UUID.class));
                entity.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                entity.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
            }

            return new Status<>(SUCCESS.name(), entity);

        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on insert entity: %s", e.getMessage()));
        }
    }

    @Override
    public Status<User> update(User entity) {
        final var updateUserSql = Query.UPDATE_USER_QUERY;

        try (final var preparedStatement = connection.prepareStatement(updateUserSql.getQuery())) {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPhone());
            preparedStatement.setString(4, entity.getFullName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on update user: %s", e.getMessage()));
        }

        return new Status<>(SUCCESS.name(), format("User %s updated successfully", entity.getUsername()));
    }

    @Override
    public Status<User> findByID(UUID id) {
        final var retrieveUserSql = Query.RETRIEVE_USER_BY_ID_QUERY;

        try (final var preparedStatement = connection.prepareStatement(retrieveUserSql.getQuery())) {
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = User.create(resultSet.getString("username"));
                user.setId(id);
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setFullName(resultSet.getString("full_name"));
                user.setDocumentNumber(resultSet.getString("document_number"));
            }

            if (isNull(user))
                return new Status<>(Status.Type.ERROR.name(), "User not found");

            return new Status<>(SUCCESS.name(), user);

        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on retrieve user: %s", e.getMessage()));
        }
    }

    @Override
    public Status<User> delete(UUID id) {
        return null;
    }

    @Override
    public Status<?> findUserAuthPassword(String authType, String search) {
        Query query = Query.getByFilter(authType);

        try (final var preparedStatement = connection.prepareStatement(query.getQuery())) {
            preparedStatement.setObject(1, search);

            ResultSet resultSet = preparedStatement.executeQuery();
            UserAuth<UUID> userAuth = null;

            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                userAuth = new UserAuth<>(id, resultSet.getString("password"));
            }

            if (isNull(userAuth))
                return new Status<>(ERROR.name(), "User not found");

            return new Status<>(SUCCESS.name(), userAuth);

        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on retrieve user: %s", e.getMessage()));
        }
    }
}
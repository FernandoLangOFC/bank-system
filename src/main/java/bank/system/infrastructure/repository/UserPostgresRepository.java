package bank.system.infrastructure.repository;

import bank.system.domain.user.User;
import bank.system.domain.user.UserAuthRequest;
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

        try (final var preparedStatement = connection.prepareStatement(createUserSql.getSql(), Statement.RETURN_GENERATED_KEYS)) {

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

            return new Status<>(SUCCESS.name(), "Created user successfully", entity);

        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on insert entity: %s", e.getMessage()));
        }
    }

    @Override
    public Status<User> update(User entity) {
        final var updateUserSql = Query.UPDATE_USER_QUERY;

        try (final var preparedStatement = connection.prepareStatement(updateUserSql.getSql())) {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPhone());
            preparedStatement.setString(4, entity.getFullName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on update user: %s", e.getMessage()));
        }

        return new Status<>(SUCCESS.name(), format("User %s updated successfully", entity.getUsername()), entity);
    }

    @Override
    public Status<User> findByID(UUID id) {
        final var retrieveUserSql = Query.RETRIEVE_USER_BY_ID_QUERY;

        try (final var preparedStatement = connection.prepareStatement(retrieveUserSql.getSql())) {
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = User.create();

                user.setId(id);
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setUsername(resultSet.getString("username"));
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
        final var updateUserSql = Query.DELETE_USER_QUERY;

        try (final var preparedStatement = connection.prepareStatement(updateUserSql.getSql())) {
            preparedStatement.setObject(1, id);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating entity failed, no rows affected.");
            }

        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on delete user: %s", e.getMessage()));
        }

        return new Status<>(SUCCESS.name(), format("User with ID %s deleted successfully", id.toString()));
    }

    @Override
    public Status<UserAuthRequest<UUID>> findUserAuthPassword(UserAuthRequest<UUID> userAuthRequest) {
        Query query = Query.getByFilter(userAuthRequest.getAuthType());

        try (final var preparedStatement = connection.prepareStatement(query.getSql())) {
            preparedStatement.setObject(1, userAuthRequest.getLogin());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                userAuthRequest.setId(id);
            }

            return new Status<>(SUCCESS.name(), userAuthRequest);

        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on retrieve user: %s", e.getMessage()));
        }
    }
}
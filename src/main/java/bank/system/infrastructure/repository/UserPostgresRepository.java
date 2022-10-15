package bank.system.infrastructure.repository;

import bank.system.domain.Identifier;
import bank.system.domain.common.Status;
import bank.system.domain.user.User;
import bank.system.domain.user.UserAuth;
import bank.system.domain.user.UserIdentifier;
import bank.system.infrastructure.persistence.query.Query;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.UUID;

import static java.lang.String.format;
import static bank.system.domain.common.Status.Type.ERROR;
import static bank.system.domain.common.Status.Type.SUCCESS;
import static java.util.Objects.isNull;


@RequiredArgsConstructor
public class UserPostgresRepository implements UserRepository {

    private final Connection connection;

    private UserPostgresRepository() {
        this(null);
    }

    @Override
    public Status<?> createUser(User user) {
        final var createUserSql = Query.CREATE_USER_QUERY;

        try (final var preparedStatement = connection.prepareStatement(createUserSql.getQuery())) {
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
        final var updateUserSql = Query.UPDATE_USER_QUERY;

        try (final var preparedStatement = connection.prepareStatement(updateUserSql.getQuery())) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getFullName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on update user: %s", e.getMessage()));
        }

        return new Status<>(SUCCESS.name(), format("User %s updated successfully", user.getUsername()));
    }

    @Override
    public Status<?> retrieveUser(Identifier<UUID> identifier) {
        final var retrieveUserSql = Query.RETRIEVE_USER_BY_ID_QUERY;

        try (final var preparedStatement = connection.prepareStatement(retrieveUserSql.getQuery())) {
            preparedStatement.setObject(1, identifier.getValue());

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                user = User.create(UserIdentifier.from(id), resultSet.getString("username"), null);
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setFullName(resultSet.getString("full_name"));
                user.setDocumentNumber(resultSet.getString("document_number"));
            }

            if (isNull(user))
                return new Status<>(ERROR.name(), "User not found");

            return new Status<>(SUCCESS.name(), user);

        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on retrieve user: %s", e.getMessage()));
        }
    }

    @Override
    public Status<?> deleteUser(Identifier<UUID> identifier) {
        return null;
    }

    @Override
    public Status<?> findUserAuthPassword(String authType, String search) {
        Query query = Query.getByFilter(authType);

        try (final var preparedStatement = connection.prepareStatement(query.getQuery())) {
            preparedStatement.setObject(1, search);

            ResultSet resultSet = preparedStatement.executeQuery();
            UserAuth userAuth = null;

            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                userAuth = new UserAuth(UserIdentifier.from(id), resultSet.getString("password"));
            }

            if (isNull(userAuth))
                return new Status<>(ERROR.name(), "User not found");

            return new Status<>(SUCCESS.name(), userAuth);

        } catch (SQLException e) {
            return new Status<>(ERROR.name(), format("Error on retrieve user: %s", e.getMessage()));
        }
    }
}
package bank.system.infrastructure.persistence.query;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Query {

    public static final String CREATE_USER_QUERY = """
        INSERT INTO `user`('username', 'password', 'email', 'document_number', 'phone', 'full_name')
        VALUES(?, ?, ?, ?, ?, ?);
    """;

    public static final String UPDATE_USER_QUERY = """
        UPDATE `user`
            SET "username" = ?
            SET "email" = ?
            SET "phone" = ?
            SET "full_name" = ?
        WHERE 'id' = ?;
    """;

    public static final String RETRIEVE_USER_BY_ID_QUERY = """
        SELECT * FROM "user" WHERE "id" = ?;
    """;


    public static final String RETRIEVE_USER_BY_DOCUMENT_NUMBER_QUERY = """
        SELECT * FROM "user" WHERE "document_number" = ?;
    """;

}
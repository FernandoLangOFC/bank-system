package bank.system.infrastructure.persistence.query;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Query {
    CREATE_USER_QUERY("CREATE", """
            INSERT INTO `bank_user`('username', 'password', 'email', 'document_number', 'phone', 'full_name')
            VALUES(?, ?, ?, ?, ?, ?);
            """),

    UPDATE_USER_QUERY("UPDATE", """
            UPDATE `bank_user`
            SET "username" = ?
            SET "email" = ?
            SET "phone" = ?
            SET "full_name" = ?
            WHERE 'id' = ?;
            """),

    RETRIEVE_USER_BY_ID_QUERY("RETRIEVE_ID", """
            SELECT * FROM "bank_user" WHERE "id" = ? LIMIT 1;
            """),

    RETRIEVE_USER_BY_DOCUMENT_NUMBER_QUERY("RETRIEVE_DOCUMENT", """
            SELECT id, password FROM "bank_user" WHERE "document_number" = ? LIMIT 1;
            """),

    RETRIEVE_USER_BY_EMAIL_QUERY("RETRIEVE_EMAIL", """
            SELECT id, password FROM "bank_user" WHERE "email" = ? LIMIT 1;
            """);

    private final String filter;
    private final String query;

    public static Query getByFilter(String filter) {
        for(Query _query : Query.values()) {
            if (_query.filter.equals(filter))
                return _query;
        }

        throw new RuntimeException("Filter not found");
    }

}
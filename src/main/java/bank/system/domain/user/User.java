package bank.system.domain.user;

import bank.system.domain.base.RegisteredEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends RegisteredEntityBase<UUID> {

    // Entity<Idendifier<UUID>> no make sence in this context
    // Is justifiable to use in Multi Class field as Id, in a web app for example
    // An EntityBase<UUID> is a much simpler version of this pattern.
    // ID is not something whose type can be changed so easily, so there's no need to
    // decouple it from the entity.
    // However, i made this BaseEntity to be in the middle, a simpler version of what
    // it was before.
    // Don't feed Cats with Bird's seeds, it's not sustainable

    /*
        - The main purpose of 'Identity VOs' it is not to depend on Logical Relationships.
        Instead of it, create a 'Physical Relationship between the entities'.

        - If there are classes that has 'User' by composition and the only relationship it's an attribute with
        a 'primitive' value, this will be a logical relationship and this is a bad pattern.

        - By the way, like you said, it's rare the identifier type change,
        but decouple it's only one benefit of an Identity VO.

        https://medium.com/@gara.mohamed/domain-driven-design-the-identifier-type-pattern-d86fd3c128b3

        */

    private UserIdentifier userID;
    private String username;
    private String password;
    private String documentNumber;
    private String fullName;
    private String email;
    private String phone;

    private User(final UUID uuid) {
        super(uuid);
    }

    private User(final UserIdentifier anId) {
        super(anId.getValue());
        this.userID = anId;
    }

    public static User create() {
        return new User(UserIdentifier.random());
    }
}
package bank.system.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Hash {

    public static String hashPassword(final String password) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
        return argon2.hash(2, 15 * 24, 1, password.toCharArray());
    }

    public static boolean validatePassword(final String hash, final String password) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
        return argon2.verify(hash, password.toCharArray());
    }
}
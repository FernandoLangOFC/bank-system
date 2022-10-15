package bank.system.infrastructure.repository;


import bank.system.domain.user.UserAuth;
import bank.system.infrastructure.common.Status;
import bank.system.domain.user.User;

import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {
    Status<UserAuth<UUID>> findUserAuthPassword(final String authType, final String search);
}
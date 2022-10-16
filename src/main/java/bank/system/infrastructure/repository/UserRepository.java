package bank.system.infrastructure.repository;


import bank.system.domain.user.UserAuthRequest;
import bank.system.infrastructure.common.Status;
import bank.system.domain.user.User;

import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {
    Status<UserAuthRequest<UUID>> findUserAuthPassword(final UserAuthRequest<UUID> userAuthRequest);
}
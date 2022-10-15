package bank.system.infrastructure.repository;

import bank.system.domain.base.EntityBase;
import bank.system.infrastructure.common.Status;

public interface Repository<T extends EntityBase<U>, U> {
    Status<T> create(T entity);
    Status<T> update(T entity);
    Status<T> findByID(U id);
    Status<T> delete(U id);
}
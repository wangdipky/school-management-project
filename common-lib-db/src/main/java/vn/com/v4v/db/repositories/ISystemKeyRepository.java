package vn.com.v4v.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.v4v.db.entities.SystemKey;

@Repository
public interface ISystemKeyRepository extends JpaRepository<SystemKey, Long> {
}
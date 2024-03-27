package BackendSiadseUfps.siadse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import BackendSiadseUfps.siadse.entity.OurUsers;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {
    Optional<OurUsers> findByEmail(String email);
}
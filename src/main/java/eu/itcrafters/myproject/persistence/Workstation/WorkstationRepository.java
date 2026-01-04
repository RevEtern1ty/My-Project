package eu.itcrafters.myproject.persistence.Workstation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkstationRepository extends JpaRepository<Workstation, Long> {
    Optional<Workstation> findByCode(String code);
}

package mk.finki.ukim.mk.emtlabs02.repository;

import mk.finki.ukim.mk.emtlabs02.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
}
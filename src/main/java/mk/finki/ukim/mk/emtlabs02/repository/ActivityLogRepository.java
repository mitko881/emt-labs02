package mk.finki.ukim.mk.emtlabs02.repository;

import mk.finki.ukim.mk.emtlabs02.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
}
package pl.ignacy.producer_load_optimiser.outbox;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxMessage, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT m FROM OutboxMessage m WHERE m.status = :status ORDER BY m.createdAt ASC")
    @QueryHints(@QueryHint(name = "jakarta.persistence.lock.timeout", value = "-2"))
    List<OutboxMessage> findByStatusWithLock(@Param("status") OutboxStatus status, Pageable pageable);

    void deleteByStatusAndSentAtBefore(OutboxStatus status, LocalDateTime before);
}

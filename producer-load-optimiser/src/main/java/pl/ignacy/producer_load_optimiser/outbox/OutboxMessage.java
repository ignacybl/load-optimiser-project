package pl.ignacy.producer_load_optimiser.outbox;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "outbox", indexes = {@Index(name = "idx_outbox_status", columnList = "status")})
@Getter
@Setter
@NoArgsConstructor
public class OutboxMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private String requestId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OutboxStatus status = OutboxStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime sentAt;

    public OutboxMessage(String topic, String requestId, String payload) {
        this.topic = topic;
        this.requestId = requestId;
        this.payload = payload;
    }
}

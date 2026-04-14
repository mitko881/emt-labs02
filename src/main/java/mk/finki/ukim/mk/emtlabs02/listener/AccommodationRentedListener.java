package mk.finki.ukim.mk.emtlabs02.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.finki.ukim.mk.emtlabs02.event.AccommodationRentedEvent;
import mk.finki.ukim.mk.emtlabs02.model.ActivityLog;
import mk.finki.ukim.mk.emtlabs02.repository.ActivityLogRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccommodationRentedListener {

    private final ActivityLogRepository activityLogRepository;

    @EventListener
    public void handleAccommodationRented(AccommodationRentedEvent event) {
        var accommodation = event.getAccommodation();

        log.info("Accommodation rented: {}", accommodation.getName());

        ActivityLog logEntry = ActivityLog.builder()
                .accommodationName(accommodation.getName())
                .eventTime(LocalDateTime.now())
                .eventType(accommodation.getNumRooms() == 0 ? "FULLY_BOOKED" : "RENTED")
                .build();

        activityLogRepository.save(logEntry);

        if (accommodation.getNumRooms() == 0) {
            log.info("Accommodation {} has no free rooms", accommodation.getName());
        }
    }
}
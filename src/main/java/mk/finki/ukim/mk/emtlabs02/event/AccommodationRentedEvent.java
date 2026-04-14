package mk.finki.ukim.mk.emtlabs02.event;

import lombok.Getter;
import mk.finki.ukim.mk.emtlabs02.model.Accommodation;
import org.springframework.context.ApplicationEvent;

@Getter
public class AccommodationRentedEvent extends ApplicationEvent {

    private final Accommodation accommodation;

    public AccommodationRentedEvent(Object source, Accommodation accommodation) {
        super(source);
        this.accommodation = accommodation;
    }
}
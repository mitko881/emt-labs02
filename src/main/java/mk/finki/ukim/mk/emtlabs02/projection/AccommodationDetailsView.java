package mk.finki.ukim.mk.emtlabs02.projection;

import mk.finki.ukim.mk.emtlabs02.model.Category;
import org.springframework.beans.factory.annotation.Value;

public interface AccommodationDetailsView {
    Long getId();
    String getName();
    Category getCategory();
    Integer getNumRooms();

    @Value("#{target.host.name + ' ' + target.host.surname}")
    String getHostFullName();

    @Value("#{target.host.country.name}")
    String getCountryName();
}
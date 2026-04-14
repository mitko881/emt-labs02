package mk.finki.ukim.mk.emtlabs02.projection;

import mk.finki.ukim.mk.emtlabs02.model.Category;

public interface AccommodationShortView {
    Long getId();
    String getName();
    Category getCategory();
    Integer getNumRooms();
}
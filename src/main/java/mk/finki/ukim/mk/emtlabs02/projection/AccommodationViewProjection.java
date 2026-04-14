package mk.finki.ukim.mk.emtlabs02.projection;

public interface AccommodationViewProjection {
    Long getId();
    String getName();
    String getCategory();
    Integer getNumRooms();
    String getHostFullName();
    String getCountryName();
}
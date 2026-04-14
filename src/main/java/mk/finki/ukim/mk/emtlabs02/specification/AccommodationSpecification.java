package mk.finki.ukim.mk.emtlabs02.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import mk.finki.ukim.mk.emtlabs02.model.Accommodation;
import mk.finki.ukim.mk.emtlabs02.model.Category;
import mk.finki.ukim.mk.emtlabs02.model.Country;
import mk.finki.ukim.mk.emtlabs02.model.Host;
import org.springframework.data.jpa.domain.Specification;

public class AccommodationSpecification {

    public static Specification<Accommodation> hasCategory(Category category) {
        return (root, query, cb) ->
                category == null ? null : cb.equal(root.get("category"), category);
    }

    public static Specification<Accommodation> hasHost(Long hostId) {
        return (root, query, cb) ->
                hostId == null ? null : cb.equal(root.get("host").get("id"), hostId);
    }

    public static Specification<Accommodation> hasCountryName(String countryName) {
        return (root, query, cb) -> {
            if (countryName == null || countryName.isBlank()) return null;
            Join<Accommodation, Host> hostJoin = root.join("host", JoinType.INNER);
            Join<Host, Country> countryJoin = hostJoin.join("country", JoinType.INNER);
            return cb.equal(cb.lower(countryJoin.get("name")), countryName.toLowerCase());
        };
    }

    public static Specification<Accommodation> hasNumRooms(Integer numRooms) {
        return (root, query, cb) ->
                numRooms == null ? null : cb.equal(root.get("numRooms"), numRooms);
    }

    public static Specification<Accommodation> hasAvailable(Boolean available) {
        return (root, query, cb) -> {
            if (available == null) return null;
            return available
                    ? cb.greaterThan(root.get("numRooms"), 0)
                    : cb.equal(root.get("numRooms"), 0);
        };
    }
}
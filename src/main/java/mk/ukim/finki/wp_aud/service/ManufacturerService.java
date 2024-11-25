package mk.ukim.finki.wp_aud.service;

import mk.ukim.finki.wp_aud.model.Manufacturer;

import java.util.*;

public interface ManufacturerService {
    List<Manufacturer> findAll();

    Optional<Manufacturer> findById(Long id);

    Optional<Manufacturer> save(String name, String address);

    boolean deleteByID(Long id);

}

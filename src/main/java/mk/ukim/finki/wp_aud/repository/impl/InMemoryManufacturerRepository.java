package mk.ukim.finki.wp_aud.repository.impl;

import mk.ukim.finki.wp_aud.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {

    public List<Manufacturer> findAll() {
        return DataHolder.manufacturerList;
    }

    public Optional<Manufacturer> findById(Long id) {
        return DataHolder.manufacturerList.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public Optional<Manufacturer> save(String name, String address) {
        Manufacturer manufacturer = new Manufacturer(name, address);
        DataHolder.manufacturerList.add(manufacturer);
        return Optional.of(manufacturer);
    }

    public boolean deleteByID(Long id) {
        return DataHolder.manufacturerList.removeIf(r -> r.getId().equals(id));
    }
}

package mk.ukim.finki.wp_aud.service.implementation;

import mk.ukim.finki.wp_aud.model.Manufacturer;
import mk.ukim.finki.wp_aud.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.wp_aud.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return Optional.of(manufacturerRepository.save(new Manufacturer(name, address)));
    }

    @Override
    public void deleteByID(Long id) {
        manufacturerRepository.deleteById(id);
    }

}

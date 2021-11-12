package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.PlateDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlateService {

    private final PlateRepository plateRepository;
    private final RestaurantRepository restaurantRepository;

    /**
     * Function that make a query with the name of the plate as parameter and check in the database if the name is present
     * @param namePlate
     * @return
     * @author: Thomas B.P.
     */
    public PlateDto getPlateByName(String namePlate){
        Optional<Plate> opt = plateRepository.findByName(namePlate);
        if (!opt.isPresent()){
            return null;
        }
        return PlateDto.convertToPlateDto(opt.get());
    }

    /**
     * Function to register new Plate
     * @param plateForm
     * @return
     * @author: Thomas B.P.
     */
    public PlateDto register(PlateForm plateForm){
        Optional<Restaurant> restaurant = restaurantRepository.findById(plateForm.getRestaurantId());
        Plate plate = plateForm.convertToPlate(plateForm);
        plate.setRestaurant(restaurant.orElse(null));
        plateRepository.save(plate);
        return new PlateDto(plate);
    }


    /**
     * Function to detail a new Plate
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    public PlateDto details(Long id) {
    Optional<Plate> plate = plateRepository.findById(id);
        if (!plate.isPresent()) {
        return null;
        }
        return new PlateDto(plate.get());
    }


    /**
     * Function to update Plate data
     * @param id
     * @param form
     * @return
     * @author: Luis Gregorio
     */
    public PlateDto update(Long id, PlateFormUpdate form) {
        Optional<Plate> opt = plateRepository.findById(id);
        if (opt.isPresent()) {
            Plate plate = form.update(id, plateRepository);
            return new PlateDto(plate);
        }
        return null;
    }

    /**
     * Function to delete a Plate
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    public PlateDto remove(Long id) {
        Optional<Plate> plate = plateRepository.findById(id);
        if(plate.isPresent()) {
            plateRepository.deleteById(id);
            return new PlateDto(plate.get());
        }
        return null;
    }
}

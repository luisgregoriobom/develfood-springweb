package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.PlateDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RestaurantDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import com.sun.java.accessibility.util.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * Function to register new Plate to the current restaurant logged in
     * @param plateForm
     * @return
     * @author: Thomas B.P.
     */
    public PlateDto register(PlateForm plateForm){
        Plate plate = plateForm.convertToPlate(plateForm);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentRestaurantAuth = authentication.getName();
        Optional<Restaurant> currentRestaurant = restaurantRepository.findByEmail(currentRestaurantAuth);
        if (!currentRestaurant.isPresent()){
            return null;
        }
        plate.setPhoto(plateForm.getPhoto());
        plate.setRestaurant(currentRestaurant.orElse(null));

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
        Plate plateUpdate = form.convertToPlateUpdate(form);
        Optional<Plate> opt = plateRepository.findById(id);
        if (opt.isPresent()) {
            Plate plate = form.update(id, plateRepository);
            String photo = plateUpdate.getPhoto();
            form.setPhoto(photo);
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

    /**
     * Function to list all categories that a plate may have.
     * @return
     * @author: Thomas B.P.
     */
    public List<String> listOfCategory(){
        List<String> categories = Arrays.stream(Category.values()).map(Category::getTranslation).collect(Collectors.toList());
        return categories;
    }

}

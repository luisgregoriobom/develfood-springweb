package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.PlateDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlateService {

    private final PlateRepository plateRepository;

    private final RestaurantRepository restaurantRepository;

    /**
     *      * Function that make a query with the name of the plate as parameter and check in the database if the name is present
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
        Plate plate = plateForm.convertToPlate(plateForm);
        plateRepository.save(plate);
        return new PlateDto(plate);
    }

    public PlateDto registrar(PlateForm plateForm){
        Optional<Restaurant> restaurant = restaurantRepository.findById(plateForm.getRestaurantId());
        if (!restaurant.isPresent()){
            return null;
        }
        Plate plate = plateForm.convertToPlate(plateForm);
        plateRepository.save(plate);
        return new PlateDto(plate);
    }
// TENTATIVA DA FUNÇÃO DE CRIAR UM PRATO E JÁ ATRELAR A UM ID DE UM RESTAURANTE JÁ CADASTRADO - A SER IMPLEMENTADO
//    public PlateDto register(PlateForm plateForm){
//        Optional<Restaurant> restaurant = restaurantRepository.findById(plateForm.getRestaurantId());
//        if (!restaurant.isPresent()){
//            return null;
//        }
//        Plate plate = plateForm.convertToPlate(plateForm);
//        plateRepository.save(plate);
//        return new PlateDto(plate);
//    }

}

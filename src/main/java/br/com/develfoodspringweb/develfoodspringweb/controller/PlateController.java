package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.PlateDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.service.PlateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/plate")
public class PlateController {

    private final PlateRepository plateRepository;

    private final PlateService plateService;

    /**
     * Method GET to list all plates
     * @param plateName
     * @return
     * @author: Luis Gregorio
     */
    @GetMapping("/ListAll")
    @Transactional
    public List<PlateDto> list(String plateName) {
        if (plateName == null) {
            List<Plate> plates = plateRepository.findAll();
            return PlateDto.converToListDto(plates);
        } else {
            List<Plate> plates = plateRepository.findByPlateName(plateName);
            return PlateDto.converToListDto(plates);
        }
    }

    /**
     * Function with GET method to do make a query with the name of the plate as parameter.
     * @param namePlate
     * @return
     * @author: Thomas B.P.
     */
    @GetMapping
    public ResponseEntity<PlateDto> getPlateByName(@RequestParam String namePlate){
        if(namePlate == null){
            return null;
        }
        PlateDto queryByName = plateService.getPlateByName(namePlate);
        if (queryByName == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                            "Plate name not found");
        }
        return new ResponseEntity<>(queryByName, HttpStatus.OK);

    }

    /**
     * Function with POST method to register new Plate while the function create the URI route and return the head HTTP location with the URL
     * @param plateForm
     * @param uriBuilder
     * @return
     * @author: Thomas B.P.
     */
    @PostMapping
    public ResponseEntity<PlateDto> register(@RequestBody @Valid PlateForm plateForm,
                                             UriComponentsBuilder uriBuilder){

        PlateDto plateToRegister = plateService.register(plateForm);

        if (plateToRegister == null){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Failed to register new plate");
        }

        URI uri = uriBuilder.
                path("/api/plate/{id}").
                buildAndExpand(plateToRegister.getId()).
                toUri();

        return ResponseEntity.created(uri).body(plateToRegister);
    }


//    @GetMapping
//    public List<PlateDto> listOfPlates (Long restaurantId){
//
//        List<Plate> plates = plateRepository.findById();
//        instancio o objeto, chamo no repositorio fazendo o getId
//        faço a arrow function do optional pra list dos ID pra retornar uma DTOLIST (igual do filtro)
//        retorno umalista de pratos do restaurantId
//
//    }

    /**
     * GET function to detail a Plate
     * @param id
     * @return
     * @author: Luis Gregorio
     */

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<PlateDto> details(@PathVariable Long id) {
        PlateDto plateDetail = plateService.details(id);
        if(plateDetail == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Plate Not Found");
        }
            return ResponseEntity.ok(plateDetail);
    }

    /**
     * PUT function to update a Plate
     * @param id
     * @param form
     * @return
     * @author: Luis Gregorio
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PlateDto> update(@PathVariable Long id, @RequestBody @Valid PlateFormUpdate form) {
        PlateDto plateUpdate = plateService.update(id, form);
        if (plateUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Plate Not Found");
        }
        return ResponseEntity.ok(plateUpdate);
    }

    /**
     * DELETE function to delete a Plate
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id){
       PlateDto plateRemove = plateService.remove(id);
       if(plateRemove == null) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                   "Plate Not Found");
       }
       return ResponseEntity.ok().build();
    }
}
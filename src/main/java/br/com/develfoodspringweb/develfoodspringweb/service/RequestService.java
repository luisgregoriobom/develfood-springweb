package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.configuration.valid.ApiExceptionError;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.PlateDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon.PlatePresent;
import br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon.RequestPresent;
import br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon.UserPresent;
import br.com.develfoodspringweb.develfoodspringweb.controller.userCommon.PlatePresentUser;
import br.com.develfoodspringweb.develfoodspringweb.controller.userCommon.RequestPresentUser;
import br.com.develfoodspringweb.develfoodspringweb.controller.userCommon.RestaurantPresentUser;
import br.com.develfoodspringweb.develfoodspringweb.controller.userCommon.UserPresentUser;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created By Luis Gregorio
 *
 * Service created to implement the search method in the controller.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final PlateRepository plateRepository;
    private final RestaurantRepository restaurantRepository;

    /**
     * Function to create new request from the current user logged in
     * @param requestForm
     * @return
     * @author: Thomas Benetti
     */
    public RequestDto registerRequest(RequestForm requestForm) {
        Optional<Restaurant> idRestaurants = restaurantRepository.findById(requestForm.getRestaurantId());

        if (idRestaurants.isPresent()) {
            requestForm.getPlatesId().forEach(pp -> {
                var valid = idRestaurants.get().getPlates().stream().anyMatch(plate -> plate.getId().equals(pp.getId()));
                        if(!valid) {
                            throw new ApiExceptionError("It is impossible to create a request with plates from other restaurants");
                        }
            });

            Request request = requestForm.convertToUserRequest(requestForm);
            request.setRestaurant(idRestaurants.get());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserAuth = authentication.getName();
            Optional<User> currentUser = userRepository.findByEmail(currentUserAuth);
            if (!currentUser.isPresent()) {
                return null;
            }

            List<Plate> listPlate = new ArrayList<>();
            List<PlateDto> listPlateDto = new ArrayList<>();
            plateManipulation(requestForm, request, listPlate, listPlateDto);

            request.setPlateId(listPlate);

            request.setUser(currentUser.get());
            requestRepository.save(request);

            RequestDto requestDto = new RequestDto(request);
            requestDto.setPlateDtos(listPlateDto);
            requestDto.setUser(null);

            return requestDto;
        }
        return null;
    }

    /**
     * Function to list all requests that a User has made
     * @return
     * @author: Thomas B.P.
     */
    public List<RequestDto> viewUserRequests (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserAuth = authentication.getName();
        Optional<User> currentUser = userRepository.findByEmail(currentUserAuth);
        List<Request> requests = currentUser.get().getRequest();

        List<RequestDto> requestsFromUser = RequestDto.convertToListDto(requests);

        return requestsFromUser;

        }

    /**
     * Function to list all requests that a User has made
     * @return
     * @author: Thomas B.P.
     */
    public List<RequestDto> viewRestaurantRequests (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserAuth = authentication.getName();
        Optional<Restaurant> currentUser = restaurantRepository.findByEmail(currentUserAuth);
        List<Request> requests = currentUser.get().getRequests();

        List<RequestDto> requestsFromRestaurant = RequestDto.convertToListDto(requests);

        return requestsFromRestaurant;

    }

    /**
     * Refactored method for returning the DTO in the create request.
     * This method aims to show the quantity of products as well as their sum, adding a final sum to the request.
     *
     * @param requestForm
     * @param request
     * @param listPlate
     * @param listPlateDto
     * @author: Luis Gregorio
     */
    private void plateManipulation(RequestForm requestForm, Request request, List<Plate> listPlate, List<PlateDto> listPlateDto) {
        requestForm.getPlatesId().forEach(vl ->{
            Optional<Plate> platesFromRequest = plateRepository.findById(vl.getId());
            PlateDto plateDto = new PlateDto();
            plateDto.setPhoto(platesFromRequest.get().getPhoto());
            plateDto.setId(platesFromRequest.get().getId());
            plateDto.setName(platesFromRequest.get().getName());
            plateDto.setDescription(platesFromRequest.get().getDescription());
            plateDto.setPrice(platesFromRequest.get().getPrice());
            plateDto.setCategory(platesFromRequest.get().getCategory());
            plateDto.setQuantity(vl.getQuantity());
            plateDto.setObs(vl.getObs());
            plateDto.setRestaurantName(platesFromRequest.get().getRestaurant().getName());

            if (platesFromRequest.isPresent()) {
                Double priceTotal = vl.getQuantity() * platesFromRequest.get().getPrice();
                Double preco = request.getPriceTotal() + priceTotal;
                plateDto.setPriceTotal(priceTotal);
                request.setPriceTotal(preco);
            }
            listPlate.add(platesFromRequest.get());
            listPlateDto.add(plateDto);
        });
    }

    /**
     * Search method using the service query to be used in the Controller class
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    public RequestDto searchRequestId(Long id) {
        Request request = requestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Request not found"));
        RequestDto dto = new RequestDto();
        dto.setPlates(request.getPlateId());
        BeanUtils.copyProperties(request, dto);
        return dto;
    }

    /**
     * Method created to use the attributes coming from the Present classes,
     * setting and simplifying the information in the request body
     *
     * @param dto
     * @return
     * @author: Luis Gregorio
     */
    public RequestPresent convertToPresent(RequestDto dto) {
        RequestPresent present = new RequestPresent();
        present.setId(dto.getId());
        present.setStatus(dto.getStatus());
        present.setDateRequest(dto.getDateRequest());

        List<PlatePresent> platePresents = new ArrayList<>();
        dto.getPlates().forEach(plates ->{
            PlatePresent platePresent = new PlatePresent();
            platePresent.setName(plates.getName());
            platePresent.setDescription(plates.getDescription());
            platePresent.setPrice(plates.getPrice());
            platePresent.setCategory(plates.getCategory());
            platePresent.setPhoto(plates.getPhoto());
            platePresent.setObs(plates.getObs());
            platePresents.add(platePresent);
        });

        UserPresent userPresent = new UserPresent();
        userPresent.setName(dto.getUser().getName());
        userPresent.setAddress(dto.getUser().getAddress());
        userPresent.setCpf(dto.getUser().getCpf());
        userPresent.setEmail(dto.getUser().getEmail());
        userPresent.setPhone(dto.getUser().getPhone());
        userPresent.setPhone(dto.getUser().getPhoto());
        present.setUser(userPresent);
        present.setPlates(platePresents);

        return present;
    }

    /**
     * Method created to use the attributes coming from the Present classes,
     * setting and simplifying the information in the request body
     *
     * @param dto
     * @return
     * @author: Luis Gregorio
     */
    public RequestPresentUser convertToUserPresent(RequestDto dto) {
        RequestPresentUser present = new RequestPresentUser();
        present.setId(dto.getId());
        present.setStatus(dto.getStatus());
        present.setDateRequest(dto.getDateRequest());
        List<PlatePresentUser> platePresents = new ArrayList<>();
        dto.getPlates().forEach(plate ->{
            PlatePresentUser platePresent = new PlatePresentUser();
            platePresent.setDescription(plate.getDescription());
            platePresent.setPrice(plate.getPrice());
            platePresent.setCategory(plate.getCategory());
            platePresent.setName(plate.getName());
            platePresent.setPhoto(plate.getPhoto());
            platePresent.setObs(plate.getObs());
            RestaurantPresentUser restaurant = new RestaurantPresentUser();
            restaurant.setName(plate.getRestaurant().getName());
            platePresent.setRestaurant(restaurant);
            platePresents.add(platePresent);
        });

        UserPresentUser userPresent = new UserPresentUser();
        userPresent.setAddress(dto.getUser().getAddress());
        userPresent.setPhone(dto.getUser().getPhone());
        userPresent.setPhoto(dto.getUser().getPhoto());
        present.setUser(userPresent);
        present.setPlates(platePresents);


        return present;
    }
}

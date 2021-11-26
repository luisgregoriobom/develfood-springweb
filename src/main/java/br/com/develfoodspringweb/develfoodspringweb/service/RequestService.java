package br.com.develfoodspringweb.develfoodspringweb.service;

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
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * Function to create new request from the current user logged in
     * @param requestForm
     * @return
     * @author: Thomas Benetti
     */
    public RequestDto registerRequest(RequestForm requestForm){
        Request request = requestForm.convertToUserRequest(requestForm);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserAuth = authentication.getName();
        Optional<User> currentUser = userRepository.findByEmail(currentUserAuth);
        if (!currentUser.isPresent()){
            return null;
        }
        List<Plate> platesFromRequest = plateRepository.findAllById(requestForm.getPlatesId());
        if (platesFromRequest.isEmpty()){
            return null;
        }

        request.setPlateId(platesFromRequest);

        platesFromRequest.stream().forEach(pl -> {
                Double preco = request.getPriceTotal() + pl.getPrice();
                request.setPriceTotal(preco);
            });
        request.setUser(currentUser.get());
        requestRepository.save(request);

        RequestDto requestDto = new RequestDto(request);
        requestDto.setUser(null);

        return requestDto;
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
        present.setObs(dto.getObs());
        present.setDateRequest(dto.getDateRequest());

        List<PlatePresent> platePresents = new ArrayList<>();
        dto.getPlates().forEach(plates ->{
            PlatePresent platePresent = new PlatePresent();
            platePresent.setName(plates.getName());
            platePresent.setDescription(plates.getDescription());
            platePresent.setPrice(plates.getPrice());
            platePresent.setCategory(plates.getCategory());
            platePresent.setPhoto(plates.getPhoto());
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
        present.setObs(dto.getObs());
        present.setDateRequest(dto.getDateRequest());
        List<PlatePresentUser> platePresents = new ArrayList<>();
        dto.getPlates().forEach(plate ->{
            PlatePresentUser platePresent = new PlatePresentUser();
            platePresent.setDescription(plate.getDescription());
            platePresent.setPrice(plate.getPrice());
            platePresent.setCategory(plate.getCategory());
            platePresent.setName(plate.getName());
            platePresent.setPhoto(plate.getPhoto());
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

package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Luis Gregorio
 *
 * Service created to implement the search method in the controller.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestService {

    private final RequestRepository requestRepository;

    /**
     * Search method using the service query to be used in the Controller class
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    public RequestDto searchRequestId(Long id) {
        Request request = requestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Request not found"));
        RequestDto dto = new RequestDto();
        BeanUtils.copyProperties(request, dto);
        return dto;
    }
}

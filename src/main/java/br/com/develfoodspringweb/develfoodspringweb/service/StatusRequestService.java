package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.controller.requestCommon.StatusPresent;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class StatusRequestService {

    private final RequestRepository requestRepository;

    public RequestDto update(Long id, RequestFormUpdate form) {
        Optional<Request> opt = requestRepository.findById(id);
        if (opt.isPresent()) {
            Request request = form.update(id, requestRepository);
            return new RequestDto(request);
        }
        return null;
    }

    /**
     * Method for setting the information that will appear in the JSON response
     * @param dto
     * @return
     * @author: Luis Gregorio
     */
    public StatusPresent convertToPresent(RequestDto dto) {
        StatusPresent present = new StatusPresent();
        present.setId(dto.getId());
        present.setStatus(dto.getStatus());
        present.setDateRequest(dto.getDateRequest());

        return present;
    }
}

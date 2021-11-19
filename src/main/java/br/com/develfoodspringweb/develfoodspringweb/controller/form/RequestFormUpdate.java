package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import br.com.develfoodspringweb.develfoodspringweb.models.StatusRequest;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Luis Gregorio.
 *
 * In this class we can define what data a plate can update in the system.
 */
@Data
public class RequestFormUpdate {

    @Enumerated(EnumType.STRING)
    private StatusRequest status = StatusRequest.WAITING_TO_ACCEPT;

    public Request update(Long id, RequestRepository requestRepository) {
        Request request = requestRepository.getById(id);
        request.setStatus(this.status);
        return request;
    }

}
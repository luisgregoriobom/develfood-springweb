package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.models.StatusRequest;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Data
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatusRequestService {

    private final RequestRepository requestRepository;



}

package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.EmailStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailDto {


    private String emailSubject = "ALTERAÇÃO DE STATUS - PEDIDO N°: ";
    private String emailSubjectUser = "BEM VINDO A PLATAFORMA DEVELFOOD, Sr(a): ";
    private String emailDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    private EmailStatus emailStatus;

}

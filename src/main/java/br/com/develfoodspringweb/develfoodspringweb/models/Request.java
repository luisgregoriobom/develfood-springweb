package br.com.develfoodspringweb.develfoodspringweb.models;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "requests")
@Data
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusRequest status = StatusRequest.WAITING_TO_ACCEPT;
    private String dateRequest = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    private String obs;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Plate> plates;
    private Double priceTotal = 0.00;


    public Request(RequestForm requestForm){
        this.id = requestForm.getId();
        this.obs = requestForm.getObs();
        this.user = requestForm.getUser();
        this.plates = requestForm.getPlates();
    }

}

    private LocalDateTime dateRequest = LocalDateTime.now();
    @ManyToOne
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "request", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Plate> plate;

    public Request(Long id, StatusRequest status, LocalDateTime dateRequest, User user, List<Plate> plate) {
        this.id = id;
        this.status = status;
        this.dateRequest = dateRequest;
        this.user = user;
        this.plate = plate;
    }
}
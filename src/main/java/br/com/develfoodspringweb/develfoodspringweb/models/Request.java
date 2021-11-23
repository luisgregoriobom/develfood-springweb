package br.com.develfoodspringweb.develfoodspringweb.models;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "requests")
@Getter
@Setter
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusRequest status = StatusRequest.WAITING_TO_ACCEPT;
    private String dateRequest = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    private String obs;
    private Double priceTotal = 0.00;
    @ManyToOne
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="plates_request", joinColumns=
    @JoinColumn(name="plate_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="request_id", referencedColumnName="id"))
    @JsonIgnore
    private List<Plate> plateId;


    public Request(RequestForm requestForm){
        this.id = requestForm.getId();
        this.obs = requestForm.getObs();
        this.user = requestForm.getUser();
        this.plateId = requestForm.getPlates();
    }

    public Request(Long id, StatusRequest status, String dateRequest, User user, List<Plate> plates) {
        this.id = id;
        this.status = status;
        this.dateRequest = dateRequest;
        this.user = user;
        this.plateId = plates;
    }
}

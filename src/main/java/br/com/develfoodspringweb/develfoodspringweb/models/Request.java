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
    private LocalDateTime dateRequest = LocalDateTime.now(); //.format()
    private String obs;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Plate> plate;
    private Double priceTotal = 0.00;


    public Request(RequestForm requestForm){
        this.id = requestForm.getId();
        this.obs = requestForm.getObs();
        this.user = requestForm.getUser();
        this.plate = requestForm.getPlates();
    }

}

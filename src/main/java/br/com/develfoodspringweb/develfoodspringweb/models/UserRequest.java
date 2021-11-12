package br.com.develfoodspringweb.develfoodspringweb.models;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "requests")
@Data
@NoArgsConstructor
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusRequest status = StatusRequest.WAITING_TO_ACCEPT;
    private LocalDateTime dateRequest;
    private String obs;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "userRequest")
    private List<Plate> plate;

    public UserRequest(RequestForm requestForm){
        this.id = requestForm.getId();
        this.status = requestForm.getStatus();
        this.dateRequest = requestForm.getDateRequest();
        this.obs = requestForm.getObs();
        this.user = requestForm.getUser();
        this.plate = requestForm.getPlates();
    }

}

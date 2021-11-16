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
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusRequest status = StatusRequest.WAITING_TO_ACCEPT;
    private LocalDateTime dateRequest = LocalDateTime.now();
    private String obs;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "request")
    private List<Plate> plate;

    public Request(RequestForm requestForm){
        this.id = requestForm.getId();
        this.obs = requestForm.getObs();
        this.user = requestForm.getUser();
        this.plate = requestForm.getPlates();
    }

    public void setUserName(User user){
        this.user.setName(user.getName());
    }

}

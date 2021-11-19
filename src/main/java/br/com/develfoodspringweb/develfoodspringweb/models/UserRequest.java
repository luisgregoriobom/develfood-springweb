package br.com.develfoodspringweb.develfoodspringweb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "requests")
@Data
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusRequest status = StatusRequest.EMPTY;
    private LocalDateTime dateRequest;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "userRequest")
    private List<Plate> plate;

    public void nextStatus() {
        if(status == StatusRequest.WAITING_TO_ACCEPT) {

        }
    }

}

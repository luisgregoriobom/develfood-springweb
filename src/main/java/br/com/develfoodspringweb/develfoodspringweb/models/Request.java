package br.com.develfoodspringweb.develfoodspringweb.models;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestForm;
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
    private LocalDateTime dateRequest = LocalDateTime.now();
    @ManyToOne
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

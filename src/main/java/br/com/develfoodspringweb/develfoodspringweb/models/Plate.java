package br.com.develfoodspringweb.develfoodspringweb.models;


import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "plates")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Plate {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Restaurant restaurant;
    @Column(columnDefinition = "text")
    private String photo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="plates_request", joinColumns=
    @JoinColumn(name="request_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="plate_id", referencedColumnName="id"))
    @JsonIgnore
    private List<Request> request;
    @OneToMany (mappedBy = "plateName")
    private List<Plate> plateName = new ArrayList<>();


    public Plate(String name, String description, Double price, Category category, String photo) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.photo = photo;

    }

    public Plate(Long id, String name, String description, Double price, String photo){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
    }

    public Plate(PlateForm plateForm){
        this.name = plateForm.getName();
        this.description = plateForm.getDescription();
        this.price = plateForm.getPrice();
        this.category = plateForm.getCategory();
    }
}

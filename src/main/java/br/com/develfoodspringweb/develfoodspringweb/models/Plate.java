package br.com.develfoodspringweb.develfoodspringweb.models;


import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "plates")
@Getter @Setter @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Plate {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    @JoinColumn(name = "quantity")
    private int quantity;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Restaurant restaurant;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="plates_request", joinColumns=
    @JoinColumn(name="request_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="plate_id", referencedColumnName="id"))
    @JsonIgnore
    private List<Request> request;
    @OneToMany (mappedBy = "plateName")
    @JsonIgnore
    private List<Plate> plateName = new ArrayList<>();


    public Plate(String name, String description, Double price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Plate(Long id, String name, String description, Double price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Plate(PlateForm plateForm){
        this.name = plateForm.getName();
        this.description = plateForm.getDescription();
        this.price = plateForm.getPrice();
        this.category = plateForm.getCategory();
        this.quantity = plateForm.getQuantity();
    }
}

package br.com.develfoodspringweb.develfoodspringweb.models;


import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "plates")
@Data @NoArgsConstructor
public class Plate {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String obs;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Restaurant restaurant;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "request_id")
    private Request request;
    @OneToMany
    private List<Plate> plateName = new ArrayList<>();

    public Plate(String name, String obs, BigDecimal price, Category category, Restaurant restaurant) {
        this.name = name;
        this.obs = obs;
        this.price = price;
        this.category = category;
        this.restaurant = restaurant;
    }

    public Plate(PlateForm plateForm){
        this.name = plateForm.getName();
        this.obs = plateForm.getObs();
        this.price = plateForm.getPrice();
        this.category = plateForm.getCategory();
    }
}

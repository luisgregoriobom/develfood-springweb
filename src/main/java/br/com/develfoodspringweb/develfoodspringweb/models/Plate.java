package br.com.develfoodspringweb.develfoodspringweb.models;


import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name = "plates")
@Data @NoArgsConstructor
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
    @ManyToOne
    private Request request;

    public Plate(String name, String description, Double price, Category category, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.restaurant = restaurant;
    }

    public Plate(PlateForm plateForm){
        this.name = plateForm.getName();
        this.description = plateForm.getDescription();
        this.price = plateForm.getPrice();
        this.category = plateForm.getCategory();
    }
}

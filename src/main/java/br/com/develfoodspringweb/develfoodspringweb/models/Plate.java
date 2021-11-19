package br.com.develfoodspringweb.develfoodspringweb.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String obs;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Restaurant restaurant;
    @ManyToOne
    @JsonIgnore
    private Request request;

    public Plate(String name, String obs, BigDecimal price, Category category, Restaurant restaurant) {
        this.name = name;
        this.obs = obs;
        this.price = price;
        this.category = category;
        this.restaurant = restaurant;
    }
}

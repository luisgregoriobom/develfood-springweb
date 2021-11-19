package br.com.develfoodspringweb.develfoodspringweb.models;


import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "plates")
@Data @NoArgsConstructor
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
    @ManyToOne
    private Request request;
    @OneToMany
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
    }
}

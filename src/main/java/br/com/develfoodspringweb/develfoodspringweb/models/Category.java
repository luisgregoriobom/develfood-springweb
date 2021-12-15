package br.com.develfoodspringweb.develfoodspringweb.models;

import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;


public enum Category {

    SAVORY("SAVORY", "SALGADO"),
    SWEET("SWEET", "DOCE"),
    JAPANESE("JAPANESE", "JAPONES"),
    CHINESE("CHINESE", "CHINES"),
    KOREAN("KOREAN", "COREANO"),
    MEXICAN("MEXICAN", "MEXICANO"),
    ARABIC("ARABIC", "ARABE"),
    BRAZILIAN("BRAZILIAN", "BRASILEIRO"),
    ITALIAN("ITALIAN", "ITALIANO"),
    THAI("THAI", "THAILANDES"),
    MEDITERRANEAN("MEDITERRANEAN", "MEDITERRANEO"),
    CONTEMPORARY("CONTEMPORARY", "CONTEMPORANEO");

    private final String name;
    private final String translation;

    /**
     * Constructor to get the translation of the ENUM.
     * @param name
     * @param translation
     * @author: Thomas B.P.
     */
    Category(String name, String translation){
        this.name = name;
        this.translation = translation;
    }

    public String getName() {
        return name;
    }

    public String getTranslation() {
        return this.translation;
    }
}

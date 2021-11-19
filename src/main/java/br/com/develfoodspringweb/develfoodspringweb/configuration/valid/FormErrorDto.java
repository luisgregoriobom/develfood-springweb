package br.com.develfoodspringweb.develfoodspringweb.configuration.valid;

import lombok.Data;

@Data
public class FormErrorDto {

    private String field;
    private String error;

    public FormErrorDto(String field, String error) {
        this.field = field;
        this.error = error;
    }
}

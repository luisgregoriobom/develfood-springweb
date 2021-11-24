package br.com.develfoodspringweb.develfoodspringweb.configuration.valid;

import lombok.Data;

/**
 * Created by Luis Gregorio.
 *
 * Class created to alert the user what needs to be filled in requests
 */
@Data
public class FormErrorDto {

    private String field;
    private String error;

    /**
     * method to demonstrate to the user which fields need to be filled in
     *
     * @param field
     * @param error
     * @author: Luis Gregorio
     */
    public FormErrorDto(String field, String error) {
        this.field = field;
        this.error = error;
    }
}

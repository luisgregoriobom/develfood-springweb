package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Luis Gregorio.
 *
 * Class created to transfer more simplified token data.
 */
@Data
@NoArgsConstructor
public class TokenDto {
    private String token;
    private String tipo;

    public TokenDto(String token, String tipo) {
    this.token = token;
    this.tipo = tipo;
    }
}

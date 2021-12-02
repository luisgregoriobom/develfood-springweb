package br.com.develfoodspringweb.develfoodspringweb.configuration.valid;

/**
 * Created By Luis Gregorio
 *
 *
 */
public class ApiExceptionError extends RuntimeException {

    public ApiExceptionError(String message) {
        super(message);
    }
}

package br.com.develfoodspringweb.develfoodspringweb.configuration.valid;

/**
 * Created By Luis Gregorio
 *
 * Class created to throw exception when creating request.
 */
public class ApiExceptionError extends RuntimeException {

    public ApiExceptionError(String message) {
        super(message);
    }
}

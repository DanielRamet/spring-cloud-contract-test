package base.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "NOT FOUND EXCEPTION TEST")
public class CustomExceptionNotFound extends RuntimeException {
    public CustomExceptionNotFound(Exception ex) {
        super(ex);
    }
    public CustomExceptionNotFound(String message) { super(message); }
    public CustomExceptionNotFound() {
        super();
    }
}

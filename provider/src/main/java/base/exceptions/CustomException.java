package base.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Id requested")
public class CustomException extends RuntimeException {

    public CustomException(String message) { super(message); }

    public CustomException(Exception ex) {
        super(ex);
    }
}


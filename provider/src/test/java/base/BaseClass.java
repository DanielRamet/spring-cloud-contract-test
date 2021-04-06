package base;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;

public class BaseClass {

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new PersonRestController());
    }

}

/* THIS CONTROLLER ADVICE WILL BE SET UP AT TEST LEVEL TO WRAP THE BEHAVIOR OF OUR APP
// Add this Controller advice to the MockMvc when setup.
@ControllerAdvice
class ExceptionController {

    @ExceptionHandler(CustomExceptionNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity handleException(CustomExceptionNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(ex);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity handleException2(CustomException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(ex);
    }
}

 */

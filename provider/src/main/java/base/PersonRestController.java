package base;

import base.exceptions.ContractError;
import base.exceptions.CustomException;
import base.exceptions.CustomExceptionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class PersonRestController {
    private final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    public PersonRestController() {
        this.personService = new PersonService();
    }

    @GetMapping(value = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findPersonById(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.ok(personService.findPersonById(id));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ContractError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createPerson() {
        throw new CustomExceptionNotFound();
    }

}

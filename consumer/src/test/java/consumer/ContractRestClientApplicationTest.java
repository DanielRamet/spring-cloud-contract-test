package consumer;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class ContractRestClientApplicationTest {
    @RegisterExtension
    public StubRunnerExtension stubRunner = new StubRunnerExtension()
            .downloadStub("org.example.drm", "provider", "0.0.1-SNAPSHOT", "stubs")
            .withPort(8100)
            .stubsMode(StubRunnerProperties.StubsMode.LOCAL);

    @Test
    public void get_person_from_service_contract() {
        // given:
        RestTemplate restTemplate = new RestTemplate();

        // when:
        ResponseEntity<Person> personResponseEntity = restTemplate.getForEntity("http://localhost:8100/person/1", Person.class);

        // then:
        BDDAssertions.then(personResponseEntity.getStatusCodeValue()).isEqualTo(200);
        BDDAssertions.then(personResponseEntity.getBody().getId()).isEqualTo(1l);
        BDDAssertions.then(personResponseEntity.getBody().getName()).isEqualTo("Richard");
        BDDAssertions.then(personResponseEntity.getBody().getSurname()).isEqualTo("Gere");
    }

    @Test
    public void get_person_from_service_negative(){

        // given:
        RestTemplate restTemplate = new RestTemplate();

        // when:
        try{
            restTemplate.getForEntity("http://localhost:8100/person/-2", Person.class);
            BDDAssertions.fail("It should fail");
        }catch (HttpClientErrorException e){
            // then:
            BDDAssertions.then(e).isNotNull();
            BDDAssertions.then(e.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            BDDAssertions.then(e.getMessage()).contains("The id cannot be negative");
        }
    }

    @Test
    public void post_person(){
        // given:
        RestTemplate restTemplate = new RestTemplate();

        // when:
        try{
            restTemplate.postForEntity("http://localhost:8100/person", null, null);
            BDDAssertions.fail("It should fail");
        }catch (HttpClientErrorException e){
            // then:
            BDDAssertions.then(e).isNotNull();
            BDDAssertions.then(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}

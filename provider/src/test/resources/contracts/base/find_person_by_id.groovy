package contracts.base

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "Test positive"
    description "should return person by id=1"

    request {
        url "/person/1"
        method GET()
    }

    response {
        status 200
        headers {
            contentType applicationJson()
        }
        body (
                id: 1,
                name: "Richard",
                surname: "Gere"
        )
    }
}

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "Test negative"
    description "Should fail when person by id is negative"
    request {
        url "/person/-2"
        method GET()
    }

    response {
        status 400
        headers {
            contentType applicationJson()
        }
        body (
                status: 400,
                message: "The id cannot be negative"
        )
    }
}
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "Create Test negative"
    description "Should fail when Create a person"
    request {
        url "/person"
        method POST()
    }

    response {
        status 404
    }
}
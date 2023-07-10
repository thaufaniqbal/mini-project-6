package contracts.controller.customer

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod

Contract.make {
    description "Reserve"
    request {
        url "/customer/reserve/"
        method HttpMethod.POST.toString()
        body("""
        {
            "customerId": 1,
            "roomId" : 1,
            "checkInDate" : "2023-07-16",
            "checkOutDate" : "2023-07-17"
        }
        """)
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 400
        body("""
        {
    "roomNumber": 1,
    "roomToken": null,
    "systemMessage": "Cannot reserve for the specified dates: 2023-07-16 - 2023-07-17"

        }
        """ )
    }
}


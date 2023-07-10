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
            "checkInDate" : "2023-08-11",
            "checkOutDate" : "2023-08-12"
        }
        """)
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body("""
        {
            "roomNumber":1,
            "roomToken":111123,
            "systemMessage":"Dear: thaufan iqbal Please keep this token secure, and perform check-in on the date specified: 2023-08-11"}
        }
        """ )
    }
}


package contracts.controller.admin

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod

Contract.make {
    description "Get all Customer"
    request {
        url "/admin/get-customers"
        method HttpMethod.GET.toString()
        body([])
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body(""" [
    {
        "id": 1,
        "customerName": "thaufan iqbal",
        "customerGender": "pria",
        "customerAddress": "tdp d/19",
        "customerPhoneNumber": "895340003300",
        "customerBirthDate": "2005-03-14",
        "idCardNumber": "32760899113218"
    }
]
        """ )
    }
}






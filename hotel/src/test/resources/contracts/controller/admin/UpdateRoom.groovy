package contracts.controller.admin

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod

int idRoom = 50;
Contract.make {

    description "Remove Room"
    request {
        url "/admin/update-room/${idRoom}"
        method HttpMethod.PUT.toString()
        body("""
        {
        "roomNumber": 131,
        "roomType": "Deluxe"
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
        "id": ${idRoom},
        "roomNumber": 131,
        "roomType": "Deluxe"
}
        """ )
    }
}






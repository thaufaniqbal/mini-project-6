package contracts.controller.general

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod

Contract.make {
    description "Get all Rooms"
    request {
        url "/available-rooms"
        method HttpMethod.GET.toString()
        body("""
        {
            "checkInDate": "2023-07-11",
            "checkOutDate": "2023-07-12"
        }
        """)
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body( """
            [
    {
        "id": 1,
        "roomNumber": 1,
        "roomType": "Deluxe"
    },
    {
        "id": 3,
        "roomNumber": 2,
        "roomType": "Deluxe"
    },
    {
        "id": 7,
        "roomNumber": 3,
        "roomType": "Deluxe"
    },
    {
        "id": 9,
        "roomNumber": 4,
        "roomType": "Standart"
    },
    {
        "id": 16,
        "roomNumber": 100,
        "roomType": "Deluxe"
    },
    {
        "id": 18,
        "roomNumber": 7,
        "roomType": "Supperior"
    },
    {
        "id": 48,
        "roomNumber": 9,
        "roomType": "Deluxe"
    },
    {
        "id": 50,
        "roomNumber": 9,
        "roomType": "Deluxe"
    }
]
        """  )
    }
}


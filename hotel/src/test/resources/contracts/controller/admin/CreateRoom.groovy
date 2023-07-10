//package contracts.controller.admin
//
//import org.springframework.cloud.contract.spec.Contract
//import org.springframework.http.HttpMethod
//
//int idRoom = 84;
//Contract.make {
//
//    description "Get all Customer"
//    request {
//        url "/admin/create-room/"
//        method HttpMethod.POST.toString()
//        body("""
//        {
//        "roomNumber": 10,
//        "roomType": "Deluxe"
//        }
//        """)
//        headers {
//            header('Content-Type', 'application/json;charset=UTF-8')
//        }
//    }
//    response {
//        status 200
//        body("""
//        {
//        "id": ${idRoom} ,
//        "roomNumber": 10,
//        "roomType": "Deluxe"
//        }
//        """ )
//    }
//}
//

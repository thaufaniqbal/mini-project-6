//package contracts.controller.admin
//
//import org.springframework.cloud.contract.spec.Contract
//import org.springframework.http.HttpMethod
//
//int idRoom = 84;
//Contract.make {
//
//    description "Remove Room"
//    request {
//        url "/admin/remove-room/${idRoom}"
//        method HttpMethod.PUT.toString()
//        body([])
//        headers {
//            header('Content-Type', 'application/json;charset=UTF-8')
//        }
//    }
//    response {
//        status 200
//        body("remove success" )
//    }
//}
//
//
//
//
//

package org.man.bootkotlin.controller

import org.man.bootkotlin.config.getLogger
import org.man.bootkotlin.model.ApplicationInfo
import org.man.bootkotlin.model.ResponseModel
import org.man.bootkotlin.repository.ApplicationRepository
import org.man.bootkotlin.service.BootKotlinMockService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApplicationController (val applicationRepository: ApplicationRepository,
                             val bootKotlinMockService: BootKotlinMockService) {

    private val log = getLogger { }

    @GetMapping("/v1/application")
    fun getEmployee(): ResponseEntity<ResponseModel<List<ApplicationInfo>>> {
        log.debug("Incoming request on getEmployee")
        val responseModel = ResponseModel<List<ApplicationInfo>>(1000)
        responseModel.dataObj = applicationRepository.getListOfApplication()
        return ResponseEntity.ok(responseModel)
    }

    @GetMapping("/v1/poc/call")
    fun getPocCall(): ResponseEntity<ResponseModel<String>> {
        println("Incoming request on poc call")
        val responseModel = ResponseModel<String>(1000)
        responseModel.dataObj = bootKotlinMockService.getBootKotlinMockV1()
        return ResponseEntity.ok(responseModel)
    }

    @GetMapping("/v2/poc/call")
    fun getPocCallV2(): ResponseEntity<ResponseModel<String>> {
        println("Incoming request on poc call")
        val responseModel = ResponseModel<String>(1000)
        responseModel.dataObj = bootKotlinMockService.getBootKotlinMockV1WithRestV2()
        return ResponseEntity.ok(responseModel)
    }

    @GetMapping("/v3/poc/call")
    fun getPocCallV3(): ResponseEntity<ResponseModel<String>> {
        println("Incoming request on poc call")
        val responseModel = ResponseModel<String>(1000)
        responseModel.dataObj = bootKotlinMockService.getBootKotlinMockV1WithRestV2WithCircuitBreaker()
        return ResponseEntity.ok(responseModel)
    }

}
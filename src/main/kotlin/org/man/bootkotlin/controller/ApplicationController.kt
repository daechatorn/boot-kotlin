package org.man.bootkotlin.controller

import org.man.bootkotlin.config.getLogger
import org.man.bootkotlin.model.ResponseModel
import org.man.bootkotlin.model.jpa.ApplicationInfo
import org.man.bootkotlin.repository.ApplicationJpaRepository
import org.man.bootkotlin.repository.ApplicationRepository
import org.man.bootkotlin.service.BootKotlinMockService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApplicationController (val applicationRepository: ApplicationRepository,
                             val bootKotlinMockService: BootKotlinMockService,
                            val applicationJpaRepository: ApplicationJpaRepository
) {

    private val log = getLogger { }

    @GetMapping("/v1/application")
    fun getEmployee(): ResponseEntity<ResponseModel<List<ApplicationInfo>>> {
        log.info("Incoming request on getEmployee")
        log.debug("Incoming request on getEmployee debug")
        val responseModel = ResponseModel<List<ApplicationInfo>>(1000)
        //responseModel.dataObj = applicationRepository.getListOfApplication()
        responseModel.dataObj = applicationJpaRepository.findAll()
        return ResponseEntity.ok(responseModel)
    }

    @GetMapping("/v1/poc/call")
    fun getPocCall(): ResponseEntity<ResponseModel<String>> {
        log.info("Incoming request on poc call")
        val responseModel = ResponseModel<String>(1000)
        responseModel.dataObj = bootKotlinMockService.getBootKotlinMockV1()
        return ResponseEntity.ok(responseModel)
    }

    @GetMapping("/v2/poc/call")
    fun getPocCallV2(): ResponseEntity<ResponseModel<String>> {
        log.info("Incoming request on poc call")
        val responseModel = ResponseModel<String>(1000)
        responseModel.dataObj = bootKotlinMockService.getBootKotlinMockV1WithRestV2()
        return ResponseEntity.ok(responseModel)
    }

    @GetMapping("/v3/poc/call")
    fun getPocCallV3(): ResponseEntity<ResponseModel<String>> {
        log.info("Incoming request on poc call")
        val responseModel = ResponseModel<String>(1000)
        responseModel.dataObj = bootKotlinMockService.getBootKotlinMockV1WithRestV2WithCircuitBreaker()
        return ResponseEntity.ok(responseModel)
    }

}
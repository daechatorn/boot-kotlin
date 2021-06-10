package org.man.bootkotlin.controller

import org.man.bootkotlin.config.getLogger
import org.man.bootkotlin.model.ApplicationInfo
import org.man.bootkotlin.model.ResponseModel
import org.man.bootkotlin.repository.ApplicationRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApplicationController (val applicationRepository: ApplicationRepository) {

    private val log = getLogger { }

    @GetMapping("/v1/application")
    fun getEmployee(): ResponseEntity<ResponseModel<List<ApplicationInfo>>> {
        log.debug("Incoming request on getEmployee")
        val responseModel = ResponseModel<List<ApplicationInfo>>(1000)
        responseModel.dataObj = applicationRepository.getListOfApplication()
        return ResponseEntity.ok(responseModel)
    }

}
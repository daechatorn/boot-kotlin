package org.man.bootkotlin.controller

import org.man.bootkotlin.config.getLogger
import org.man.bootkotlin.model.Address
import org.man.bootkotlin.model.ResponseModel
import org.man.bootkotlin.model.jpa.UserInfoEntity
import org.man.bootkotlin.repository.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserInfoController (val userInfoJpaRepository: UserInfoJpaRepository) {

    private val log = getLogger { }

    @GetMapping("/v1/user")
    fun getUser(): ResponseEntity<ResponseModel<List<UserInfoEntity>>> {
        log.info("Incoming request on getEmployee")
        val responseModel = ResponseModel<List<UserInfoEntity>>(1000)
        //responseModel.dataObj = applicationRepository.getListOfApplication()
        responseModel.dataObj = userInfoJpaRepository.findAll()
        return ResponseEntity.ok(responseModel)
    }

    @PostMapping("/v1/user")
    fun postApplication(): ResponseEntity<ResponseModel<UserInfoEntity>> {
        log.info("Incoming request on postApplication")
        val responseModel = ResponseModel<UserInfoEntity>(1000)
        var userInfoRepo = UserInfoEntity(
            address = Address(
                moo = (Math.random()*1000).toInt().toString(),
                existingAddress = listOf(
                    Address(
                        moo = (Math.random()*1000).toInt().toString(),
                    ),
                    Address(
                        moo = (Math.random()*1000).toInt().toString(),
                    )
                )
            ),
            phoneNo = listOf("11111", "222222")
        )
        responseModel.dataObj = userInfoJpaRepository.save(userInfoRepo)
        return ResponseEntity.ok(responseModel)
    }

}
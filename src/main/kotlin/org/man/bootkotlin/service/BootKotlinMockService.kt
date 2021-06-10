package org.man.bootkotlin.service

import org.man.bootkotlin.model.ResponseModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class BootKotlinMockService(private val restTemplate: RestTemplate,
                            @Qualifier("restTemplateV2") private val restTemplateV2: RestTemplate) {

    fun getBootKotlinMockV1(): String {
        val uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8002/boot-kotlin-mock/api/v1/mock")
            .build()
        val request = RequestEntity<Any>(HttpMethod.GET, uri.toUri())
        val response = restTemplate.exchange(request, object : ParameterizedTypeReference<ResponseModel<String>>() {})
            .body ?: error("Content response body can not be null")
        return response.dataObj
    }

    fun getBootKotlinMockV1WithRestV2(): String {
        val uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8002/boot-kotlin-mock/api/v1/mock")
            .build()
        val request = RequestEntity<Any>(HttpMethod.GET, uri.toUri())
        val response = restTemplateV2.exchange(request, object : ParameterizedTypeReference<ResponseModel<String>>() {})
            .body ?: error("Content response body can not be null")
        return response.dataObj
    }

}
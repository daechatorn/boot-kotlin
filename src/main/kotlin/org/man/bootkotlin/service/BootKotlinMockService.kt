package org.man.bootkotlin.service

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty
import org.man.bootkotlin.model.MockDataV1
import org.man.bootkotlin.model.MockDataV2
import org.man.bootkotlin.model.ResponseModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
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

    fun getBootKotlinMockV1Data(): MockDataV1 {
        val uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8002/boot-kotlin-mock/api/v1/mock/data")
            .build()
        val request = RequestEntity<Any>(HttpMethod.GET, uri.toUri())
        val response = restTemplate.exchange(request, object : ParameterizedTypeReference<ResponseModel<MockDataV1>>() {})
            .body ?: error("Content response body can not be null")
        return response.dataObj
    }

    fun getBootKotlinMockV2Data(): MockDataV2 {
        val uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8002/boot-kotlin-mock/api/v2/mock/data")
            .build()
        val request = RequestEntity<Any>(HttpMethod.GET, uri.toUri())
        val response = restTemplate.exchange(request, object : ParameterizedTypeReference<ResponseModel<MockDataV2>>() {})
            .body ?: error("Content response body can not be null")
        return response.dataObj
    }

    @HystrixCommand(
        //fallbackMethod = "fooMethodFallback",
        commandProperties = [
            HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
        ]
    )
    fun getBootKotlinMockV1WithRestV2WithCircuitBreaker(): String {
        val uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8002/boot-kotlin-mock/api/v1/mock")
            .build()
        val request = RequestEntity<Any>(HttpMethod.GET, uri.toUri())
        val response = restTemplateV2.exchange(request, object : ParameterizedTypeReference<ResponseModel<String>>() {})
            .body ?: error("Content response body can not be null")
        return response.dataObj
    }

}
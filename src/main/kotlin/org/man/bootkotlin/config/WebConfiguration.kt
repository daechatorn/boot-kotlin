package org.man.bootkotlin.config

import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class WebConfiguration {

    @Bean
    fun objectMapper() = customObjectMapper()

    @Bean
    @Primary
    fun createRestTemplate(restTemplateProperties: RestTemplateProperties) = RestTemplate(getCustomHttpRequestFactory(restTemplateProperties))

    @Bean("restTemplateV2")
    fun restTemplateV2(restTemplatePropertiesV2: RestTemplatePropertiesOtherTimeout) =
        RestTemplate(getCustomHttpRequestFactoryV2(restTemplatePropertiesV2))

    private fun getCustomHttpRequestFactory(restTemplateProperties: RestTemplateProperties) = HttpComponentsClientHttpRequestFactory(getHttpClient(restTemplateProperties.connection))
            .apply {
                setConnectTimeout(restTemplateProperties.timeout.connection)
                setReadTimeout(restTemplateProperties.timeout.read)
                setConnectionRequestTimeout(restTemplateProperties.timeout.request)
            }

    private fun getHttpClient(connection: Connection): HttpClient {
        val connectionManager = PoolingHttpClientConnectionManager()
                .apply {
                    maxTotal = connection.maxTotal
                    defaultMaxPerRoute = connection.maxPerRoute
                }
        return HttpClientBuilder.create().setConnectionManager(connectionManager).build()
    }

    private fun getCustomHttpRequestFactoryV2(restTemplateProperties: RestTemplatePropertiesOtherTimeout) = HttpComponentsClientHttpRequestFactory(getHttpClientV2(restTemplateProperties.connection))
        .apply {
            setConnectTimeout(restTemplateProperties.timeout.connection)
            setReadTimeout(restTemplateProperties.timeout.read)
            setConnectionRequestTimeout(restTemplateProperties.timeout.request)
        }

    private fun getHttpClientV2(connection: ConnectionV2): HttpClient {
        val connectionManager = PoolingHttpClientConnectionManager()
            .apply {
                maxTotal = connection.maxTotal
                defaultMaxPerRoute = connection.maxPerRoute
            }
        return HttpClientBuilder.create().setConnectionManager(connectionManager).build()
    }

}
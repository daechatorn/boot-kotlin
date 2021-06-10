package org.man.bootkotlin

import org.man.bootkotlin.config.RestTemplateProperties
import org.man.bootkotlin.config.RestTemplatePropertiesOtherTimeout
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RestTemplateProperties::class, RestTemplatePropertiesOtherTimeout::class)
class BootKotlinApplication

fun main(args: Array<String>) {
	runApplication<BootKotlinApplication>(*args)
}

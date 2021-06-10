package org.man.bootkotlin.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "rest")
data class RestTemplateProperties(val timeout: Timeout, val connection: Connection)
data class Timeout(val connection: Int, val read: Int, val request: Int)
data class Connection(val maxPerRoute: Int, val maxTotal: Int)
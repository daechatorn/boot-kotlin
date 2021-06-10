package org.man.bootkotlin.config

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Timestamp
import java.time.Instant
import java.time.ZoneOffset

import java.time.ZonedDateTime


fun getLogger(c: () -> Unit): Logger = LoggerFactory.getLogger(c.javaClass.enclosingClass)

fun customObjectMapper(): ObjectMapper = jacksonObjectMapper()
        .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
        .registerModule(JavaTimeModule())
        //.registerModule(SimpleModule().addSerializer(ZonedDateTime::class.java, ZonedDateTimeSerializer(ISO_DATETIMEFORMATTER)))
        //.setTimeZone((TimeZone.getTimeZone(ZONE_ID)))
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

fun Any.asJsonString(): String = customObjectMapper()
        .writeValueAsString(this)

fun getDateTime(timestamp: Timestamp?): ZonedDateTime? {
        return if (timestamp != null) ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp.time), ZoneOffset.UTC
        ) else null
}
package org.man.bootkotlin.config

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Timestamp
import java.time.Instant
import java.time.ZoneOffset

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

const val ISO_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
const val ZONE_ID = "Asia/Bangkok"

val ISO_DATETIMEFORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(ISO_DATETIME_FORMAT)

fun getLogger(c: () -> Unit): Logger = LoggerFactory.getLogger(c.javaClass.enclosingClass)

fun customObjectMapper(): ObjectMapper = jacksonObjectMapper()
        .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
        .registerModule(JavaTimeModule())
        .registerModule(SimpleModule().addSerializer(ZonedDateTime::class.java, ZonedDateTimeSerializer(ISO_DATETIMEFORMATTER)))
        .setTimeZone((TimeZone.getTimeZone(ZONE_ID)))
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

fun Any.asJsonString(): String = customObjectMapper()
        .writeValueAsString(this)

fun getDateTime(timestamp: Timestamp?): ZonedDateTime? {
        return if (timestamp != null) ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp.time), ZoneOffset.UTC
        ) else null
}
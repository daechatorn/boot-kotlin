package org.man.bootkotlin.repository

import com.fasterxml.jackson.core.JsonProcessingException
import org.man.bootkotlin.config.customObjectMapper
import org.man.bootkotlin.config.getLogger
import org.man.bootkotlin.model.Address
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class AddressConverter : AttributeConverter<Address, String> {

    private val log = getLogger { }
    override fun convertToDatabaseColumn(obj: Address?): String? {
        return if (obj == null) {
            null
        } else try {
            customObjectMapper().writeValueAsString(obj)
        } catch (e: JsonProcessingException) {
            log.error("Could not convert Address to json string")
            null
        }
    }

    override fun convertToEntityAttribute(dbValue: String?): Address? {
        return if (dbValue == null) {
            null
        } else try {
            customObjectMapper().readValue(dbValue, Address::class.java)
        } catch (e: JsonProcessingException) {
            log.error("Could not convert json string to Address")
            null
        }
    }

}
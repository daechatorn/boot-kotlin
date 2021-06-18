package org.man.bootkotlin.model

import java.io.Serializable

data class Address(val moo: String,
                   val floor: String? = null,
                   val existingAddress: List<Address>? = null
) : Serializable

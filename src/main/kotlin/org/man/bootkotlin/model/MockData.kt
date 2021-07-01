package org.man.bootkotlin.model

import java.io.Serializable

data class MockDataV1(
    val firstName: String,
    val lastName: String
) : Serializable

data class MockDataV2(
    val customerId: Int,
    val customerName: String
) : Serializable

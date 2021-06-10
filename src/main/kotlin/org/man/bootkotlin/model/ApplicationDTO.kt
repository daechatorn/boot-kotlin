package org.man.bootkotlin.model

import java.io.Serializable
import java.time.ZonedDateTime

data class ApplicationInfo(val applicationId: Int,
                           val thaiId: String,
                           val dob: String? = null,
                           val email: String? = null,
                           val emailVerified: Boolean? = false,
                           val createdDatetime: ZonedDateTime) : Serializable
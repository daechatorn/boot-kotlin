package org.man.bootkotlin.model.jpa

import org.springframework.data.annotation.CreatedDate
import java.io.Serializable
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name="application_info")
data class ApplicationInfo(
    @Id
    @Column(name = "application_id")
    val applicationId: String,
    @Column(name = "thai_id")
    val thaiId: Boolean,
    @Column(name = "dob")
    val dob: String? = null,
    @Column(name = "email")
    val email: String? = null,
    @Column(name = "email_verified")
    val emailVerified: Boolean? = null,
) : CreatedAt(), Serializable

open class CreatedAt(
    @CreatedDate @Column(name = "created_datetime") val createDatetime: ZonedDateTime? = ZonedDateTime.now()
) : Serializable
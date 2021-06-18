package org.man.bootkotlin.model.jpa

import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import org.man.bootkotlin.model.Address
import org.man.bootkotlin.repository.AddressConverter
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
    val thaiId: String,
    @Column(name = "dob")
    val dob: String? = null,
    @Column(name = "email")
    val email: String? = null,
    @Column(name = "email_verified")
    val emailVerified: Boolean? = null,
    @CreatedDate
    @Column(name = "created_datetime")
    val createDatetime: ZonedDateTime = ZonedDateTime.now()
) : Serializable

@Entity
@Table(name = "user_info")
@TypeDefs(TypeDef(name = "json", typeClass = JsonStringType::class))
data class UserInfoEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id") val userId: Int = 0,
    //@Convert(converter = AddressConverter::class) @Column(name = "address") val address: Address,
    //we can used com.vladmihalcea instead of converter
    @Type(type = "json") @Column(name = "address") val address: Address,
    @Type(type = "json") @Column(name = "phone_no") val phoneNo: List<String>,
) : Serializable
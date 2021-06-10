package org.man.bootkotlin.repository

import org.man.bootkotlin.model.ApplicationInfo
import org.man.bootkotlin.repository.mapper.applicationInfoMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ApplicationRepository(val jdbcTemplate: JdbcTemplate) {

    fun getListOfApplication(): List<ApplicationInfo>? {
        return jdbcTemplate.query("SELECT * FROM application_info", applicationInfoMapper)
    }

}
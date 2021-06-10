package org.man.bootkotlin.repository.mapper

import org.man.bootkotlin.config.getDateTime
import org.man.bootkotlin.model.ApplicationInfo
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

val applicationInfoMapper: RowMapper<ApplicationInfo> = RowMapper<ApplicationInfo> { resultSet: ResultSet, rowIndex: Int ->
    ApplicationInfo(resultSet.getInt("application_id"),
        resultSet.getString("thai_id"),
        resultSet.getString("dob"),
        resultSet.getString("email"),
        resultSet.getBoolean("email_verified"),
        getDateTime(resultSet.getTimestamp("created_datetime"))!!,
    )
}
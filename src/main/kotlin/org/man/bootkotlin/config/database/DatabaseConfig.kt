package org.man.bootkotlin.config.database

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
class DatabaseConfig {
    @Bean
    @ConfigurationProperties("spring.datasource")
    fun dataSource(): HikariDataSource {
        return DataSourceBuilder.create().build() as HikariDataSource
    }

    @Bean
    fun namedParameterJdbcTemplate(dataSource: DataSource?): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }
}
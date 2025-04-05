package com.budzko.dao

import com.budzko.dao.entity.LogEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import java.sql.Connection
import javax.sql.DataSource

class LogDao(private val connectionProvider: ConnectionProvider) {

    @Autowired
    private val dataSource: DataSource? = null

    fun save(logEntity: LogEntity) {
        val query = "insert into logs (id, data, create_time, level, user_id) values (?, ?, ?, ?, ?)"
        var conn: Connection? = null
        try {
            conn = connectionProvider.conn()
            val prepareStatement = conn.prepareStatement(query)
            var idx = 0;
            prepareStatement?.setString(++idx, logEntity.id)
            prepareStatement?.setString(++idx, logEntity.data)
            prepareStatement?.setTimestamp(++idx, logEntity.createTime)
            prepareStatement?.setString(++idx, logEntity.level)
            prepareStatement?.setString(++idx, logEntity.userId)
            prepareStatement?.executeUpdate()
            prepareStatement?.close()
//            println("Save $logEntity")
        } catch (e: RuntimeException) {
            logger.error(e.message, e)
        }
    }

    fun saveV2(logEntity: LogEntity) {
        val query = "insert into logs (id, data, create_time, level, user_id) values (?, ?, ?, ?, ?)"
        val jdbcTemplate = JdbcTemplate(dataSource!!)
        val args = arrayOf<Any>(logEntity.id, logEntity.data, logEntity.createTime, logEntity.level, logEntity.userId)
        jdbcTemplate.update(query, *args)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(LogDao::class.java)
    }
}

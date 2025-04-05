package com.budzko.dao

import com.budzko.dao.entity.UserEntity
import org.slf4j.LoggerFactory
import java.sql.Connection

class UserDao(private val connectionProvider: ConnectionProvider) {
    fun save(userEntity: UserEntity) {
        val query = "insert into users (id, name) values (?, ?)"
        var conn: Connection? = null
        try {
            conn = connectionProvider.conn()
            val prepareStatement = conn.prepareStatement(query)
            var idx = 0;
            prepareStatement?.setString(++idx, userEntity.id)
            prepareStatement?.setString(++idx, userEntity.name)
            prepareStatement?.executeUpdate()
            prepareStatement?.close()
        } catch (e: RuntimeException) {
            logger.error(e.message, e)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UserDao::class.java)
    }
}

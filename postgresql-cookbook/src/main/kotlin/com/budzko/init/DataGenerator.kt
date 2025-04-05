package com.budzko.init

import com.budzko.dao.ConnectionProvider
import com.budzko.dao.LogDao
import com.budzko.dao.UserDao
import com.budzko.dao.entity.LogEntity
import com.budzko.dao.entity.UserEntity
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import java.util.concurrent.Executors
import javax.sql.DataSource

@Component
class DataGenerator {

    @Autowired
    private val dataSource: DataSource? = null

    @PostConstruct
    fun generateData() {
        val threadCount = 80
        val threadPool = Executors.newFixedThreadPool(threadCount)
        for (i in 1..threadCount) {
            threadPool.submit {
                try {
                    generateLogs()
                } catch (e: RuntimeException) {
                    logger.error(e.message)
                }
            }
        }
        threadPool.close()
    }

    private fun generateLogs() {
        val connectionProvider = ConnectionProvider(dataSource!!)
        val userDao = UserDao(connectionProvider)
        val logDao = LogDao(connectionProvider)
        val start = System.currentTimeMillis()
        for (i in 0..logCount) {
            try {
                val userId = UUID.randomUUID().toString()
                val user = UserEntity(id = userId, name = System.nanoTime().toString())
                userDao.save(user)
                logDao.save(
                    LogEntity(
                        id = UUID.randomUUID().toString(),
                        data = UUID.randomUUID().toString(),
                        level = "info",
                        createTime = Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
                        userId = userId,
                    )
                )
            } catch (e: RuntimeException) {
                logger.error(e.message)
            }
        }
        val end = System.currentTimeMillis()
        println("${end - start} ms")
    }

    companion object {
        private const val logCount = 1000
        private val logger = LoggerFactory.getLogger(DataGenerator::class.java)

    }
}

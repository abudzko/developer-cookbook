package com.budzko.dao

import java.sql.Connection
import javax.sql.DataSource

class ConnectionProvider(private val dataSource: DataSource) {

    private val lock = Object()
    private var connection: Connection? = null

    fun conn(): Connection {
        if (connection == null || connection!!.isClosed) {
            synchronized(lock) {
                if (connection == null || connection!!.isClosed) {
                    connection = dataSource.connection
                }
            }
        }
        return connection!!
    }
}

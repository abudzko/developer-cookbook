package com.budzko.dao.entity

import java.sql.Timestamp

data class LogEntity(
    val id: String,
    val data: String,
    val createTime: Timestamp,
    val level: String,
    val userId: String
)
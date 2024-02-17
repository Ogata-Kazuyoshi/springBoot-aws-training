package com.example.backend_kotlin2

data class Memo(
    val id: Int = 0,
    val user_id: Int? =null,
    val create_date: Int? =null,
    val update_date: Int = 0,
    val content: String = ""
)


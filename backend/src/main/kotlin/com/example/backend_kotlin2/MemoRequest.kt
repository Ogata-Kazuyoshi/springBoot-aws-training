package com.example.backend_kotlin2

data class MemoRequest(
    val create_date: Int?,
    val update_date: Int,
    val content: String
)


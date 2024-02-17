package com.example.backend_kotlin2

import org.springframework.data.jpa.repository.JpaRepository

interface MemoRepository :JpaRepository<MemoEntity, Int>{
}
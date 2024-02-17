package com.example.backend_kotlin2

import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery
import java.util.*
import java.util.function.Function

class MemoRepositoryTestDouble: MemoRepository {
    var findAll_isCalled = false
    var findAll_returnValue = mutableListOf<MemoEntity>()
    var save_isCalled = false
    var save_returnValue = MemoEntity()
    var save_argument = MemoEntity()
    var findById_return_value = MemoEntity()
    var deleteById_isCalled = false
    var deleteById_argument = 0



    override fun <S : MemoEntity?> save(entity: S & Any): S & Any {
        save_isCalled = true
        save_argument = entity
        return save_returnValue as (S & Any)
    }

    override fun <S : MemoEntity?> saveAll(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : MemoEntity?> findAll(example: Example<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : MemoEntity?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<MemoEntity> {
        findAll_isCalled = true
        return findAll_returnValue
    }

    override fun findAll(sort: Sort): MutableList<MemoEntity> {
        TODO("Not yet implemented")
    }

    override fun findAll(pageable: Pageable): Page<MemoEntity> {
        TODO("Not yet implemented")
    }

    override fun <S : MemoEntity?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Int>): MutableList<MemoEntity> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun <S : MemoEntity?> count(example: Example<S>): Long {
        TODO("Not yet implemented")
    }


    override fun delete(entity: MemoEntity) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<MemoEntity>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun <S : MemoEntity?> findOne(example: Example<S>): Optional<S> {
        TODO("Not yet implemented")
    }

    override fun <S : MemoEntity?> exists(example: Example<S>): Boolean {
        TODO("Not yet implemented")
    }

    override fun <S : MemoEntity?, R : Any?> findBy(
        example: Example<S>,
        queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>
    ): R & Any {
        TODO("Not yet implemented")
    }

    override fun flush() {
        TODO("Not yet implemented")
    }

    override fun <S : MemoEntity?> saveAndFlush(entity: S & Any): S & Any {
        TODO("Not yet implemented")
    }

    override fun <S : MemoEntity?> saveAllAndFlush(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch(entities: MutableIterable<MemoEntity>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch() {
        TODO("Not yet implemented")
    }

    override fun deleteAllByIdInBatch(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun getReferenceById(id: Int): MemoEntity {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): MemoEntity {
        TODO("Not yet implemented")
    }

    override fun getOne(id: Int): MemoEntity {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
       deleteById_isCalled = true
        deleteById_argument = id
    }

    override fun existsById(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Optional<MemoEntity> {
        return Optional.of(findById_return_value)
    }


}
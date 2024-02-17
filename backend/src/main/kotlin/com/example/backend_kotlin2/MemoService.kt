package com.example.backend_kotlin2

import org.springframework.stereotype.Service

interface MemoService {
    fun getMemos ():List<Memo>
    fun postMemo (memo:Memo):Memo
    fun updateMemo (memo:Memo):Memo
    fun deleteMemo (id:Int)
}

@Service
class MemoServiceImpl(val repository:MemoRepository): MemoService{
    override fun getMemos(): List<Memo> {
        return repository.findAll().map{
            Memo(
                it.id,
                it.user_id,
                it.create_date,
                it.update_date,
                it.content
            )
        }
    }

    override fun postMemo(memo:Memo):Memo {
        val res = repository.save(
            MemoEntity(
                id = memo.id,
                user_id = memo.user_id?:0,
                create_date = memo.create_date?:0,
                update_date = memo.update_date,
                content = memo.content
            )
        )
        return Memo(
            id = res.id,
            user_id = res.user_id,
            create_date = res.create_date,
            update_date = res.update_date,
            content = res.content
        )
    }

    override fun updateMemo(memo:Memo):Memo {
        val findById_result = repository.findById(memo.id).get()
        val res = repository.save(MemoEntity(
            id = memo.id,
            user_id = findById_result.user_id,
            create_date = findById_result.create_date,
            update_date = memo.update_date,
            content = memo.content
        ))
        return Memo(
            id = res.id,
            user_id = res.user_id,
            create_date = res.create_date,
            update_date = res.update_date,
            content = res.content
        )
    }

    override fun deleteMemo(id:Int) {
        repository.deleteById(id)
    }
}




package com.example.backend_kotlin2
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MemoController (val memoService: MemoService) {

    @GetMapping("/v1/data")
    fun getMemo():List<Memo>{
        return memoService.getMemos()
    }

    @PostMapping("/v1/data/memo/{id}")
    fun postMemo(
        @PathVariable id:Int ,@RequestBody reqBody:MemoRequest
    ):Memo{
        return memoService.postMemo(Memo(
            id = id,
            user_id = 1,
            create_date = reqBody.create_date,
            update_date = reqBody.update_date,
            content = reqBody.content
        ))
    }

    @PatchMapping("/v1/data/memo/{id}")
    fun updateMemo(
        @PathVariable id:Int ,
        @RequestBody reqBody:MemoRequest
    ):Memo{
        return memoService.updateMemo(Memo(
            id =  id,
            update_date =  reqBody.update_date,
            content = reqBody.content
        ))
    }

    @DeleteMapping("/v1/data/{id}")
    fun deleteMemo(
        @PathVariable id:Int ,
    ){
        memoService.deleteMemo(id)
    }
}
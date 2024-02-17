package com.example.backend_kotlin2

class MemoServiceTestDouble:MemoService {
    var getMemos_isCalled = false
    var getMemos_return_value = listOf(Memo())
    var postMemo_isCalled = false
    var postMemo_return_value = Memo()
    var postMemo_argument = Memo()
    var updateMemo_isCalled = false
    var updateMemo_argument = Memo()
    var updateMemo_return_value = Memo()
    var deleteMemo_isCalled = false
    var deleteMemo_argument = 0

    override fun getMemos(): List<Memo> {
        getMemos_isCalled = true
        return getMemos_return_value
    }

    override fun postMemo(memo:Memo):Memo {
        postMemo_isCalled = true
        postMemo_argument = memo
        return postMemo_return_value
    }

    override fun updateMemo(memo: Memo):Memo {
        updateMemo_isCalled = true
        updateMemo_argument = memo
        return updateMemo_return_value
    }

    override fun deleteMemo(id:Int) {
        deleteMemo_isCalled = true
        deleteMemo_argument = id
    }
}
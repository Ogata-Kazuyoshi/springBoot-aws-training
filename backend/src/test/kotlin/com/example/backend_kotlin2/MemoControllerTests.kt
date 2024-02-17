package com.example.backend_kotlin2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class MemoControllerTests {
    private lateinit var controller: MemoController
    private lateinit var mockMvc : MockMvc

    val memoService = MemoServiceTestDouble()

    @BeforeEach
    fun setup(){
        controller = MemoController(memoService)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Nested
    inner class `getMemosTests`{
        @Test
        fun `it should return status code 200`(){
            mockMvc.perform(
                get("/api/v1/data")
            )
                .andExpect(status().isOk)
        }
        @Test
        fun `it should call MemoService`(){
            //given arrange


            //when act　
            mockMvc.perform(
                get("/api/v1/data")
            )

            //then
            assertThat(memoService.getMemos_isCalled).isTrue()
        }
        @Test
        fun `it should return list of Memo`(){
            //given arrange
            memoService.getMemos_return_value=listOf(Memo(
                id =1,
                user_id =2,
                create_date =100,
                update_date =200,
                content ="これはテストダブルです"
            ))

            //when act　
            mockMvc.perform(
                get("/api/v1/data")
            )

                //then
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user_id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].create_date").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].update_date").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("これはテストダブルです"))
        }
    }

    @Nested
    inner class `postMemoTests`{
        @Test
        fun `it should return status code 200`(){
            mockMvc.perform(
                post("/api/v1/data/memo/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                            {
                                "create_date": 1111,
                                "update_date": 2222,
                                "content": "sample content"
                            }
                        """
                    )
            )
                .andExpect(status().isOk)
        }
        @Test
        fun `it should call memoService_postMemo() with correct argument`(){
            //given arrange

            //when act　
            mockMvc.perform(
                post("/api/v1/data/memo/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                            {
                                "create_date": 1111,
                                "update_date": 2222,
                                "content": "sample content"
                            }
                        """
                    )
            )

            //then
            assertThat(memoService.postMemo_isCalled).isTrue()
            assertEquals(Memo(
                id=1,
                user_id=1,
                create_date=1111,
                update_date=2222,
                content= "sample content"
                ),memoService.postMemo_argument)
        }
        @Test
        fun `it should return Memo`(){
            //given arrange
            memoService.postMemo_return_value = Memo(
                id = 10,
                user_id = 20,
                create_date = 1000,
                update_date = 2000,
                content = "これはテストダブル2です"
            )

            //when act　
            mockMvc.perform(
                post("/api/v1/data/memo/10")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                            {
                                "create_date": 1111,
                                "update_date": 2222,
                                "content": "sample content"
                            }
                        """
                    )
            )

                //then
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user_id").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.create_date").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.update_date").value(2000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("これはテストダブル2です"))
        }
    }

    @Nested
    inner class `PatchTests` {

        @Test
        fun `it should return status code 200`(){
            mockMvc.perform(
                patch("/api/v1/data/memo/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                            {
                                "update_date": 2222,
                                "content": "sample content"
                            }
                        """
                    )
            )
                .andExpect(status().isOk)
        }

        @Test
        fun `it should call memoservice updatememo with correct argument`(){
            mockMvc.perform(
                patch("/api/v1/data/memo/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                            {
                                "update_date": 2222,
                                "content": "sample content"
                            }
                        """
                    )
            )

            assertThat(memoService.updateMemo_isCalled).isTrue()
            assertEquals(Memo(
                id = 1,
                update_date = 2222,
                content = "sample content"
            ),memoService.updateMemo_argument )
        }

        @Test
        fun `it should return list of Memo`(){
            //given arrange
            memoService.updateMemo_return_value=Memo(
                id =1,
                update_date =2222,
                content ="sample content"
            )

            //when act　
            mockMvc.perform(
                patch("/api/v1/data/memo/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                            {
                                "update_date": 2222,
                                "content": "sample content"
                            }
                        """
                    )
            )

                //then
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.update_date").value(2222))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("sample content"))
        }
    }

    @Nested
    inner class `deleteTests` {
        @Test
        fun `it should return status code 200`(){
            mockMvc.perform(
                delete("/api/v1/data/2")
            )
                .andExpect(status().isOk)
        }

        @Test
        fun `it should call MemoService delete with correct argument`() {
            //given arrange


            //when act　
            mockMvc.perform(
                delete("/api/v1/data/2")
            )

            //then
            assertThat(memoService.deleteMemo_isCalled).isTrue()
            assertEquals(2,memoService.deleteMemo_argument)
        }
    }
}
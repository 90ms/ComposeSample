package k90ms.compose.remote.api

import k90ms.compose.remote.model.OpenAI
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAIService {

    @POST("v1/completions")
    suspend fun getQuery(
        @Body request: OpenAI.RQ
    ): OpenAI.RS

}
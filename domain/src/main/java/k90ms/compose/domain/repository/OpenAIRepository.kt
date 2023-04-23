package k90ms.compose.domain.repository

interface OpenAIRepository {

    suspend fun requestValue(value: String)
}
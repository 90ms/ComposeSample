package k90ms.compose.repository

import k90ms.compose.domain.repository.OpenAIRepository
import k90ms.compose.remote.api.OpenAIService
import k90ms.compose.remote.model.OpenAI
import javax.inject.Inject

class OpenAiRepositoryImpl @Inject constructor(
    private val api: OpenAIService
) : OpenAIRepository {
    override suspend fun requestValue(value: String) {
        api.getQuery(
            OpenAI.RQ(
                value,
            )
        )
    }
}
package k90ms.compose.domain.usecase

import k90ms.compose.domain.repository.OpenAIRepository
import javax.inject.Inject

class OpenAIUseCase @Inject constructor(
    private val openAIRepository: OpenAIRepository
) {

    suspend operator fun invoke(value: String) = openAIRepository.requestValue(value)
}
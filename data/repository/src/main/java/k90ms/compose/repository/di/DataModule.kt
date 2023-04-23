package k90ms.compose.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import k90ms.compose.domain.repository.OpenAIRepository
import k90ms.compose.repository.OpenAiRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindOpenAIRepository(
        repository: OpenAiRepositoryImpl,
    ): OpenAIRepository
}
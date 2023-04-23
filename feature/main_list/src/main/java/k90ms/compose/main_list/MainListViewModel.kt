package k90ms.compose.main_list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import k90ms.compose.design.base.BaseViewModel
import k90ms.compose.domain.usecase.OpenAIUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val openAIUseCase: OpenAIUseCase
) : BaseViewModel(), MainListContract {

    private val mutableState = MutableStateFlow(MainListContract.State())
    override val state: StateFlow<MainListContract.State> = mutableState.asStateFlow()

    override fun event(event: MainListContract.Event) {
        when (event) {
            MainListContract.Event.OnRefresh -> TODO()
            is MainListContract.Event.OnGetList -> {
                getData()
            }

            is MainListContract.Event.OnSetShowFavoriteList -> {

            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
            openAIUseCase("요청 들어갔니?")
        }
    }
}
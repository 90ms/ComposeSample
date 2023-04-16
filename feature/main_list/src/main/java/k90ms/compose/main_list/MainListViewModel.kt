package k90ms.compose.main_list

import dagger.hilt.android.lifecycle.HiltViewModel
import k90ms.compose.design.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(

): BaseViewModel(), MainListContract{

    private val mutableState = MutableStateFlow(MainListContract.State())
    override val state: StateFlow<MainListContract.State> = mutableState.asStateFlow()

    override fun event(event: MainListContract.Event) {

    }
}
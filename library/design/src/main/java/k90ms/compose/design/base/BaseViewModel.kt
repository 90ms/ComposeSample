package k90ms.compose.design.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class BaseViewModel : ViewModel(), BaseContract {

    protected val mutableBaseState: MutableStateFlow<BaseContract.BaseState> =
        MutableStateFlow(BaseContract.BaseState.OnSuccess)
    override val baseState: StateFlow<BaseContract.BaseState> get() = mutableBaseState

    protected val baseEffectChannel = Channel<BaseContract.BaseEffect>(Channel.UNLIMITED)
    override val baseEffect: Flow<BaseContract.BaseEffect> = baseEffectChannel.receiveAsFlow()

    override fun baseEvent(event: BaseContract.BaseEvent) = when(event) {
        BaseContract.BaseEvent.OnBackPressed -> onBackPressed()
        BaseContract.BaseEvent.OnRetryPressed -> onRetryPressed()
    }

    private fun onBackPressed() {
        baseEffectChannel.trySend(BaseContract.BaseEffect.OnBackPressed)
    }

    private fun onRetryPressed() {}

}
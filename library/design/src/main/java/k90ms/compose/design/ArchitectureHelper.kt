package k90ms.compose.design

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

interface BaseUnidirectionalViewModel<BASE_EVENT, BASE_EFFECT, BASE_STATE> {
    val baseState: StateFlow<BASE_STATE>
    val baseEffect: Flow<BASE_EFFECT>

    fun baseEvent(event: BASE_EVENT)
}

@Composable
inline fun <reified BASE_EVENT, BASE_EFFECT, BASE_STATE> useBase(
    viewModel: BaseUnidirectionalViewModel<BASE_EVENT, BASE_EFFECT, BASE_STATE>,
): StateEffectDispatch<BASE_EVENT, BASE_EFFECT, BASE_STATE> {

    val state by viewModel.baseState.collectAsStateWithLifecycle()

    val dispatch: (BASE_EVENT) -> Unit = { event->
        viewModel.baseEvent(event)
    }

    return StateEffectDispatch(
        state = state,
        effectFlow = viewModel.baseEffect,
        dispatch = dispatch
    )
}

data class StateEffectDispatch<EVENT, EFFECT, STATE>(
    val state: STATE,
    val effectFlow: Flow<EFFECT>,
    val dispatch: (EVENT) -> Unit,
)

data class StateDispatch<EVENT, STATE>(
    val state: STATE,
    val dispatch: (EVENT) -> Unit,
)

@Composable
fun <T>Flow<T>.collectInLaunchedEffect(function: suspend (value: T) -> Unit ){
    val flow = this
    LaunchedEffect(key1 = flow) {
        flow.collectLatest(function)
    }
}

interface UnidirectionalViewModel<EVENT, STATE> {
    val state: StateFlow<STATE>
    fun event(event: EVENT)
}

@Composable
inline fun <reified EVENT, STATE> use(
    viewModel: UnidirectionalViewModel<EVENT, STATE>
): StateDispatch<EVENT, STATE> {
    val state by viewModel.state.collectAsState()

    val dispatch: (EVENT) -> Unit = {
        viewModel.event(it)
    }

    return StateDispatch(state, dispatch)
}
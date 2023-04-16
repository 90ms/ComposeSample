package k90ms.compose.design.base

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import k90ms.compose.design.collectInLaunchedEffect
import k90ms.compose.design.useBase

@Composable
fun BaseRoute(
    baseViewModel: BaseViewModel,
    content: @Composable () -> Unit,
) {

    val (baseState, baseEffect, baseEvent) = useBase(viewModel = baseViewModel)

    val context = LocalContext.current
    val activity = content as? Activity

    baseEffect.collectInLaunchedEffect {
        when(it) {
            BaseContract.BaseEffect.OnBackPressed -> {
                activity?.onBackPressed()
            }
        }
    }

    BaseScreen(
        baseState = baseState,
        content = content,
    )
}


@Composable
private fun BaseScreen(
    baseState: BaseContract.BaseState,
    content: @Composable () -> Unit,
) {

    Box(modifier = Modifier.fillMaxSize()) {
        when(baseState) {
            BaseContract.BaseState.OnSuccess -> content()
        }
    }
}
package k90ms.compose.main_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import k90ms.compose.design.base.BaseViewModel
import k90ms.compose.design.use
import k90ms.compose.domain.ItemData
import k90ms.compose.main_list.component.MainListItem

@Composable
fun MainListRoute(
    viewModel: MainListViewModel = hiltViewModel(),
    showFavoriteList: Boolean = false,
    onNavigateToDetailScreen: (data: ItemData) -> Unit,
    onProvideBaseViewModel: (baseViewModel: BaseViewModel) -> Unit
) {

    val (state, event) = use(viewModel)

    LaunchedEffect(key1 = Unit) {
        onProvideBaseViewModel(viewModel)
        event.invoke(
            MainListContract.Event.OnSetShowFavoriteList(showFavoriteList)
        )
        event.invoke(
            MainListContract.Event.OnGetList(showFavoriteList)
        )
    }

    MainListScreen(
        mainListState = state,
        onNavigateToDetailScreen = {

        },
        onFavoriteClick = {

        },
        onRefresh = {

        }
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainListScreen(
    mainListState: MainListContract.State,
    onNavigateToDetailScreen: (data: ItemData) -> Unit,
    onFavoriteClick: (data: ItemData) -> Unit,
    onRefresh: () -> Unit
) {
    val refreshState = rememberPullRefreshState(
        refreshing = mainListState.refreshing,
        onRefresh = onRefresh
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pullRefresh(refreshState)
    ) {
        AnimatedVisibility(
            visible = !mainListState.refreshing,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(mainListState.data) { data ->
                    MainListItem(
                        data = data,
                        onItemClick = { onNavigateToDetailScreen(data) },
                        onFavoriteClick = { onFavoriteClick(data) }
                    )
                }
            }
            PullRefreshIndicator(
                refreshing = mainListState.refreshing,
                state = refreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }

}
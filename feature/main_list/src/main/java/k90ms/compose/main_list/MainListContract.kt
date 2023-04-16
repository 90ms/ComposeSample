package k90ms.compose.main_list

import k90ms.compose.design.UnidirectionalViewModel
import k90ms.compose.domain.ItemData

interface MainListContract :
    UnidirectionalViewModel<MainListContract.Event, MainListContract.State> {

    data class State(
        val data: List<ItemData> = emptyList(),
        val refreshing: Boolean = false,
        val showFavoriteList: Boolean = false
    )

    sealed class Event {
        object OnRefresh : Event()
        data class OnGetList(val showFavoriteList: Boolean) : Event()
        data class OnSetShowFavoriteList(val showFavoriteList: Boolean) : Event()
    }
}
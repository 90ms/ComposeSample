package k90ms.compose.design.base

import k90ms.compose.design.BaseUnidirectionalViewModel

interface BaseContract : BaseUnidirectionalViewModel<
    BaseContract.BaseEvent,
    BaseContract.BaseEffect,
    BaseContract.BaseState
    > {

    sealed class BaseState {
        object OnSuccess : BaseState()
    }

    sealed class BaseEffect {
        object OnBackPressed : BaseEffect()
    }

    sealed class BaseEvent {
        object OnBackPressed : BaseEvent()
        object OnRetryPressed : BaseEvent()
    }
}
package me.darthwithap.hotel_app.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class HotelAppViewModel<E: Event, S: State> : ViewModel() {
    abstract val state: StateFlow<S>

    abstract fun emitEvent(event: E)
}
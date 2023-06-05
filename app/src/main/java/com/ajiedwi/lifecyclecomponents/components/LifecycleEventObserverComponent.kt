package com.ajiedwi.lifecyclecomponents.components

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class LifecycleEventObserverComponent(
    private val ctx: Context,
    private val lifecycle: Lifecycle,
    private val callback: ((String) -> Unit),
): LifecycleEventObserver {

    companion object {
        private const val TAG = "Lifecycle Event Component Tag"
    }

    fun doSomeCalculation(){
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
            callback.invoke("$TAG : calculation success")
        }
        else callback.invoke("$TAG : calculation failed, lifecycle not surpassing CREATED state")
    }

    var enabled = false
        set(value) {
            if (value && !field) { // change from false to true
                field = value
                callback.invoke("$TAG : enabled success")
            }
            else if (!value && field) { // change from true to false
                field = value
                callback.invoke("$TAG : disabled")
            }
        }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_CREATE -> {
                callback.invoke("$TAG : entering state on_create")
            }
            Lifecycle.Event.ON_START -> {
                callback.invoke("$TAG : entering state on_start")
            }
            Lifecycle.Event.ON_RESUME -> {
                callback.invoke("$TAG : entering state on_resume")
                enabled = true
            }
            Lifecycle.Event.ON_PAUSE -> {
                callback.invoke("$TAG : entering state on_pause")
                enabled = false
            }
            Lifecycle.Event.ON_STOP -> {
                callback.invoke("$TAG : entering state on_stop")
            }
            Lifecycle.Event.ON_DESTROY -> {
                callback.invoke("$TAG : entering state on_destroy")
            }
            else -> Unit
        }
    }
}
package com.ajiedwi.lifecyclecomponents.components

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class DefaultLifecycleObserverComponent(
    private val ctx: Context,
    private val lifecycle: Lifecycle,
    private val callback: ((String) -> Unit),
): DefaultLifecycleObserver {

    companion object {
        private const val TAG = "Default Component Tag"
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

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        callback.invoke("$TAG : entering state on_create")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        callback.invoke("$TAG : entering state on_start")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        callback.invoke("$TAG : entering state on_resume")
        enabled = true
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        callback.invoke("$TAG : entering state on_pause")
        enabled = false
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        callback.invoke("$TAG : entering state on_stop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        callback.invoke("$TAG : entering state on_destroy")
    }

}
package com.ajiedwi.lifecyclecomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ajiedwi.lifecyclecomponents.components.DefaultLifecycleObserverComponent
import com.ajiedwi.lifecyclecomponents.components.LifecycleEventObserverComponent

class MainActivity : AppCompatActivity() {

    private lateinit var defaultLifecycleObserverComponent: LifecycleEventObserverComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        defaultLifecycleObserverComponent = LifecycleEventObserverComponent(
            ctx = this,
            lifecycle = lifecycle
        ){ str ->
            println(str)
        }

        lifecycle.addObserver(defaultLifecycleObserverComponent)
        defaultLifecycleObserverComponent.doSomeCalculation()
    }

    override fun onResume() {
        super.onResume()
        defaultLifecycleObserverComponent.doSomeCalculation()
    }
}
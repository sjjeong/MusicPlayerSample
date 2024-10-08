package com.dino.core.util

import android.app.Activity
import android.app.Application
import android.os.Bundle

class ActivityDestroyCallback(
    private val onActivityDestroyed: () -> Unit,
) : Application.ActivityLifecycleCallbacks {
    private var count = 0

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // no-op
        count++
    }

    override fun onActivityStarted(activity: Activity) {
        // no-op
    }

    override fun onActivityResumed(activity: Activity) {
        // no-op
    }

    override fun onActivityPaused(activity: Activity) {
        // no-op
    }

    override fun onActivityStopped(activity: Activity) {
        // no-op
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // no-op
    }

    override fun onActivityDestroyed(activity: Activity) {
        // no-op
        if (--count == 0) {
            onActivityDestroyed()
        }
    }
}

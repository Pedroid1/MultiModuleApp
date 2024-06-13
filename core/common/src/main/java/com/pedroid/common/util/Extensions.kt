package com.pedroid.common.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

fun String.capitalize() = this.replaceFirstChar { it.uppercase() }

fun Fragment.observeAndNavigateBack(liveData: LiveData<Boolean>) {
    liveData.observe(viewLifecycleOwner) { mustNavigateBack ->
        if (mustNavigateBack.handleOpt()) {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }
}
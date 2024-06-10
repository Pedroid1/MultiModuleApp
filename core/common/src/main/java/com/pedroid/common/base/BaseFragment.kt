package com.pedroid.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VDB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    protected lateinit var _binding: VDB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = (DataBindingUtil.inflate(inflater, layoutId, container, false) as VDB).apply {
        lifecycleOwner = viewLifecycleOwner
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObservers()
    }

    abstract fun setupViewModel()

    abstract fun setupObservers()

}
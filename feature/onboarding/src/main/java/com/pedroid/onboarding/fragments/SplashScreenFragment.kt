package com.pedroid.onboarding.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pedroid.feature.onboarding.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity())[OnboardingViewModel::class.java] }
    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.isUserOnboarded.observe(viewLifecycleOwner) { isOnboarded ->
            lifecycleScope.launch {
                delay(2000)
                if(isOnboarded) {
                    val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeNavGraph()
                    findNavController().navigate(action)
                    return@launch
                }
                val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToViewPagerFragment()
                findNavController().navigate(action)
            }
        }
    }
}
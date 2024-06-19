package com.pedroid.onboarding.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pedroid.common.preferences.PreferencesManager
import com.pedroid.feature.onboarding.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    @Inject
    lateinit var preferencesManager: PreferencesManager
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
        lifecycleScope.launch {
            delay(2000)
            if(preferencesManager.isOnboardingCompleted) {
                val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeNavGraph()
                findNavController().navigate(action)
                return@launch
            }
            val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToViewPagerFragment()
            findNavController().navigate(action)
        }
    }
}
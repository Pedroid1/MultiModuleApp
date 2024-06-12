package com.pedroid.onboarding.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pedroid.data.repository.PrefsRepository
import com.pedroid.feature.onboarding.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    @Inject lateinit var prefsRepository: PrefsRepository
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
        val isOnboardingCompleted = prefsRepository.isUserOnboarded()

        lifecycleScope.launch {
            delay(2000)
            if(isOnboardingCompleted) {
                // TODO Go to home screen
                Toast.makeText(requireContext(), "Onboarding completed", Toast.LENGTH_LONG).show()
                return@launch
            }
            val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToViewPagerFragment()
            findNavController().navigate(action)
        }
    }
}
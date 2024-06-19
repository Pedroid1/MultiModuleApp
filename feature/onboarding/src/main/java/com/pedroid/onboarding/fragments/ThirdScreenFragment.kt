package com.pedroid.onboarding.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.pedroid.common.preferences.PreferencesManager
import com.pedroid.communicator.HomeFeatureCommunicator
import com.pedroid.feature.onboarding.R
import com.pedroid.feature.onboarding.databinding.FragmentThirdScreenBinding
import com.pedroid.onboarding.navigation.OnboardingNavigationNode
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ThirdScreenFragment : Fragment() {

    @Inject
    lateinit var preferencesManager: PreferencesManager
    @Inject
    lateinit var homeFeatureCommunicator: HomeFeatureCommunicator
    private lateinit var binding: FragmentThirdScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.finishBtn.setOnClickListener {
            preferencesManager.isOnboardingCompleted = true
            homeFeatureCommunicator.launchFeature(HomeFeatureCommunicator.HomeFeatureArgs(OnboardingNavigationNode.ROUTE))
        }
        binding.previusBtn.setOnClickListener {
            activity?.findViewById<ViewPager2>(R.id.viewPager)?.apply {
                currentItem = --currentItem
            }
        }
    }
}
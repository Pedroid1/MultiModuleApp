package com.pedroid.onboarding.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.pedroid.data.repository.PrefsRepository
import com.pedroid.feature.onboarding.R
import com.pedroid.feature.onboarding.databinding.FragmentThirdScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ThirdScreenFragment : Fragment() {

    @Inject
    lateinit var prefsRepository: PrefsRepository
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
        binding.nextBtn.setOnClickListener {
            prefsRepository.setUserOnboarded(true)
            // TODO Go to home activity
        }
        binding.previusBtn.setOnClickListener {
            activity?.findViewById<ViewPager2>(R.id.viewPager)?.apply {
                currentItem = --currentItem
            }
        }
    }
}
package com.frank.practicehilt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frank.practicehilt.R
import com.frank.practicehilt.databinding.FragmentHomeBinding
import com.frank.practicehilt.ui.jsonplaceholder.JsonPlaceHolderFragment
import com.frank.practicehilt.ui.stackoverflow.StackOverFlowFragment

class HomeFragment : Fragment() {

    private lateinit var dataBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentHomeBinding.inflate(inflater)

        dataBinding.btnStackOverFlow.setOnClickListener {
            openScreen(StackOverFlowFragment())
        }

        dataBinding.btnJsonPlaceHolder.setOnClickListener {
            openScreen(JsonPlaceHolderFragment())
        }

        return dataBinding.root
    }

    private fun openScreen(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(fragment.javaClass.name).commit()
    }

}
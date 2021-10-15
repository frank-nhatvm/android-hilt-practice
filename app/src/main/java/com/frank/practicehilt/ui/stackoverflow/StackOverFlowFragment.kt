package com.frank.practicehilt.ui.stackoverflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frank.practicehilt.databinding.FragmentStackOverFlowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StackOverFlowFragment : Fragment() {


    private val stackOverflowViewModel by viewModels<StackOverFlowViewModel>()

    private lateinit var dataBinding: FragmentStackOverFlowBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stackOverflowViewModel.fetchData()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentStackOverFlowBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = stackOverflowViewModel


        stackOverflowViewModel.listQuestions.observe(viewLifecycleOwner, { list ->
            list.firstOrNull()?.let { question ->
                dataBinding.tvResult.text = question.toString()
            }
        })


        return dataBinding.root
    }


}
package com.frank.practicehilt.ui.stackoverflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frank.practicehilt.databinding.FragmentStackOverFlowBinding

class StackOverFlowFragment : Fragment() {

    private val viewModel by viewModels<StackOverFlowViewModel>()
    private lateinit var dataBinding: FragmentStackOverFlowBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentStackOverFlowBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = viewModel


        viewModel.listQuestions.observe(viewLifecycleOwner,{
            list ->
            list.firstOrNull()?.let { question ->
                dataBinding.tvResult.text = question.toString()
            }
        })


        return dataBinding.root
    }





}
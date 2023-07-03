package com.example.xoso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.xoso.databinding.FragmentHomeBinding

class Fragment_Home : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment__home,container,false
        )
        binding.ketQua.setOnClickListener { view: View ->

            Navigation.findNavController(view).navigate(R.id.action_fragment_Home_to_ketQua)
        }
        return binding.root
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.fragment__home, container, false)
    }
}
package com.example.xoso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.xoso.databinding.FragmentHomeBinding

class Fragment_Home : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val updateTime = System.currentTimeMillis()

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment__home,container,false
        )
        binding.ketQua.homeItemTextview.text = "kết quả"
        binding.ketQua.homeItem.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(Fragment_HomeDirections.actionFragmentHomeToKhungXoSoMB())
        }
        binding.homnay.homeItemTextview.text = "Hôm nay"
        binding.homnay.homeItem.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(Fragment_HomeDirections.actionFragmentHomeToHomNay())
        }
        binding.ketQuaDai.homeItemTextview.text = "Kết quả đài"
        binding.ketQuaDai.homeItem.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(Fragment_HomeDirections.actionFragmentHomeToFragmentKetQuaDai())
        }
        binding.tuongThuat.homeItemTextview.text = "Tường thuật"
        binding.tuongThuat.homeItem.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(Fragment_HomeDirections.actionFragmentHomeToKhungXoSoMB())
        }
        binding.soMo.homeItemTextview.text = "Sổ Mơ"
        binding.soMo.homeItem.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(Fragment_HomeDirections.actionFragmentHomeToSoMo3())
        }
        binding.vietLott.homeItem.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(Fragment_HomeDirections.actionFragmentHomeToKhungXoSoMN())
        }
        return binding.root
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.fragment__home, container, false)
    }
}
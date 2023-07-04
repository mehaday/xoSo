package com.example.xoso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.xoso.databinding.FragmentKetQuaDaiBinding

class ket_qua_dai : Fragment() {
    lateinit var adapterHomNay : adapter_hom_nay
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding : FragmentKetQuaDaiBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_ket_qua_dai, container,false
        )
        var list = mutableListOf<list_hom_nay>()
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Bình Định","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Đà Nẵng","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Đắk Lắk","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Đắk Nông","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Gia Lai","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Huế","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Khánh Hoà","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Kon Tum","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ Ninh Thuận","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Phú Yên","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Quảng Bình","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Quảng Nam","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Quảng Ngãi","Mở thưởng ngày 29/6/2023"))
        list.add(list_hom_nay(R.drawable.icon_ketquadai,"Xổ số Quảng Trị","Mở thưởng ngày 29/6/2023"))

        adapterHomNay = adapter_hom_nay(requireActivity(),list)
        binding.itemKetQuaDai.adapter = adapterHomNay
        // Inflate the layout for this fragment
        return binding.root
    }
}
package com.example.xoso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.xoso.databinding.FragmentHomNayBinding

class homNay : Fragment() {
    lateinit var adapterHomNay: adapter_hom_nay
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomNayBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_hom_nay,container,false
        )
        var list = mutableListOf<list_hom_nay>()
        list.add(list_hom_nay(R.drawable.icon_homnay_item,"Xổ số Miền Bắc","Mở thưởng lúc 18h15p","mien-bac"))
        list.add(list_hom_nay(R.drawable.icon_homnay_item,"Xổ số Bạc Liêu","Mở thưởng lúc 16h15p","bac-lieu"))
        list.add(list_hom_nay(R.drawable.icon_homnay_item,"Xổ số Bến Tre","Mở thưởng lúc 16h15p","ben-tre"))
        list.add(list_hom_nay(R.drawable.icon_homnay_item,"Xổ số Vũng Tàu","Mở thưởng lúc 16h15p","vung-tau"))
        list.add(list_hom_nay(R.drawable.icon_homnay_item,"Xổ số Đắk Lắk","Mở thưởng lúc 17h15p","dak-lak"))
        list.add(list_hom_nay(R.drawable.icon_homnay_item,"Xổ số Quảng Nam","Mở thưởng lúc 17h15p","quang-nam"))

        adapterHomNay = adapter_hom_nay(requireActivity(),list)

        binding.itemHomnay.adapter = adapterHomNay


        binding.itemHomnay.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            if (i == 0) {
                Toast.makeText(requireContext(), "Bạn chọn ${list[i].title}", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(homNayDirections.actionHomNayToKhungXoSoMB(list[i].key,list[i].title))
            }else {
                Toast.makeText(requireContext(), "Bạn chọn ${list[i].title}", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(homNayDirections.actionHomNayToKhungXoSoMN(list[i].key,list[i].title))
            }

        }
        return binding.root
    }
}
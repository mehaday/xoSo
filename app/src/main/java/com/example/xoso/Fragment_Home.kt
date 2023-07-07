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

        binding.thongKe.homeItemTextview.text = "Thống kê"
        binding.thongKe.homeItemImg.setImageResource(R.drawable.thong_ke)
        binding.TKVitri.homeItemTextview.text = "TK Vị trí"
        binding.TKVitri.homeItemImg.setImageResource(R.drawable.tk_vi_tri)
        binding.loGan.homeItemTextview.text = "Lô gan"
        binding.loGan.homeItemImg.setImageResource(R.drawable.lo_gan)
        binding.traCuu.homeItemTextview.text = "Tra cứu"
        binding.traCuu.homeItemImg.setImageResource(R.drawable.tim_kiem)
        binding.phanTich.homeItemTextview.text = "Phân tích"
        binding.phanTich.homeItemImg.setImageResource(R.drawable.phan_tich)
        binding.TKKeno.homeItemTextview.text = "T.Kê Keno"
        binding.TKKeno.homeItemImg.setImageResource(R.drawable.thong_ke_keno)
        binding.TKMega.homeItemTextview.text = "T.Kê Mega"
        binding.TKMega.homeItemImg.setImageResource(R.drawable.thong_ke_mega)
        binding.TKloto.homeItemTextview.text = "TK Loto"
        binding.TKloto.homeItemImg.setImageResource(R.drawable.tk_loto)
        binding.nguHanh.homeItemTextview.text = "Ngũ hành"
        binding.nguHanh.homeItemImg.setImageResource(R.drawable.ngu_hanh)
        binding.vietLott.homeItemTextview.text = "Vietlott"
        binding.vietLott.homeItemImg.setImageResource(R.drawable.tk_loto)


        binding.ketQua.homeItemTextview.text = "kết quả"
        binding.ketQua.homeItemImg.setImageResource(R.drawable.ic_ket_qua)
        binding.ketQua.homeItem.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(Fragment_HomeDirections.actionFragmentHomeToKhungXoSoMB("mien-bac","Xổ số Miền Bắc"))
        }
        binding.homnay.homeItemTextview.text = "Hôm nay"
        binding.homnay.homeItemImg.setImageResource(R.drawable.ic_hom_nay)
        binding.homnay.homeItem.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(Fragment_HomeDirections.actionFragmentHomeToHomNay())
        }
        binding.ketQuaDai.homeItemTextview.text = "Kết quả đài"
        binding.ketQuaDai.homeItemImg.setImageResource(R.drawable.ic_ket_qua_dai)
        binding.ketQuaDai.homeItem.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(Fragment_HomeDirections.actionFragmentHomeToFragmentKetQuaDai())
        }
        binding.tuongThuat.homeItemTextview.text = "Tường thuật"
        binding.tuongThuat.homeItemImg.setImageResource(R.drawable.ic_tuong_thuat)
        binding.tuongThuat.homeItem.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(Fragment_HomeDirections.actionFragmentHomeToKhungXoSoMB("mien-bac","Xổ số Miền Bắc"))
        }
        binding.soMo.homeItemTextview.text = "Sổ Mơ"
        binding.soMo.homeItemImg.setImageResource(R.drawable.ic_so_mo)
        binding.soMo.homeItem.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(Fragment_HomeDirections.actionFragmentHomeToSoMo3())
        }

        return binding.root
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.fragment__home, container, false)
    }
}
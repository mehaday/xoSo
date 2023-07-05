package com.example.xoso

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.xoso.databinding.FragmentHomeBinding
import com.example.xoso.databinding.FragmentSoMoBinding
import com.google.gson.Gson
import java.io.File

class soMo : Fragment() {
    lateinit var adapterket_so_mo: adapter_hom_nay
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSoMoBinding = DataBindingUtil.inflate(
            inflater , R.layout.fragment_so_mo , container , false
        )
        val gson = Gson()

        // Đọc nội dung của file JSON
//        val jsonString = File("path/to/your/file.json").readText()
        val resourceId = resources.getIdentifier("data_so_mo", "raw", requireContext().packageName)
        val jsonString = resources.openRawResource(resourceId).bufferedReader().use {
            it.readText()
        }

        // Chuyển đổi JSON thành danh sách các đối tượng Data
        val dataList = gson.fromJson(jsonString, HashMap<String, String>()::class.java)


        var list = mutableListOf<list_hom_nay>()

// Sử dụng dữ liệu đã đọc
        val iterator = dataList.entries.iterator()
        if (iterator.hasNext()) iterator.next() // Bỏ qua phần tử đầu tiên
        while (iterator.hasNext()) {
            val (key, value) = iterator.next()
            list.add(list_hom_nay(R.drawable.icon_homnay_item, key, "Cặp số tương ứng: $value"))
        }
        adapterket_so_mo = adapter_hom_nay(requireActivity(),list)

        binding.itemSoMo.adapter = adapterket_so_mo
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Xử lý khi người dùng nhấn nút tìm kiếm
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Xử lý khi người dùng thay đổi nội dung tìm kiếm
                // Lọc dữ liệu của bạn dựa trên nội dung tìm kiếm mới
                // Cập nhật adapter của bạn với dữ liệu đã lọc
                return true
            }
        })

        return binding.root
    }
}
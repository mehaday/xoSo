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
    lateinit var originalList: MutableList<list_hom_nay> // Danh sách gốc

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
        val resourceId = resources.getIdentifier("data_so_mo", "raw", requireContext().packageName)
        val jsonString = resources.openRawResource(resourceId).bufferedReader().use {
            it.readText()
        }

        // Chuyển đổi JSON thành danh sách các đối tượng Data
        val dataList = gson.fromJson(jsonString, HashMap<String, String>()::class.java)

        originalList = mutableListOf() // Khởi tạo danh sách gốc

        var list = mutableListOf<list_hom_nay>()

        // Sử dụng dữ liệu đã đọc
        val iterator = dataList.entries.iterator()
        if (iterator.hasNext()) iterator.next() // Bỏ qua phần tử đầu tiên
        while (iterator.hasNext()) {
            val (key, value) = iterator.next()
            list.add(list_hom_nay(R.drawable.dream, key, "Cặp số tương ứng: $value"))
        }

        originalList.addAll(list) // Sao chép nội dung vào danh sách gốc

        adapterket_so_mo = adapter_hom_nay(requireActivity(), list)
        binding.itemSoMo.adapter = adapterket_so_mo

        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //xử lý khi ấn tìm kiếm
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                // xử lý khi nhập ( nội dung ô tìm kiếm thay đổi )
                val filteredList = originalList.filter { item ->
                    item.title.contains(newText, ignoreCase = true)
                }
                adapterket_so_mo.updateData(filteredList)
                return true
            }
        })

        return binding.root
    }
}

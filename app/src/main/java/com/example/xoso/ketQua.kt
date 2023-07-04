package com.example.xoso

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ketQua : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ket_qua, container, false)
        val tableLayout = view.findViewById<LinearLayout>(R.id.tableLayout)

        // lấy ngày tháng năm
        val currentDate = LocalDate.now()
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        //api
        val dataProcessor = MyDataProcessor()
        dataProcessor.processScrapingData("Hà Nội", formattedDate) { contents, exception ->
            if (exception != null) {
                // Xử lý lỗi
                Log.d("aaa","lỗi")
                exception.printStackTrace()
            } else {
                Log.d("abc", contents.toString())
                // Xử lý kết quả
                activity?.runOnUiThread {
                    var mau_xen_ke = 1
                    if (contents != null) {
                        //day
                    }
                }
            }
        }

        return view
    }
}

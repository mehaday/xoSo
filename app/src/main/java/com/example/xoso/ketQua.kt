package com.example.xoso

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.GridLayout
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity

class ketQua : Fragment() {
    private val contents = listOf(
        "Giải ĐB", "901983",
        "Giải Nhất", "34838",
        "Giải Nhì", "97761",
        "Giải ba", "13608 - 49691",
        "Giải tư", "94613 - 81118 - 97821 - 65989 - 25658 - 43468 - 02692",
        "Giải năm", "7985",
        "Giải sáu", "2074 - 4183 - 7845",
        "Giải tám", "23"
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //api
        val myDataProcessor = MyDataProcessor()
        val tinh = "Bạc Liêu"
        val ngay = "2023-06-18"

        val data = myDataProcessor.processScrapingData(tinh, ngay)

        for (content in data) {
            Log.d("aaaa",content)
        }
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ket_qua, container, false)
        val tableLayout = view.findViewById<LinearLayout>(R.id.tableLayout)
        var mau_xen_ke = 1
        for (i in contents.indices step 2) {
            mau_xen_ke += 1
            val tableRow = LinearLayout(requireContext())
            tableRow.gravity = Gravity.CENTER
            if (mau_xen_ke % 2 == 0) {
                val drawable = resources.getDrawable(R.drawable.your_background_drawable)
                tableRow.background = drawable
            } // Replace "your_background_drawable" with your actual drawable name

            val prizeTextView = TextView(requireContext())
            prizeTextView.text = contents[i]
            prizeTextView.gravity = Gravity.CENTER
            val linearLayout0 = LinearLayout(requireContext())
            linearLayout0.orientation = LinearLayout.HORIZONTAL

            val layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            linearLayout0.layoutParams = layoutParams
            linearLayout0.addView(prizeTextView)
            tableRow.addView(linearLayout0)

            val numbers = contents[i + 1].split(" - ")

            var linearLayout = LinearLayout(requireContext())
            linearLayout.orientation = LinearLayout.HORIZONTAL

            val linearLayout2 = LinearLayout(requireContext())
            linearLayout2.orientation = LinearLayout.VERTICAL
            var dem = 0

            for (number in numbers) {
                dem = dem + 1
                val numberTextView = TextView(requireContext())
                numberTextView.text = number
                numberTextView.gravity = Gravity.CENTER
                numberTextView.layoutParams = layoutParams
                linearLayout.addView(numberTextView)
                if (dem == 3) {
                    linearLayout2.addView(linearLayout)
                    linearLayout = LinearLayout(requireContext())
                    dem = 0
                }
            }

            val layoutParams2 = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                4f
            )

            if (numbers.size < 3) {
                linearLayout.layoutParams = layoutParams2
                tableRow.addView(linearLayout)
            } else {
                linearLayout2.layoutParams = layoutParams2
                if (numbers.size % 3 != 0) {
                    linearLayout2.addView(linearLayout)
                    tableRow.addView(linearLayout2)
                } else {
                    tableRow.addView(linearLayout2)
                }
            }

            tableLayout.addView(tableRow)
        }
        return view
    }

}
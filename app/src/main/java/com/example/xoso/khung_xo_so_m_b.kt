package com.example.xoso

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class khung_xo_so_m_b : Fragment() {

    private val rowsLast2Digits: MutableList<List<String>> = mutableListOf()
    private val rowsLast3Digits: MutableList<List<String>> = mutableListOf()
//    private val rowsFull: List<List<String>> = listOf(
//        listOf("ĐB", "96894"),
//        listOf("G.1", "03260"),
//        listOf("G.2", "17064", "71834"),
//        listOf("G.3", "86082", "55306", "22767"),
//        listOf("G.3", "85062", "06138", "27224"),
//        listOf("G.4", "6983", "2670", "2483", "7136"),
//        listOf("G.5", "7354", "5524", "1596"),
//        listOf("G.5", "1151", "3202", "4001"),
//        listOf("G.6", "005", "551", "305"),
//        listOf("G.7", "49", "08", "10", "05")
//    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_khung_xo_so_m_b, container, false)

        // lấy ngày tháng năm
        val currentDate = LocalDate.now()
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        // doc bien truyen vao

        var args = khung_xo_so_m_bArgs.fromBundle((requireArguments()))

        // set ten xo so
        val myTextView = view.findViewById<TextView>(R.id.xoSo)
        myTextView.text = args.tenXoSo


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
                        val emptyList = mutableListOf<List<String>>()
                        println("Hello, world!!!")
                        for (i in contents.indices step 2) {
                            var emptyList2 = mutableListOf<String>()
//                            println(contents[i])
                            emptyList2.add(contents[i])
                            val numbers = contents[i + 1].split(" - ")
                            var dem = 0
                            for (number in numbers) {
                                dem++
                                if (dem == 4 && numbers.size > 4) {
                                    emptyList.add(emptyList2)
                                    emptyList2 = mutableListOf<String>()
                                    emptyList2.add(contents[i])
                                    dem = 1
                                }
//                                println(number)
                                emptyList2.add(number)
                            }
                            emptyList.add(emptyList2)
                        }
//                        println(emptyList)
                        //day
                        val rowsFull = emptyList
                        val tableLayout = TableLayout(requireContext()).apply {
                            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            for (row in rowsFull) {
                                val newRow2 = row.mapIndexed { index, cell ->
                                    if (index == 0) cell else if (cell.length > 2) cell.substring(cell.length - 2) else cell
                                }
                                rowsLast2Digits.add(newRow2)
                            }
                            for (row in rowsFull) {
                                val newRow3 = row.mapIndexed { index, cell ->
                                    if (index == 0) cell else if (cell.length > 3) cell.substring(cell.length - 3) else cell
                                }
                                rowsLast3Digits.add(newRow3)
                            }



                            var currentRows = rowsFull

                            for (row in currentRows) {
                                val tableRow = TableRow(requireContext())
                                for (cell in row) {
                                    val textView = TextView(requireContext()).apply {
                                        layoutParams = TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                                        text = cell
                                    }
                                    tableRow.addView(textView)
                                }
                                addView(tableRow)
                            }
                        }

                        val parentLayout = view.findViewById<ViewGroup>(R.id.parent_layout)
                        parentLayout.removeAllViews()
                        parentLayout.addView(tableLayout)

                        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
                        radioGroup.setOnCheckedChangeListener { _, checkedId ->
                            tableLayout.removeAllViews()

                            if (checkedId == R.id.radio_button1) {
                                for (row in rowsLast2Digits) {
                                    val tableRow = TableRow(requireContext())
                                    for (cell in row) {
                                        val textView = TextView(requireContext()).apply {
                                            layoutParams = TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                                            text = cell
                                        }
                                        tableRow.addView(textView)
                                    }
                                    tableLayout.addView(tableRow)
                                }
                            }else if (checkedId == R.id.radio_button2) {
                                for (row in rowsLast3Digits) {
                                    val tableRow = TableRow(requireContext())
                                    for (cell in row) {
                                        val textView = TextView(requireContext()).apply {
                                            layoutParams = TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                                            text = cell
                                        }
                                        tableRow.addView(textView)
                                    }
                                    tableLayout.addView(tableRow)
                                }
                            }else {
                                for (row in rowsFull) {
                                    val tableRow = TableRow(requireContext())
                                    for (cell in row) {
                                        val textView = TextView(requireContext()).apply {
                                            layoutParams = TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                                            text = cell
                                        }
                                        tableRow.addView(textView)
                                    }
                                    tableLayout.addView(tableRow)
                                }
                            }
                        }
                    }
                }
            }
        }


        return view
    }
}

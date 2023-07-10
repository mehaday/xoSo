package com.example.xoso

import MyDataProcessor
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.xoso.databinding.FragmentKhungXoSoMBBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class khung_xo_so_m_b : Fragment() {
    private val rowsLast3Digits: MutableList<List<String>> = mutableListOf()
    private var rowsLast4Digits: MutableList<List<String>> = mutableListOf()
    private val rowsLast2Digits: MutableList<List<String>> = mutableListOf()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentKhungXoSoMBBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_khung_xo_so_m_b, container, false
        )


        //
        val lay_gia_tri_test = arguments?.let { khung_xo_so_m_bArgs.fromBundle(it) }
        binding.tenXoSo.text = lay_gia_tri_test?.tenXoSo
        // lấy thứ hiện tại
        val today: LocalDate = LocalDate.now()
        val dayOfWeek: DayOfWeek = today.dayOfWeek
        Log.d("xinchao",dayOfWeek.toString())
        //
        //lấy ngày hiện tại
        val currentDate = LocalDate.now()
        // Trừ đi 1 ngày
        val previousDate = currentDate.minusDays(1)
        // Định dạng lại ngày thành chuỗi
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formattedDate = previousDate.format(formatter)
        //tỉnh
        val tinh = lay_gia_tri_test?.key.toString()


        Log.d("xinchao",lay_gia_tri_test?.key.toString())
        //

        binding.textView2.text = "${dayOfWeek} $formattedDate"
        GlobalScope.launch(Dispatchers.Main) {
            val generator = MyDataProcessor()
            val emptyList = withContext(Dispatchers.IO) {
                generator.getEmptyList(tinh, formattedDate)
            }

            // Sử dụng kết quả emptyList theo nhu cầu
            Log.d("Empty List", emptyList.toString())
            // Gán kết quả emptyList vào binding hoặc sử dụng nó theo nhu cầu
            // Ví dụ: binding.myTextView.text = emptyList.toString()
            var ketqua = emptyList
            Log.d("ketqua", ketqua.toString())

            //xử lý đầu đuôi
            val transformedItem = mutableListOf<String>()
            for (item in emptyList) {
                for (i in 0 until item.size) {
                    if (i != 0) {
                        val number = item[i]
                        val shortenedNumber = number.takeLast(2)
                        transformedItem.add(shortenedNumber)
                    }
                }
            }
            Log.d("danhsach", transformedItem.toString())
            //
            var a = transformedItem
            var dau = mutableListOf<List<String>>()
            for (j in 0..9) {
                var list = mutableListOf<String>()
                var text = ""
                for (i in a) {
                    var b = j.toString()
                    if (b == i[0].toString()) {
                        text = text + i[1].toString() + " , "
                    }
                }
                list.add(j.toString())
                if (text == "") {
                    list.add("-")
                }else {
                    text = text.dropLast(2)
                    list.add(text)
                }
                dau.add(list)
            }
            var duoi = mutableListOf<List<String>>()
            for (j in 0..9) {
                var list = mutableListOf<String>()
                var text = ""
                for (i in a) {
                    var b = j.toString()
                    if (b == i[0].toString()) {
                        text = text + i[1].toString() + " , "
                    }
                }
                list.add(j.toString())
                if (text == "") {
                    list.add("-")
                }else {
                    text = text.dropLast(2)
                    list.add(text)
                }
                duoi.add(list)
            }
//            Log.d("danhsach",dau[0].toString())
            binding.dauDuoi = DataDauDuoi(dau,duoi)

            val radioGroup = binding.radioGroup
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                if (checkedId == R.id.radio_button1) {
                    for (item in emptyList) {
                        val transformedItem = mutableListOf<String>()
                        for (i in 0 until item.size) {
                            if (i == 0) {
                                transformedItem.add(item[i].toString())
                            } else {
                                val number = item[i].toString()
                                val shortenedNumber = number.takeLast(2)
                                transformedItem.add(shortenedNumber)
                            }
                        }
                        rowsLast2Digits.add(transformedItem)
                    }
                    Log.d("ketqua",rowsLast2Digits.toString())
                    binding.giaiDB = DataThongTinKetQua(rowsLast2Digits[0][0], rowsLast2Digits[0].subList(1, emptyList[0].size))
                    binding.giai1 = DataThongTinKetQua(rowsLast2Digits[1][0], rowsLast2Digits[1].subList(1, emptyList[1].size))
                    binding.giai2 = DataThongTinKetQua(rowsLast2Digits[2][0], rowsLast2Digits[2].subList(1, emptyList[2].size))
                    binding.giai3 = DataThongTinKetQua(rowsLast2Digits[3][0], rowsLast2Digits[3].subList(1, emptyList[3].size))
                    binding.giai4 = DataThongTinKetQua(rowsLast2Digits[4][0], rowsLast2Digits[4].subList(1, emptyList[4].size))
                    binding.giai5 = DataThongTinKetQua(rowsLast2Digits[5][0], rowsLast2Digits[5].subList(1, emptyList[5].size))
                    binding.giai6 = DataThongTinKetQua(rowsLast2Digits[6][0], rowsLast2Digits[6].subList(1, emptyList[6].size))
                    binding.giai7 = DataThongTinKetQua(rowsLast2Digits[7][0], rowsLast2Digits[7].subList(1, emptyList[7].size))


                }else if (checkedId == R.id.radio_button2) {
                    for (item in emptyList) {
                        val transformedItem = mutableListOf<String>()
                        for (i in 0 until item.size) {
                            if (i == 0) {
                                transformedItem.add(item[i].toString())
                            } else {
                                val number = item[i].toString()
                                val shortenedNumber = number.takeLast(3)
                                transformedItem.add(shortenedNumber)
                            }
                        }
                        rowsLast3Digits.add(transformedItem)
                    }
                    Log.d("ketqua",rowsLast3Digits.toString())
                    binding.giaiDB = DataThongTinKetQua(rowsLast3Digits[0][0], rowsLast3Digits[0].subList(1, emptyList[0].size))
                    binding.giai1 = DataThongTinKetQua(rowsLast3Digits[1][0], rowsLast3Digits[1].subList(1, emptyList[1].size))
                    binding.giai2 = DataThongTinKetQua(rowsLast3Digits[2][0], rowsLast3Digits[2].subList(1, emptyList[2].size))
                    binding.giai3 = DataThongTinKetQua(rowsLast3Digits[3][0], rowsLast3Digits[3].subList(1, emptyList[3].size))
                    binding.giai4 = DataThongTinKetQua(rowsLast3Digits[4][0], rowsLast3Digits[4].subList(1, emptyList[4].size))
                    binding.giai5 = DataThongTinKetQua(rowsLast3Digits[5][0], rowsLast3Digits[5].subList(1, emptyList[5].size))
                    binding.giai6 = DataThongTinKetQua(rowsLast3Digits[6][0], rowsLast3Digits[6].subList(1, emptyList[6].size))
                    binding.giai7 = DataThongTinKetQua(rowsLast3Digits[7][0], rowsLast3Digits[7].subList(1, emptyList[7].size))

                }else {
                    binding.giaiDB = DataThongTinKetQua(emptyList[0][0], emptyList[0].subList(1, emptyList[0].size))
                    binding.giai1 = DataThongTinKetQua(emptyList[1][0], emptyList[1].subList(1, emptyList[1].size))
                    binding.giai2 = DataThongTinKetQua(emptyList[2][0], emptyList[2].subList(1, emptyList[2].size))
                    binding.giai3 = DataThongTinKetQua(emptyList[3][0], emptyList[3].subList(1, emptyList[3].size))
                    binding.giai4 = DataThongTinKetQua(emptyList[4][0], emptyList[4].subList(1, emptyList[4].size))
                    binding.giai5 = DataThongTinKetQua(emptyList[5][0], emptyList[5].subList(1, emptyList[5].size))
                    binding.giai6 = DataThongTinKetQua(emptyList[6][0], emptyList[6].subList(1, emptyList[6].size))
                    binding.giai7 = DataThongTinKetQua(emptyList[7][0], emptyList[7].subList(1, emptyList[7].size))
                }
            }
            Log.d("doi","da doi")
            binding.giaiDB = DataThongTinKetQua(emptyList[0][0], emptyList[0].subList(1, emptyList[0].size))
            binding.giai1 = DataThongTinKetQua(emptyList[1][0], emptyList[1].subList(1, emptyList[1].size))
            binding.giai2 = DataThongTinKetQua(emptyList[2][0], emptyList[2].subList(1, emptyList[2].size))
            binding.giai3 = DataThongTinKetQua(emptyList[3][0], emptyList[3].subList(1, emptyList[3].size))
            binding.giai4 = DataThongTinKetQua(emptyList[4][0], emptyList[4].subList(1, emptyList[4].size))
            binding.giai5 = DataThongTinKetQua(emptyList[5][0], emptyList[5].subList(1, emptyList[5].size))
            binding.giai6 = DataThongTinKetQua(emptyList[6][0], emptyList[6].subList(1, emptyList[6].size))
            binding.giai7 = DataThongTinKetQua(emptyList[7][0], emptyList[7].subList(1, emptyList[7].size))

        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Các xử lý khác của phương thức onViewCreated
    }
}

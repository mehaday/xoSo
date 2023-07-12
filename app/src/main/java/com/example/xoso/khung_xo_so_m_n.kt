package com.example.xoso

import MyDataProcessor
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.xoso.databinding.FragmentKhungXoSoMNBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class khung_xo_so_m_n : Fragment() {
    private val rowsLast3Digits: MutableList<List<String>> = mutableListOf()
    private var rowsLast4Digits: MutableList<List<String>> = mutableListOf()
    private val rowsLast2Digits: MutableList<List<String>> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentKhungXoSoMNBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_khung_xo_so_m_n, container, false
        )

        val currentDate = LocalDate.now()
        // Trừ đi 1 ngày
        val previousDate = currentDate.minusDays(1)
        // Định dạng lại ngày thành chuỗi
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formattedDate = previousDate.format(formatter)

        binding.textView2.text = formattedDate


        val lay_gia_tri_test = arguments?.let { khung_xo_so_m_bArgs.fromBundle(it) }
        binding.tenXoSo.text = lay_gia_tri_test?.tenXoSo

        val tinh = lay_gia_tri_test?.key.toString()

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
            val dau = mutableListOf<List<String>>()
            val duoi = mutableListOf<List<String>>()
            for (j in 0..9) {
                val list = mutableListOf<String>()
                val list2 = mutableListOf<String>()
                var text = ""
                var text2 = ""
                for (i in a) {
                    val b = j.toString()
                    if (b == i[0].toString()) {
                        text += "${i[1]}, "
                    }
                    if (b == i[1].toString()) {
                        text2 += "${i[0]}, "
                    }
                }
                list.add(j.toString())
                list2.add(j.toString())
                list.add(if (text.isEmpty()) "-" else text.dropLast(2))
                list2.add(if (text2.isEmpty()) "-" else text2.dropLast(2))
                dau.add(list)
                duoi.add(list2)
            }

//            Log.d("danhsach",dau[0].toString())
            binding.dauDuoi = DataDauDuoi(dau, duoi)


            val radioGroup = binding.radioGroup
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                val rowsLast2Digits: MutableList<List<String>> = mutableListOf()
                val digitsToTake = when (checkedId) {
                    R.id.radio_button1 -> 2
                    R.id.radio_button2 -> 3
                    else -> 10
                }
                Log.d("doi",digitsToTake.toString())
                for (item in emptyList) {
                    val transformedItem = mutableListOf<String>()
                    for (i in 0 until item.size) {
                        if (i == 0) {
                            transformedItem.add(item[i].toString())
                        } else {
                            val number = item[i].toString()
                            val shortenedNumber = number.takeLast(digitsToTake)
                            transformedItem.add(shortenedNumber)
                        }
                    }
                    rowsLast2Digits.add(transformedItem)
                }
                Log.d("ketqua", rowsLast2Digits.toString())
                binding.giaiDB = DataThongTinKetQua(
                    rowsLast2Digits[0][0],
                    rowsLast2Digits[0].subList(1, emptyList[0].size)
                )
                binding.giai1 = DataThongTinKetQua(
                    rowsLast2Digits[1][0],
                    rowsLast2Digits[1].subList(1, emptyList[1].size)
                )
                binding.giai2 = DataThongTinKetQua(
                    rowsLast2Digits[2][0],
                    rowsLast2Digits[2].subList(1, emptyList[2].size)
                )
                binding.giai3 = DataThongTinKetQua(
                    rowsLast2Digits[3][0],
                    rowsLast2Digits[3].subList(1, emptyList[3].size)
                )
                binding.giai4 = DataThongTinKetQua(
                    rowsLast2Digits[4][0],
                    rowsLast2Digits[4].subList(1, emptyList[4].size)
                )
                binding.giai5 = DataThongTinKetQua(
                    rowsLast2Digits[5][0],
                    rowsLast2Digits[5].subList(1, emptyList[5].size)
                )
                binding.giai6 = DataThongTinKetQua(
                    rowsLast2Digits[6][0],
                    rowsLast2Digits[6].subList(1, emptyList[6].size)
                )
                binding.giai7 = DataThongTinKetQua(
                    rowsLast2Digits[7][0],
                    rowsLast2Digits[7].subList(1, emptyList[7].size)
                )
                binding.giai8 = DataThongTinKetQua(
                    rowsLast2Digits[8][0],
                    rowsLast2Digits[8].subList(1, emptyList[8].size)
                )

            }
            Log.d("doi", "da doi")
            binding.giaiDB =
                DataThongTinKetQua(emptyList[0][0], emptyList[0].subList(1, emptyList[0].size))
            binding.giai1 =
                DataThongTinKetQua(emptyList[1][0], emptyList[1].subList(1, emptyList[1].size))
            binding.giai2 =
                DataThongTinKetQua(emptyList[2][0], emptyList[2].subList(1, emptyList[2].size))
            binding.giai3 =
                DataThongTinKetQua(emptyList[3][0], emptyList[3].subList(1, emptyList[3].size))
            binding.giai4 =
                DataThongTinKetQua(emptyList[4][0], emptyList[4].subList(1, emptyList[4].size))
            binding.giai5 =
                DataThongTinKetQua(emptyList[5][0], emptyList[5].subList(1, emptyList[5].size))
            binding.giai6 =
                DataThongTinKetQua(emptyList[6][0], emptyList[6].subList(1, emptyList[6].size))
            binding.giai7 =
                DataThongTinKetQua(emptyList[7][0], emptyList[7].subList(1, emptyList[7].size))
            binding.giai8 =
                DataThongTinKetQua(emptyList[8][0], emptyList[8].subList(1, emptyList[8].size))

        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Các xử lý khác của phương thức onViewCreated
    }

}


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
import com.example.xoso.databinding.FragmentNorthernLotteryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NorthernLottery : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNorthernLotteryBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_northern_lottery, container, false
        )


        //
        val getArgs = arguments?.let { NorthernLotteryArgs.fromBundle(it) }
        binding.tenXoSo.text = getArgs?.tenXoSo
        // lấy thứ hiện tại
        val today: LocalDate = LocalDate.now()
        val dayOfWeek: DayOfWeek = today.dayOfWeek
        Log.d("xinchao", dayOfWeek.toString())
        //
        //lấy ngày hiện tại
        val currentDate = LocalDate.now()
        // Trừ đi 1 ngày
        val previousDate = currentDate.minusDays(1)
        // Định dạng lại ngày thành chuỗi
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formattedDate = previousDate.format(formatter)
        //tỉnh
        val province = getArgs?.key.toString()


        Log.d("xinchao", getArgs?.key.toString())
        //

        binding.textView2.text = "$formattedDate"
        GlobalScope.launch(Dispatchers.Main) {
            val generator = MyDataProcessor()
            val emptyList = withContext(Dispatchers.IO) {
                generator.getEmptyList(province, formattedDate)
            }

            //xử lý đầu đuôi
            val transformedItem = mutableListOf<String>()
            for (item in emptyList) {
                for (i in item.indices) {
                    if (i != 0) {
                        val number = item[i]
                        val shortenedNumber = number.takeLast(2)
                        transformedItem.add(shortenedNumber)
                    }
                }
            }
            Log.d("danhsach", transformedItem.toString())
            //
            val listFirst = mutableListOf<List<String>>()
            val listLast = mutableListOf<List<String>>()
            for (j in 0..9) {
                val listFirstValue = mutableListOf<String>()
                val listLastValue = mutableListOf<String>()
                var textInListFirst = ""
                var textInListLast = ""
                for (i in transformedItem) {
                    if (j.toString() == i[0].toString()) {
                        textInListFirst += "${i[1]}, "
                    }
                    if (j.toString() == i[1].toString()) {
                        textInListLast += "${i[0]}, "
                    }
                }
                listFirstValue.add(j.toString())
                listLastValue.add(j.toString())
                listFirstValue.add(if (textInListFirst.isEmpty()) "-" else textInListFirst.dropLast(2))
                listLastValue.add(if (textInListLast.isEmpty()) "-" else textInListLast.dropLast(2))
                listFirst.add(listFirstValue)
                listLast.add(listLastValue)
            }

//            Log.d("danhsach",dau[0].toString())
            binding.dauDuoi = DataDauDuoi(listFirst, listLast)


            val radioGroup = binding.radioGroup
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                val rowsLastDigits: MutableList<List<String>> = mutableListOf()
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
                    rowsLastDigits.add(transformedItem)
                }
                Log.d("ketqua", rowsLastDigits.toString())
                extracted(binding,rowsLastDigits)

            }
            Log.d("doi", "da doi")
            extracted(binding,emptyList)

        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Các xử lý khác của phương thức onViewCreated
    }
    private fun extracted(binding : FragmentNorthernLotteryBinding, emptyList: MutableList<List<String>>){
        binding.giaiDB =
            ResultsInfoLottery(emptyList[0][0], emptyList[0].subList(1, emptyList[0].size))
        binding.giai1 =
            ResultsInfoLottery(emptyList[1][0], emptyList[1].subList(1, emptyList[1].size))
        binding.giai2 =
            ResultsInfoLottery(emptyList[2][0], emptyList[2].subList(1, emptyList[2].size))
        binding.giai3 =
            ResultsInfoLottery(emptyList[3][0], emptyList[3].subList(1, emptyList[3].size))
        binding.giai4 =
            ResultsInfoLottery(emptyList[4][0], emptyList[4].subList(1, emptyList[4].size))
        binding.giai5 =
            ResultsInfoLottery(emptyList[5][0], emptyList[5].subList(1, emptyList[5].size))
        binding.giai6 =
            ResultsInfoLottery(emptyList[6][0], emptyList[6].subList(1, emptyList[6].size))
        binding.giai7 =
            ResultsInfoLottery(emptyList[7][0], emptyList[7].subList(1, emptyList[7].size))
    }
}

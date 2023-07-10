package com.example.xoso

import MyDataProcessor
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
import androidx.databinding.DataBindingUtil
import com.example.xoso.databinding.FragmentKetQuaBinding
import com.example.xoso.databinding.FragmentKetQuaDaiBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ketQua : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding: FragmentKetQuaBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_ket_qua, container, false
        )
        return binding.root
    }
}

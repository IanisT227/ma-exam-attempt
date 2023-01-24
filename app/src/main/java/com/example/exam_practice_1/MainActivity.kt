package com.example.exam_practice_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exam_practice_1.viewmodel.ExamViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val examViewModel: ExamViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
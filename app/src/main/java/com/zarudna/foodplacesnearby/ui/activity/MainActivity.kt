package com.zarudna.foodplacesnearby.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zarudna.foodplacesnearby.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }


}
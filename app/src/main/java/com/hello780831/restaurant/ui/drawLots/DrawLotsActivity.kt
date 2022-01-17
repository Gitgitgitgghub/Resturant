package com.hello780831.restaurant.ui.drawLots

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewOutlineProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hello780831.restaurant.R
import com.hello780831.restaurant.databinding.ActivityDrawLotsBinding
import com.hello780831.restaurant.model.Restaurant
import com.hello780831.restaurant.ui.DrawLotsResultActivity
import com.hello780831.restaurant.ui.EditRestaurant.EditRestaurantActivity
import java.lang.RuntimeException

class DrawLotsActivity : AppCompatActivity() {

    companion object{

        const val DATA = "data"

        fun launch(context: Context,restaurants: List<Restaurant>){
            context.startActivity(Intent(context, DrawLotsActivity::class.java).apply {
                putExtra(DATA,restaurants.toTypedArray())
            })
        }

    }

    private lateinit var mBinding :ActivityDrawLotsBinding
    private lateinit var mViewModel :DrawLotsVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_draw_lots)
        mBinding.btnDrawLots.setOnClickListener {
            mViewModel.drawLot(getIntentData())
        }
        mViewModel = ViewModelProvider(this).get(DrawLotsVM::class.java)
        mViewModel.mDrawLotsResultLiveData.observe(this){
            Log.d("kkk", "onCreate: $it")
            DrawLotsResultActivity.launch(this,it)
        }
    }

    private fun getIntentData() : List<Restaurant> {
        if (intent.getParcelableArrayExtra(DATA) == null){
            throw RuntimeException("DrawLotsActivity getParcelableExtra is null")
        }
        try {
            return intent.getParcelableArrayExtra(DATA)!!.asList() as List<Restaurant>
        }catch (e :ClassCastException) {
            throw e
        }
    }
}
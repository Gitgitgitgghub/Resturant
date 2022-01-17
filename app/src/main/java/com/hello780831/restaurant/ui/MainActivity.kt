package com.hello780831.restaurant.ui

import android.content.Context
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hello780831.restaurant.R
import com.hello780831.restaurant.databinding.ActivityMainBinding
import com.hello780831.restaurant.model.Restaurant
import com.hello780831.restaurant.ui.fastDrawLotsSelect.FastDrawLotsSelectActivity
import com.hello780831.restaurant.ui.restaurantList.RestaurantListActivity
import java.util.concurrent.Executor




class MainActivity : AppCompatActivity() ,View.OnClickListener{


    private lateinit var mBinding :ActivityMainBinding

    companion object{

        val TAG = "kkk"
        fun launch(context: Context){
            context.startActivity(Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initView()
    }

    private fun initView(){
        mBinding.btnEditRestaurant.setOnClickListener(this)
        mBinding.btnFastDrawSelect.setOnClickListener(this)
        mBinding.btnAboutUs.setOnClickListener(this)
    }



    val mHandler = Handler(Looper.myLooper()!!)
    val runnable : () ->Unit = {
        Log.d(MainActivity.TAG,"postDelayed")
        loop()
    }
    var mCount = 0
    private fun loop(){
        if (++mCount <= 10){
            mHandler.postDelayed(runnable,1000)
        }else{
            mHandler.removeCallbacks(runnable)
        }
    }



    override fun onClick(view: View) {
        when(view){
            mBinding.btnEditRestaurant->{
                RestaurantListActivity.launch(this)
            }
            mBinding.btnFastDrawSelect->{
                //FastDrawLotsSelectActivity.launch(this)
                mHandler.post(runnable)
            }
            mBinding.btnAboutUs->{
                //AboutMeActivity.launch(this)

                mHandler.removeCallbacks(runnable)
            }
        }
    }

    //@Synchronized
    fun log(s :String)
    {
        //Thread.sleep(1000)
        Log.d("kkk", "log: " + s)
    }

    val mName = A()
    val lock = Any()

    fun update(name :String){
        synchronized(lock){
            mName.name = name
        }
    }

    inner class A(var name: String ="BB"){

    }

    inner class B(){
        var name = ""
    }
}
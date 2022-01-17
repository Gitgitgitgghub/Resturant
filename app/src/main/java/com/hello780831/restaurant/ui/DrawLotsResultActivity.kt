package com.hello780831.restaurant.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hello780831.restaurant.R
import com.hello780831.restaurant.databinding.ActivityDrawLotsResultBinding
import com.hello780831.restaurant.model.Restaurant
import com.hello780831.restaurant.ui.restaurantList.RestaurantListActivity
import android.webkit.WebViewClient
import android.view.View

import android.webkit.WebView
import com.hello780831.restaurant.ui.drawLots.DrawLotsActivity
import java.lang.RuntimeException


class DrawLotsResultActivity : AppCompatActivity() {

    companion object{

        const val DATA = "data"

        fun launch(context: Context,restaurant: Restaurant){
            context.startActivity(Intent(context, DrawLotsResultActivity::class.java).apply {
                putExtra(DATA,restaurant)
            })
        }
    }

    private lateinit var mBinding : ActivityDrawLotsResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_draw_lots_result)
        initView()
        initWebView()
        setDefaultValue()
    }

    private fun initView(){
        mBinding.layoutInput.etAreaName.isFocusable = false
        mBinding.layoutInput.etRestaurantName.isFocusable = false
        mBinding.layoutInput.etRestaurantPhone.isFocusable = false
        mBinding.layoutInput.etRestaurantAddress.isFocusable = false
        mBinding.layoutInput.etRestaurantGoogleRatio.isFocusable = false
        mBinding.layoutInput.etRestaurantHasPark.isFocusable = false
        mBinding.layoutInput.etRestaurantPrice.isFocusable = false
        mBinding.layoutInput.etRestaurantMap.isFocusable = false
        mBinding.btnBackMain.setOnClickListener {
            MainActivity.launch(this)
            finish()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(){
        val intentData = getIntentData()
        val webView :WebView = mBinding.webViewMap
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient() //不調用系統瀏覽器
        webView.loadUrl(intentData.mapLink)
    }

    private fun setDefaultValue(){
        val intentData = getIntentData()
        mBinding.layoutInput.etAreaName.setText(intentData.area)
        mBinding.layoutInput.etRestaurantName.setText(intentData.restaurantName)
        mBinding.layoutInput.etRestaurantPhone.setText(intentData.phone)
        mBinding.layoutInput.etRestaurantAddress.setText(intentData.address)
        mBinding.layoutInput.etRestaurantMap.setText(intentData.mapLink)
        mBinding.layoutInput.etRestaurantGoogleRatio.setText(intentData.googleRatio.toString())
        mBinding.layoutInput.etRestaurantHasPark.setText(if (intentData.hasPark) "是" else "否")
        val stringArray = resources.getStringArray(R.array.priceOptionArray)
        val starCount = stringArray.indexOf(intentData.price) + 1
        var price = ""
        repeat(starCount){
            price += getString(R.string.star)
        }
        mBinding.layoutInput.etRestaurantPrice.setText(price)
    }

    private fun getIntentData() : Restaurant {
        if (intent.getParcelableExtra<Restaurant>(DATA) == null){
            throw RuntimeException("DrawLotsResultActivity getParcelableExtra is null")
        }
        try {
            return intent.getParcelableExtra(DATA)!!
        }catch (e :ClassCastException) {
            throw e
        }
    }
}
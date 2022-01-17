package com.hello780831.restaurant.ui.AreaRestaurantList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hello780831.restaurant.R
import com.hello780831.restaurant.adapter.AreaRestaurantAdapter
import com.hello780831.restaurant.databinding.ActivityAreaRestaurantBinding
import com.hello780831.restaurant.model.AreaRestaurants
import com.hello780831.restaurant.model.Restaurant
import com.hello780831.restaurant.ui.EditRestaurant.EditRestaurantActivity
import com.hello780831.restaurant.ui.drawLots.DrawLotsActivity
import java.lang.RuntimeException

class AreaRestaurantActivity : AppCompatActivity() ,
    AreaRestaurantAdapter.ItemClickListener,
    View.OnClickListener{

    companion object{

        const val DATA = "data"

        fun launch(context: Context,areaRestaurants: AreaRestaurants){
            val intent = Intent(context,AreaRestaurantActivity::class.java)
            intent.putExtra(DATA,areaRestaurants)
            context.startActivity(intent)
        }
    }

    private lateinit var mBinding: ActivityAreaRestaurantBinding
    private lateinit var mViewModel :AreaRestaurantVM
    private lateinit var mAdapter: AreaRestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_area_restaurant)
        mViewModel = ViewModelProvider(this,AreaRestaurantVMFactory(application,getIntentData().area)).get(AreaRestaurantVM::class.java)
        initView()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun fetchData() {
        mViewModel.fetchAreaRestaurant()
    }

    private fun getIntentData() :AreaRestaurants{
        if (intent.getParcelableExtra<AreaRestaurants>(DATA) == null){
            throw RuntimeException("EditRestaurantListActivity getParcelableExtra is null")
        }
        return intent.getParcelableExtra<AreaRestaurants>(DATA)!!
    }

    private fun initView(){
        mBinding.rvRestaurant.layoutManager = LinearLayoutManager(this)
        mBinding.btnAddRestaurant.setOnClickListener(this)
        mBinding.btnEditRestaurant.setOnClickListener(this)
        mBinding.btnDeleteRestaurant.setOnClickListener(this)
        mBinding.btnDrawLots.setOnClickListener(this)
        mBinding.btnSelectAll.setOnClickListener(this)
        mViewModel.mAreaRestaurantLiveData.observe(this){ dataSource ->
            mBinding.tvAreaName.text = dataSource.area.areaName
            if (mBinding.rvRestaurant.adapter == null){
                mBinding.rvRestaurant.adapter = AreaRestaurantAdapter(dataSource,
                    false,
                    true,
                    this)
                    .also {
                        mAdapter = it
                    }
            }else{
                mAdapter.updateNewAreaRestaurant(dataSource)
            }
        }
        mViewModel.mEditRestaurantLiveData.observe(this){
            if (it == null){
                Toast.makeText(this,getString(R.string.selectEditRestaurantCountError),Toast.LENGTH_SHORT).show()
            }else{
                EditRestaurantActivity.launchEditMode(this,it)
            }
        }
        mViewModel.mDrawLotRestaurantsLiveData.observe(this){
            DrawLotsActivity.launch(this,it)
        }
    }

    override fun onClick(view: View) {
        when(view){
            mBinding.btnAddRestaurant ->{
                EditRestaurantActivity.launchAddMode(this)
            }
            mBinding.btnDeleteRestaurant ->{
                mViewModel.deleteCheckRestaurant()
            }
            mBinding.btnEditRestaurant ->{
                mViewModel.editRestaurant()
            }
            mBinding.btnDrawLots ->{
                mViewModel.drawLots()
            }
            mBinding.btnSelectAll ->{
                mViewModel.clickAllSelect()
            }
        }
    }

    override fun onAreaEditClick(areaRestaurants: AreaRestaurants) {
        TODO("Not yet implemented")
    }

    override fun onRestaurantCheckboxClick(isCheck: Boolean, restaurant: Restaurant) {
        mViewModel.restaurantCheckChange(isCheck,restaurant)
    }
}
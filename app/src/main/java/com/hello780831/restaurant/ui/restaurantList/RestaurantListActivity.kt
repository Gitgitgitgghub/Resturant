package com.hello780831.restaurant.ui.restaurantList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.hello780831.restaurant.R
import com.hello780831.restaurant.adapter.AreaRestaurantAdapter
import com.hello780831.restaurant.databinding.ActivityRestaurantListBinding
import com.hello780831.restaurant.model.AreaRestaurants
import com.hello780831.restaurant.model.Restaurant
import com.hello780831.restaurant.ui.AreaRestaurantList.AreaRestaurantActivity
import com.hello780831.restaurant.ui.drawLots.DrawLotsActivity

open class RestaurantListActivity : AppCompatActivity() , AreaRestaurantAdapter.ItemClickListener, View.OnClickListener {

    companion object{

        fun launch(context: Context){
            context.startActivity(Intent(context, RestaurantListActivity::class.java))
        }
    }

    protected lateinit var mViewModel :RestaurantListVM
    protected lateinit var mBinding: ActivityRestaurantListBinding
    protected lateinit var mAdapter: ConcatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_restaurant_list)
        createViewModel()
        initView()
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        mViewModel.fetchRestaurant()
    }

    open fun createViewModel() {
        mViewModel = ViewModelProvider(this).get(RestaurantListVM::class.java)
    }

    open fun isSelectAllShow() = false

    private fun initView(){
        mBinding.btnSelectAll.visibility = if (isSelectAllShow()) View.VISIBLE else View.GONE
        mBinding.btnDrawLots.setOnClickListener(this)
        mBinding.btnSelectAll.setOnClickListener(this)
        mViewModel.mDrawLotRestaurantsLiveData.observe(this){
            DrawLotsActivity.launch(this,it)
        }
    }

    open fun initRecyclerView(){
        mBinding.rvRestaurant.layoutManager = LinearLayoutManager(this)
        mBinding.rvRestaurant.adapter = ConcatAdapter().also {
            mAdapter = it
        }
        mViewModel.mAreaRestaurantLiveData.observe(this){ it ->
            it.forEachIndexed { index, areaRestaurant ->
                if (mAdapter.adapters.size != it.size){
                    mAdapter.addAdapter(AreaRestaurantAdapter(areaRestaurant,true,false,this))
                }else{
                    val childAdapter = mAdapter.adapters[index]
                    if (childAdapter is AreaRestaurantAdapter){
                        childAdapter.updateNewAreaRestaurant(areaRestaurant)
                    }
                }
            }
        }
    }

    override fun onClick(v: View) {
        when(v){
            mBinding.btnDrawLots -> mViewModel.drawLots()
            mBinding.btnSelectAll -> mViewModel.clickAllSelect()
            else ->{

            }
        }
    }

    override fun onAreaEditClick(areaRestaurants: AreaRestaurants) {
        AreaRestaurantActivity.launch(this,areaRestaurants)
    }

    override fun onRestaurantCheckboxClick(isCheck: Boolean, restaurant: Restaurant) {
        mViewModel.restaurantCheckChange(isCheck,restaurant)
    }


}
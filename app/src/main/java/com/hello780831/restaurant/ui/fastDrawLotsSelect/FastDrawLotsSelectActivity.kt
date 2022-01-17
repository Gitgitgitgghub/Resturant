package com.hello780831.restaurant.ui.fastDrawLotsSelect

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.hello780831.restaurant.adapter.AreaRestaurantAdapter
import com.hello780831.restaurant.ui.restaurantList.RestaurantListActivity

class FastDrawLotsSelectActivity : RestaurantListActivity() {

    companion object{

        fun launch(context: Context){
            context.startActivity(Intent(context, FastDrawLotsSelectActivity::class.java))
        }
    }

    override fun isSelectAllShow() = true

    override fun initRecyclerView() {
        mBinding.rvRestaurant.layoutManager = LinearLayoutManager(this)
        mBinding.rvRestaurant.adapter = ConcatAdapter().also {
            mAdapter = it
        }
        mViewModel.mAreaRestaurantLiveData.observe(this){ it ->
            it.forEachIndexed { index, areaRestaurant ->
                if (mAdapter.adapters.size != it.size){
                    mAdapter.addAdapter(AreaRestaurantAdapter(areaRestaurant,false,true,this))
                }else{
                    val childAdapter = mAdapter.adapters[index]
                    if (childAdapter is AreaRestaurantAdapter){
                        childAdapter.updateNewAreaRestaurant(areaRestaurant)
                    }
                }
            }
        }
    }

    override fun createViewModel() {
        mViewModel = ViewModelProvider(this).get(FastDrawLotsSelectVM::class.java)
    }
}
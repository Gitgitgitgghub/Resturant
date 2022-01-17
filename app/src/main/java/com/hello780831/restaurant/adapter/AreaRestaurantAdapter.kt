package com.hello780831.restaurant.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hello780831.restaurant.R
import com.hello780831.restaurant.databinding.ItemAreaBinding
import com.hello780831.restaurant.databinding.ItemRestaurantNameBinding
import com.hello780831.restaurant.model.AreaRestaurants
import com.hello780831.restaurant.model.Restaurant

class AreaRestaurantAdapter(private var mDataSource :AreaRestaurants,
                            private val withAreaName :Boolean,
                            private val withCheckbox :Boolean,
                            private val mItemClickListener: ItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val TYPE_AREA = 0
        const val TYPE_RESTAURANT = 1
    }

    interface ItemClickListener{
        fun onAreaEditClick(areaRestaurants: AreaRestaurants)
        fun onRestaurantCheckboxClick(isCheck :Boolean ,restaurant: Restaurant)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNewAreaRestaurant(dataSource :AreaRestaurants){
        mDataSource = dataSource
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (withAreaName && position == 0){
            return TYPE_AREA
        }
        return TYPE_RESTAURANT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_AREA){
            return AreaViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_area,
                parent,
                false),
                mItemClickListener)
        }
        return RestaurantNameViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_restaurant_name,
                parent,
                false),
                mItemClickListener,
                withCheckbox)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val realPosition = if (withAreaName) position - 1 else position
        when(holder){
            is AreaViewHolder ->{
                holder.bind(mDataSource)
            }
            is RestaurantNameViewHolder ->{
                val restaurantNumber = if (withAreaName) position else position + 1
                holder.bind(restaurantNumber,mDataSource.restaurant[realPosition])
            }
        }
    }

    override fun getItemCount(): Int {
        return if (withAreaName) mDataSource.restaurant.size + 1 else mDataSource.restaurant.size
    }

    class AreaViewHolder(private val mBinding: ItemAreaBinding,private val mItemClickListener: ItemClickListener) : RecyclerView.ViewHolder(mBinding.root){

        fun bind(areaRestaurants: AreaRestaurants){
            mBinding.tvAreaName.text = areaRestaurants.area.areaName
            mBinding.btnEditRestaurant.setOnClickListener{
                mItemClickListener.onAreaEditClick(areaRestaurants)
            }
        }
    }

    class RestaurantNameViewHolder(private val mBinding: ItemRestaurantNameBinding,
                                   private val mItemClickListener: ItemClickListener,
                                   withCheckbox :Boolean = false) : RecyclerView.ViewHolder(mBinding.root){

        init {
            if (withCheckbox){
                mBinding.cbRestaurant.visibility = View.VISIBLE
            }else{
                mBinding.cbRestaurant.visibility = View.GONE
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(restaurantNumber :Int ,restaurant: Restaurant){
            mBinding.tvRestaurantName.text = "$restaurantNumber.${restaurant.restaurantName}"
            mBinding.cbRestaurant.isChecked = restaurant.isCheck
            mBinding.cbRestaurant.setOnCheckedChangeListener { _, isCheck ->
                restaurant.isCheck = isCheck
                mItemClickListener.onRestaurantCheckboxClick(isCheck,restaurant)
            }
        }
    }
}
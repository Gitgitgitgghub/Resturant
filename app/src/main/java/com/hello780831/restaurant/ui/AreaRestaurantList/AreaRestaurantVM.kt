package com.hello780831.restaurant.ui.AreaRestaurantList

import android.app.Application
import androidx.lifecycle.*
import com.hello780831.restaurant.database.RestaurantRepository
import com.hello780831.restaurant.model.Area
import com.hello780831.restaurant.model.AreaRestaurants
import com.hello780831.restaurant.model.Restaurant
import kotlinx.coroutines.launch

class AreaRestaurantVMFactory(private val application :Application, private val area: Area) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AreaRestaurantVM::class.java))
        {
            return AreaRestaurantVM(application,area) as T
        }
        throw IllegalArgumentException("error viewModel class")
    }
}

class AreaRestaurantVM(application: Application, private val area: Area) : AndroidViewModel(application) {

    val mAreaRestaurantLiveData = MutableLiveData<AreaRestaurants>()
    val mEditRestaurantLiveData = MutableLiveData<Restaurant?>()
    val mDrawLotRestaurantsLiveData = MutableLiveData<List<Restaurant>>()
    private var mAllCheck = false

    fun fetchAreaRestaurant(){
        viewModelScope.launch {
            mAreaRestaurantLiveData.value = AreaRestaurants(area,RestaurantRepository.getRestaurantsByArea(area))
        }
    }

    fun restaurantCheckChange(isCheck: Boolean, restaurant: Restaurant){
        viewModelScope.launch {
            restaurant.isCheck = isCheck
            RestaurantRepository.updateRestaurant(restaurant)
        }
    }

    fun clickAllSelect(){
        mAreaRestaurantLiveData.value?.let {
            viewModelScope.launch {
                mAllCheck = !mAllCheck
                it.restaurant.forEach {
                    it.isCheck = mAllCheck
                }
                RestaurantRepository.updateRestaurant(*it.restaurant.toTypedArray())
                fetchAreaRestaurant()
            }
        }
    }

    fun deleteCheckRestaurant(){
        viewModelScope.launch {
            RestaurantRepository.deleteCheckedRestaurant()
            fetchAreaRestaurant()
        }
    }

    fun editRestaurant(){
        val value = mAreaRestaurantLiveData.value?.restaurant
        if (value.isNullOrEmpty()) return
        var editRestaurant :Restaurant? = null
        var checkCount = 0
        value.forEach {
            if (it.isCheck){
                editRestaurant = it
                checkCount++
            }
        }
        mEditRestaurantLiveData.value = if (checkCount == 1) editRestaurant else null
    }

    fun drawLots(){
        val areaRestaurant = mAreaRestaurantLiveData.value?.restaurant
        if (areaRestaurant.isNullOrEmpty()) return
        val drawLotRestaurants = mutableListOf<Restaurant>()
        areaRestaurant.forEach {
            if (it.isCheck){
                drawLotRestaurants.add(it)
            }
        }
        if (drawLotRestaurants.isNotEmpty()){
            mDrawLotRestaurantsLiveData.value = drawLotRestaurants
        }
    }
}
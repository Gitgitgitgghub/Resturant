package com.hello780831.restaurant.ui.EditRestaurant

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.ArrayRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hello780831.restaurant.R
import com.hello780831.restaurant.databinding.ActivityEditRestaurantBinding
import com.hello780831.restaurant.model.Restaurant
import com.hello780831.restaurant.ui.CustomEditText
import java.lang.RuntimeException

class EditRestaurantActivity : AppCompatActivity() , View.OnClickListener,CustomEditText.TextChangedListener{

    companion object{

        const val DATA = "data"

        fun launchEditMode(context: Context,restaurant: Restaurant){
            context.startActivity(Intent(context, EditRestaurantActivity::class.java).apply {
                putExtra(DATA,restaurant)
            })
        }

        fun launchAddMode(context: Context){
            context.startActivity(Intent(context, EditRestaurantActivity::class.java))
        }
    }

    private lateinit var mBinding :ActivityEditRestaurantBinding
    private lateinit var mViewModel :EditRestaurantVM
    private var mHasParkSelectDialog :AlertDialog? = null
    private var mPriceSelectDialog :AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_edit_restaurant)
        mViewModel = ViewModelProvider(this).get(EditRestaurantVM::class.java)
        mViewModel.setRestaurant(getIntentData())
        initView()
    }

    private fun getIntentData() : Restaurant? {
        return intent.getParcelableExtra<Restaurant>(EditRestaurantActivity.DATA)
    }

    private fun initView(){
        setDefaultValue()
        setBtnDefaultText()
        mBinding.layoutInput.etRestaurantHasPark.setOnClickListener(this)
        mBinding.layoutInput.etRestaurantPrice.setOnClickListener(this)
        mBinding.btnApplyAdd.setOnClickListener(this)
        mBinding.btnCancelAdd.setOnClickListener(this)
        mBinding.layoutInput.etRestaurantPrice.setOnClickListener(this)
        mBinding.layoutInput.etAreaName.addTextChangedListener(this)
        mBinding.layoutInput.etRestaurantHasPark.addTextChangedListener(this)
        mBinding.layoutInput.etRestaurantPrice.addTextChangedListener(this)
        mBinding.layoutInput.etRestaurantName.addTextChangedListener(this)
        mBinding.layoutInput.etRestaurantPhone.addTextChangedListener(this)
        mBinding.layoutInput.etRestaurantAddress.addTextChangedListener(this)
        mBinding.layoutInput.etRestaurantGoogleRatio.addTextChangedListener(this)
        mBinding.layoutInput.etRestaurantMap.addTextChangedListener(this)
        mViewModel.mInputErrorMessageLiveData.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDefaultValue(){
        val defaultRestaurant = getIntentData() ?: Restaurant()
        mBinding.layoutInput.etAreaName.setText(defaultRestaurant.area)
        mBinding.layoutInput.etRestaurantName.setText(defaultRestaurant.restaurantName)
        mBinding.layoutInput.etRestaurantAddress.setText(defaultRestaurant.address)
        mBinding.layoutInput.etRestaurantPhone.setText(defaultRestaurant.phone)
        mBinding.layoutInput.etRestaurantHasPark.setText(if (defaultRestaurant.hasPark) "有" else "無")
        mBinding.layoutInput.etRestaurantPrice.setText(defaultRestaurant.price)
        mBinding.layoutInput.etRestaurantGoogleRatio.setText(defaultRestaurant.googleRatio.toString())
        mBinding.layoutInput.etRestaurantMap.setText(defaultRestaurant.mapLink)
    }

    private fun setBtnDefaultText(){
        mBinding.btnCancelAdd.text = if (getIntentData() == null) getString(R.string.cancelAdd) else getString(R.string.cancelEdit)
        mBinding.btnApplyAdd.text = if (getIntentData() == null) getString(R.string.addRestaurant) else getString(R.string.applyEdit)
    }

    override fun onClick(p0: View) {
        when(p0){
            mBinding.btnApplyAdd-> {
                if (getIntentData() == null){
                    mViewModel.addNewRestaurant()
                }else{
                    mViewModel.updateRestaurant()
                }
                finish()
            }
            mBinding.btnCancelAdd -> finish()
            mBinding.layoutInput.etRestaurantHasPark -> showHasParkSelectDialog()
            mBinding.layoutInput.etRestaurantPrice -> showPriceSelectDialog()
        }
    }

    override fun onTextChange(customEditText: CustomEditText, text: String) {
        mViewModel.onInputChange(customEditText.getTitle().toString(),text)
    }

    private fun showHasParkSelectDialog(){
        if (mHasParkSelectDialog == null){
            mHasParkSelectDialog = createSelectDialog(getString(R.string.restaurantPrice),
                R.array.hasParkOptionArray){ dialog: DialogInterface?, which: Int ->
                mBinding.layoutInput.etRestaurantHasPark.setText(resources.getStringArray(R.array.hasParkOptionArray)[which])
                dialog?.dismiss()
            }
        }
        mHasParkSelectDialog!!.show()
    }

    private fun showPriceSelectDialog(){
        if (mPriceSelectDialog == null){
            mPriceSelectDialog = createSelectDialog(getString(R.string.restaurantPrice),
                R.array.priceOptionArray){ dialog: DialogInterface?, which: Int ->
                mBinding.layoutInput.etRestaurantPrice.setText(resources.getStringArray(R.array.priceOptionArray)[which])
                dialog?.dismiss()
            }
        }
        mPriceSelectDialog!!.show()
    }

    private fun createSelectDialog(title :String, @ArrayRes itemsRes :Int, clickListener: DialogInterface.OnClickListener) :AlertDialog{
        return AlertDialog
            .Builder(this)
            .setTitle(title)
            .setSingleChoiceItems(itemsRes,0,clickListener)
            .create()
    }
}
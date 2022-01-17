package com.hello780831.restaurant.ui

import android.content.Context
import android.content.res.TypedArray
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.hello780831.restaurant.R


class CustomEditText(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    init {
        initView(attrs!!)
    }

    private lateinit var tvInputTitle :TextView
    private lateinit var etInput :EditText
    private var mTextChangedListener :TextChangedListener? = null

    private fun initView(attrs: AttributeSet){
        val inflate = LayoutInflater.from(context).inflate(R.layout.costom_edit_text, this, true)
        tvInputTitle = inflate.findViewById(R.id.tvInputTitle)
        etInput = inflate.findViewById(R.id.etInput)
        val typedArray : TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText)
        setTitle(typedArray.getString(R.styleable.CustomEditText_title))
        setInputType(typedArray.getInt(R.styleable.CustomEditText_android_inputType,87))
        focusable = typedArray.getInt(R.styleable.CustomEditText_android_focusable,1)
        typedArray.recycle()
    }

    fun setTitle(title :String?){
        if (title.isNullOrEmpty()){
            tvInputTitle.text = ""
        }else{
            tvInputTitle.text = title
        }
    }

    fun getTitle() = tvInputTitle.text

    fun setInputType(inputType :Int){
        if (inputType != 87){
            etInput.inputType = inputType
        }
    }

    override fun setFocusable(focusable :Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            etInput.focusable = focusable
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        etInput.setOnClickListener{
            l?.onClick(this)
        }
    }

    fun setText(text :String?){
        if (text == null){
            etInput.setText("")
        }else{
            etInput.setText(text)
        }
    }

    fun addTextChangedListener(textChangedListener: TextChangedListener){
        mTextChangedListener = textChangedListener
        etInput.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val text = if (s.isNullOrEmpty()) "" else s.toString()
                mTextChangedListener?.onTextChange(this@CustomEditText,text)
            }

        })
    }

    interface TextChangedListener{
        fun onTextChange(customEditText: CustomEditText ,text: String)
    }
}
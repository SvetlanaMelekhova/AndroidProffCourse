package com.svetlana.learn.androidproffcourse.presentation

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.svetlana.learn.androidproffcourse.R

@BindingAdapter("errorInputName")
fun bindErrorInputName(textInputLayout: TextInputLayout, isErrorBoolean: Boolean) {
    val message = if (isErrorBoolean) {
        textInputLayout.context.getString(R.string.error_input_name)
    } else {
        null
    }

    textInputLayout.error = message
}

@BindingAdapter("errorInputCount")
fun bindErrorInputCount(textInputLayout: TextInputLayout, isErrorBoolean: Boolean) {
    val message = if (isErrorBoolean) {
        textInputLayout.context.getString(R.string.error_input_count)
    } else {
        null
    }

    textInputLayout.error = message
}
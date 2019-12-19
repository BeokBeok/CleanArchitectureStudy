package com.beok.repobrowse.ext

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.BindingAdapter

@BindingAdapter("OnItemSelected")
fun Spinner.onItemSelectedListener(listener: (String) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit

        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            if (view == null) return
            listener.invoke(parent?.getItemAtPosition(position).toString())
        }
    }
}
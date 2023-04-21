package br.com.luishenrique.moviesbrasil.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.EditText

fun EditText.onClickRightIcon(onClick: () -> Unit) {
    this.setOnTouchListener { _, event ->
        val drawableRight = 2
        val rightPosition = this.right
        val width = this.compoundDrawables[drawableRight].bounds.width()

        if (event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= (rightPosition - width)) {
                onClick.invoke()
            }
        }
        false
    }
}

fun EditText.textChange(
    beforeTextChanged: (s: CharSequence?) -> Unit = {},
    onTextChanged: (s: CharSequence?) -> Unit = {},
    afterTextChanged: (s: Editable?) -> Unit = {},
) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
            beforeTextChanged.invoke(s)
        }

        override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            onTextChanged.invoke(text)
        }

        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s)
        }
    })
}
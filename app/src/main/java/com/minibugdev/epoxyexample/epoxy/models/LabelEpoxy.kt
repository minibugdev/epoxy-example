package com.minibugdev.epoxyexample.epoxy.models

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class LabelEpoxy @JvmOverloads constructor(
		context: Context,
		attrs: AttributeSet? = null,
		defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

	@TextProp
	fun setLabel(label: CharSequence?) {
		this.text = label
	}
}
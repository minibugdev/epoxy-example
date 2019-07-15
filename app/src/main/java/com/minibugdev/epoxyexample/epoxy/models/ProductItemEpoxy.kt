package com.minibugdev.epoxyexample.epoxy.models

import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.minibugdev.epoxyexample.R
import com.minibugdev.epoxyexample.data.Product
import com.minibugdev.epoxyexample.epoxy.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.row_product_item)
abstract class ProductItemEpoxy : EpoxyModelWithHolder<Holder>() {

	@EpoxyAttribute lateinit var product: Product

	override fun bind(holder: Holder) {
		with(holder) {
			Glide.with(this.productImage)
				.load(product.image)
				.centerCrop()
				.placeholder(R.color.colorPrimary)
				.into(this.productImage)

			this.productName.text = product.name
			this.productPrice.text = "%.2f THB".format(product.price)
		}
	}
}

class Holder : KotlinEpoxyHolder() {
	val productImage by bind<ImageView>(R.id.imageViewProductImage)
	val productName by bind<TextView>(R.id.textViewProductName)
	val productPrice by bind<TextView>(R.id.textViewProductPrice)
}
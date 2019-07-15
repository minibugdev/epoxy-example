package com.minibugdev.epoxyexample.epoxy

import android.util.Log
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.minibugdev.epoxyexample.R
import com.minibugdev.epoxyexample.data.Banner
import com.minibugdev.epoxyexample.data.Category
import com.minibugdev.epoxyexample.data.Product
import com.minibugdev.epoxyexample.epoxy.models.BannerEpoxyModel_
import com.minibugdev.epoxyexample.epoxy.models.GridCarouselModel_
import com.minibugdev.epoxyexample.epoxy.models.LabelEpoxyModel_
import com.minibugdev.epoxyexample.epoxy.models.ProductItemEpoxy_
import kotlin.properties.Delegates.observable

class Controller : EpoxyController() {
	init {
		Carousel.setDefaultGlobalSnapHelperFactory(null)
	}

	var megaBanner by observable(null as Banner?) { _, _, _ ->
		requestModelBuild()
	}

	var horizontalBanners by observable(emptyList<Banner>()) { _, _, _ ->
		requestModelBuild()
	}

	var categories by observable(emptyList<Category>()) { _, _, _ ->
		requestModelBuild()
	}

	var productItems by observable(emptyList<Product>()) { _, _, _ ->
		requestModelBuild()
	}

	override fun buildModels() {
		// MegaBanner
		BannerEpoxyModel_()
			.id("MEGA_BANNER")
			.imageUrl(megaBanner?.image)
			.clickListener { model, _, _, position ->
				Log.d("Controller", "Banner image: ${model.imageUrl()}")
			}
			.addTo(this)

		// Horizontal Banner
		val banners = horizontalBanners.map { banner ->
			BannerEpoxyModel_()
				.id(banner.id)
				.imageUrl(banner.image)
		}
		CarouselModel_()
			.id("HORIZONTAL_BANNER")
			.numViewsToShowOnScreen(2.4f)
			.paddingDp(PADDING_IN_DP)
			.models(banners)
			.addIf(banners.isNotEmpty(), this)

		// Category
		LabelEpoxyModel_()
			.id("CATEGORY_LABEL")
			.label(R.string.label_category)
			.addTo(this)

		GridCarouselModel_()
			.id("CATEGORY_LIST")
			.numViewsToShowOnScreen(3.2f)
			.paddingDp(PADDING_IN_DP)
			.models(categories.map { category ->
				BannerEpoxyModel_()
					.id(category.id)
					.imageUrl(category.image)
			})
			.addTo(this)

		// Promotion
		LabelEpoxyModel_()
			.id("PROMOTION_LABEL")
			.label(R.string.label_promotion)
			.addTo(this)

		CarouselModel_()
			.id("PROMOTION_LIST")
			.numViewsToShowOnScreen(2.2f)
			.paddingDp(PADDING_IN_DP)
			.models(productItems.map { product ->
				ProductItemEpoxy_()
					.id(product.id)
					.product(product)
			})
			.addTo(this)
	}

	companion object {
		private const val PADDING_IN_DP = 8
	}
}
package com.delivery.hero.productdetail

import android.content.res.Resources
import com.airbnb.mvrx.test.MvRxTestRule
import com.delivery.hero.NavigationViewBinding
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.ClassRule
import org.junit.Test

class ProductListViewBindingTest {

    private val resources = mock<Resources> {
        on { getString(R.string.app_name) } doReturn "Delivery Hero"
    }
    private val fragment = ProductDetailFragment()
    private val repository = mock<ProductDetailRepository> {
        on { fetchProduct(any()) } doReturn Single.just(
            Product(
                "1",
                "1",
                "Test",
                "Image",
                "Brand",
                true,
                Price(1.0, 2.0, "EUR")
            )
        )
    }
    private val viewModel = testViewModel(repository)
    private val viewBinding =
        ProductDetailViewBinding(
            fragment,
            viewModel,
            NavigationViewBinding(fragment),
            resources
        )

    @Test
    fun `given view binding initialised the title should be right one`() {
        assertThat(viewBinding.navigationViewBinding.toolbarTitle).isEqualTo("Test")
    }

    companion object {

        @JvmField
        @ClassRule
        val mvrxTestRule = MvRxTestRule()
    }
}

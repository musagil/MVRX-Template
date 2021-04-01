package com.delivery.hero.productdetail

import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.ClassRule
import org.junit.Test

class ProductListViewModelTest {

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

    @Test
    fun `given a product, should set proper state`() {
        withState(viewModel) {
            assertThat(it.product()).isEqualTo(
                ProductDetailItem(
                    "1",
                    "Test",
                    "Brand",
                    "Image",
                    Price(1.0, 2.0, "EUR")
                )
            )
        }
    }

    companion object {

        @JvmField
        @ClassRule
        val mvrxTestRule = MvRxTestRule()
    }
}

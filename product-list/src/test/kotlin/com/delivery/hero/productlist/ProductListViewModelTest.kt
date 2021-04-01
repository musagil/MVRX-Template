package com.delivery.hero.productlist

import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.ClassRule
import org.junit.Test

class ProductListViewModelTest {

    private val repository = mock<ProductListRepository> {
        on { fetchProducts() } doReturn Single.just(
            listOf(
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
        )
    }
    private val viewModel = testViewModel(repository)

    @Test
    fun `given that navigation to product detail, clicked id should be cleared`() {
        viewModel.onNavigatedToProductDetail()

        withState(viewModel) {
            assertThat(it.productClickedId).isNull()
        }
    }

    @Test
    fun `given list of products, should set proper state`() {
        withState(viewModel) {
            assertThat(it.products()).isEqualTo(
                listOf(
                    ProductListItem(
                        "1",
                        "Test",
                        "Brand",
                        "Image",
                        Price(1.0, 2.0, "EUR")
                    )
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

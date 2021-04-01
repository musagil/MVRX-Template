package com.delivery.hero.productlist

import android.content.res.Resources
import com.airbnb.mvrx.test.MvRxTestRule
import com.delivery.hero.FragmentNavigation
import com.delivery.hero.NavigationViewBinding
import com.delivery.hero.data.ProductInput
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.then
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.ClassRule
import org.junit.Test

class ProductListViewBindingTest {

    private val resources = mock<Resources> {
        on { getString(R.string.app_name) } doReturn "Delivery Hero"
    }
    private val fragment = ProductListFragment()
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
    private val viewBinding =
        ProductListViewBinding(
            fragment,
            viewModel,
            NavigationViewBinding(fragment),
            mock(),
            resources
        )

    @Test
    fun `given view binding initialised the title should be right one`() {
        assertThat(viewBinding.navigationViewBinding.toolbarTitle).isEqualTo("Delivery Hero")
    }

    @Test
    fun `given view binding initialised adapter should be product adapter`() {
        assertThat(viewBinding.adapter).isInstanceOf(ProductAdapter::class.java)
    }

    @Test
    fun `given view binding initialised loading indicator should be invisible`() {
        assertThat(viewBinding.shouldShowRefreshing).isFalse()
    }

    @Test
    fun `given productClickedId, should navigate`() {
        val viewModel = testViewModel(repository) {
            copy(productClickedId = "selectedId")
        }
        val navigation = mock<FragmentNavigation>()
        testViewBinding(fragment, viewModel = viewModel, navigation = navigation)

        then(navigation).should().navigate(
            R.id.productDetailFragment,
            ProductInput("selectedId")
        )
    }

    companion object {

        @JvmField
        @ClassRule
        val mvrxTestRule = MvRxTestRule()
    }
}

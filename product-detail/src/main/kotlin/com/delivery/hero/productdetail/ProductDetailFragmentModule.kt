package com.delivery.hero.productdetail

import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MvRx
import com.delivery.hero.data.ProductInput
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@AssistedModule
@Module(includes = [AssistedInject_ProductDetailFragmentModule::class])
abstract class ProductDetailFragmentModule {

    @Binds
    abstract fun bindFragment(fragment: ProductDetailFragment): Fragment

    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun provideRequests(retrofit: Retrofit) = retrofit.create<ProductDetailRequests>()

        @JvmStatic
        @Provides
        fun productInput(fragment: ProductDetailFragment): ProductInput {
            val arguments = fragment.requireArguments()
            // Deeplink
            if (arguments.containsKey("id")) {
                return ProductInput(arguments.getString("id") ?: "")
            }
            return arguments.getParcelable(MvRx.KEY_ARG)!!
        }
    }
}

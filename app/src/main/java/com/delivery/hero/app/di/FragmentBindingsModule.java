package com.delivery.hero.app.di;

import com.delivery.hero.productdetail.ProductDetailFragment;
import com.delivery.hero.productdetail.ProductDetailFragmentModule;
import com.delivery.hero.productlist.ProductListFragment;
import com.delivery.hero.productlist.ProductListFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
interface FragmentBindingsModule {

    @ContributesAndroidInjector(modules = ProductListFragmentModule.class)
    ProductListFragment productListFragment();

    @ContributesAndroidInjector(modules = ProductDetailFragmentModule.class)
    ProductDetailFragment productDetailFragment();

}

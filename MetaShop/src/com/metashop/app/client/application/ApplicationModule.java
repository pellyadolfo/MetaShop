package com.metashop.app.client.application;

import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.metashop.app.client.NameTokens;
import com.metashop.app.client.blog.BlogPresenter;
import com.metashop.app.client.blog.BlogView;
import com.metashop.app.client.blogsingle.BlogSinglePresenter;
import com.metashop.app.client.blogsingle.BlogSingleView;
import com.metashop.app.client.cart.CartPresenter;
import com.metashop.app.client.cart.CartView;
import com.metashop.app.client.checkout.CheckOutPresenter;
import com.metashop.app.client.checkout.CheckOutView;
import com.metashop.app.client.contactus.ContactUsPresenter;
import com.metashop.app.client.contactus.ContactUsView;
import com.metashop.app.client.error.ErrorPresenter;
import com.metashop.app.client.error.ErrorView;
import com.metashop.app.client.home.HomePresenter;
import com.metashop.app.client.home.HomeView;
import com.metashop.app.client.login.LoginPresenter;
import com.metashop.app.client.login.LoginView;
import com.metashop.app.client.productdetails.ProductDetailsPresenter;
import com.metashop.app.client.productdetails.ProductDetailsView;
import com.metashop.app.client.products.ProductsPresenter;
import com.metashop.app.client.products.ProductsView;
import com.metashop.app.client.widget.categories.CategoriesPresenter;
import com.metashop.app.client.widget.categories.CategoriesView;
import com.metashop.app.client.widget.featured.FeaturedPresenter;
import com.metashop.app.client.widget.featured.FeaturedView;
import com.metashop.app.client.widget.product.ProductPresenter;
import com.metashop.app.client.widget.product.ProductView;

/**
 * @author Joshua Godi
 */
public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
    	
        install(new RpcDispatchAsyncModule());

        // Main Application View
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);

        // eshoping
        bindPresenter(BlogPresenter.class, BlogPresenter.MyView.class, BlogView.class, BlogPresenter.MyProxy.class);
        bindPresenter(BlogSinglePresenter.class, BlogSinglePresenter.MyView.class, BlogSingleView.class, BlogSinglePresenter.MyProxy.class);
        bindPresenter(CartPresenter.class, CartPresenter.MyView.class, CartView.class, CartPresenter.MyProxy.class);
        bindPresenter(CheckOutPresenter.class, CheckOutPresenter.MyView.class, CheckOutView.class, CheckOutPresenter.MyProxy.class);
        bindPresenter(ContactUsPresenter.class, ContactUsPresenter.MyView.class, ContactUsView.class, ContactUsPresenter.MyProxy.class);
        bindPresenter(ErrorPresenter.class, ErrorPresenter.MyView.class, ErrorView.class, ErrorPresenter.MyProxy.class);
        bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);
        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class, LoginPresenter.MyProxy.class);
        bindPresenter(ProductDetailsPresenter.class, ProductDetailsPresenter.MyView.class, ProductDetailsView.class, ProductDetailsPresenter.MyProxy.class);
        bindPresenter(ProductsPresenter.class, ProductsPresenter.MyView.class, ProductsView.class, ProductsPresenter.MyProxy.class);
        
        // widgets
        bindPresenterWidget(ProductPresenter.class, ProductPresenter.MyView.class, ProductView.class);
        bindPresenterWidget(FeaturedPresenter.class, FeaturedPresenter.MyView.class, FeaturedView.class);
        bindPresenterWidget(CategoriesPresenter.class, CategoriesPresenter.MyView.class, CategoriesView.class);
        //bindPresenterWidget(BrandsPresenter.class, BrandsPresenter.MyView.class, BrandsView.class);
        
        // bind constants
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.HOME);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.ERROR);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.ERROR);
        

        //install(new ServerModule());
    }
}

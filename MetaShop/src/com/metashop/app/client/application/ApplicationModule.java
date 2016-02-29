package com.metashop.app.client.application;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2015 GwtBootstrap3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
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
import com.metashop.app.client.index.IndexPresenter;
import com.metashop.app.client.index.IndexView;
import com.metashop.app.client.login.LoginPresenter;
import com.metashop.app.client.login.LoginView;
import com.metashop.app.client.productdetails.ProductDetailsPresenter;
import com.metashop.app.client.productdetails.ProductDetailsView;
import com.metashop.app.client.shop.ShopPresenter;
import com.metashop.app.client.shop.ShopView;

/**
 * @author Joshua Godi
 */
public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        // Main Application View
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);

        // General Views
        bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);
        
        // eshoping
        bindPresenter(BlogPresenter.class, BlogPresenter.MyView.class, BlogView.class, BlogPresenter.MyProxy.class);
        bindPresenter(BlogSinglePresenter.class, BlogSinglePresenter.MyView.class, BlogSingleView.class, BlogSinglePresenter.MyProxy.class);
        bindPresenter(CartPresenter.class, CartPresenter.MyView.class, CartView.class, CartPresenter.MyProxy.class);
        bindPresenter(CheckOutPresenter.class, CheckOutPresenter.MyView.class, CheckOutView.class, CheckOutPresenter.MyProxy.class);
        bindPresenter(ContactUsPresenter.class, ContactUsPresenter.MyView.class, ContactUsView.class, ContactUsPresenter.MyProxy.class);
        bindPresenter(ErrorPresenter.class, ErrorPresenter.MyView.class, ErrorView.class, ErrorPresenter.MyProxy.class);
        bindPresenter(IndexPresenter.class, IndexPresenter.MyView.class, IndexView.class, IndexPresenter.MyProxy.class);
        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class, LoginPresenter.MyProxy.class);
        bindPresenter(ProductDetailsPresenter.class, ProductDetailsPresenter.MyView.class, ProductDetailsView.class, ProductDetailsPresenter.MyProxy.class);
        bindPresenter(ShopPresenter.class, ShopPresenter.MyView.class, ShopView.class, ShopPresenter.MyProxy.class);
    }
}

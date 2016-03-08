package com.metashop.app.client.blog;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
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

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.metashop.app.client.NameTokens;
import com.metashop.app.client.application.ApplicationPresenter;
import com.metashop.app.client.widget.brands.BrandsPresenter;
import com.metashop.app.client.widget.categories.CategoriesPresenter;
import com.metashop.app.data.Brand;
import com.metashop.app.data.Category;
import com.metashop.app.dispatch.GetBrandsRequest;
import com.metashop.app.dispatch.GetBrandsResult;
import com.metashop.app.dispatch.GetCategoriesRequest;
import com.metashop.app.dispatch.GetCategoriesResult;

public class BlogPresenter extends Presenter<BlogPresenter.MyView, BlogPresenter.MyProxy> implements BlogUiHandlers {
    @ProxyCodeSplit
    @NameToken(NameTokens.BLOG)
    public interface MyProxy extends ProxyPlace<BlogPresenter> {
    }
    
    public interface MyView extends View, HasUiHandlers<BlogUiHandlers> {
    }
    
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    @Inject
    public BlogPresenter(final EventBus eventBus,
                          final MyView view,
                          final MyProxy proxy,
                          PlaceManager placeManager,		// This comes here because Proxy has been declared as place
                          DispatchAsync dispatcher) {		// Need to include dispatcher stuff in .gwt.xml
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
        
        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        
        getView().setUiHandlers(this);

        // preload data
        loadCategories();
        loadBrands();
    }
    
    // load categories
    public static final Slot<CategoriesPresenter> SLOT_CATEGORIES = new Slot<CategoriesPresenter>();
    @Inject Provider<CategoriesPresenter> categoriesPresenterProvider;
    public void loadCategories() {
        dispatcher.execute(new GetCategoriesRequest("textToServer"), new AsyncCallback<GetCategoriesResult>() {
	        @Override
	        public void onFailure(Throwable caught) {
	            //getView().setServerResponse("An error occurred: " + caught.getMessage());
	        }
	
	        @Override
	        public void onSuccess(GetCategoriesResult result) {
            	for(int i = 0; i < result.getCategories().size(); i++) {
            		CategoriesPresenter categoriesPresenter = categoriesPresenterProvider.get();
            		categoriesPresenter.setCategory(result.getCategories().get(i));
    				getView().addToSlot(SLOT_CATEGORIES, categoriesPresenter);
            	}
	        }
	    });
    }
    
    // load brands
    public static final Slot<BrandsPresenter> SLOT_BRANDS = new Slot<BrandsPresenter>();
    @Inject Provider<BrandsPresenter> brandsPresenterProvider;
    public void loadBrands() {
        dispatcher.execute(new GetBrandsRequest("textToServer"), new AsyncCallback<GetBrandsResult>() {
            @Override
            public void onFailure(Throwable caught) {
                //getView().setServerResponse("An error occurred: " + caught.getMessage());
            }

            @Override
            public void onSuccess(GetBrandsResult result) {
            	
            	for(int i = 0; i < result.getBrands().size(); i++) {
            		BrandsPresenter brandsPresenter = brandsPresenterProvider.get();
            		brandsPresenter.setBrand(result.getBrands().get(i));
    				getView().addToSlot(SLOT_BRANDS, brandsPresenter);
            	}
            }
        });
    }
}

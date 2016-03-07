package com.metashop.app.client.productdetails;

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
import com.metashop.app.client.widget.product.ProductPresenter;
import com.metashop.app.data.Brand;
import com.metashop.app.data.Category;
import com.metashop.app.data.Product;
import com.metashop.app.dispatch.GetBrandsRequest;
import com.metashop.app.dispatch.GetBrandsResult;
import com.metashop.app.dispatch.GetCategoriesRequest;
import com.metashop.app.dispatch.GetCategoriesResult;
import com.metashop.app.dispatch.GetRecommendedRequest;
import com.metashop.app.dispatch.GetRecommendedResult;

public class ProductDetailsPresenter extends Presenter<ProductDetailsPresenter.MyView, ProductDetailsPresenter.MyProxy> implements ProductDetailsUiHandlers {
    @ProxyCodeSplit	
    @NameToken(NameTokens.PRODUCTDETAILS)
    public interface MyProxy extends ProxyPlace<ProductDetailsPresenter> {
    }
    
    public interface MyView extends View, HasUiHandlers<ProductDetailsUiHandlers> {
    	void setCategories(List<Category> categories);
    	void setBrands(List<Brand> brads);
    	//void setRecommended(List<Product> recommended);
    }
    
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    @Inject
    public ProductDetailsPresenter(final EventBus eventBus,
                          final MyView view,
                          final MyProxy proxy,
                          PlaceManager placeManager,		// This comes here because Proxy has been declared as place
                          DispatchAsync dispatcher) {		// Need to include dispatcher stuff in .gwt.xml
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
        
        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        
        getView().setUiHandlers(this);
        
        loadCategories();
        loadBrands();
        loadRecommended();
    }
    
    public void loadCategories() {
        dispatcher.execute(new GetCategoriesRequest("textToServer"), new AsyncCallback<GetCategoriesResult>() {
	        @Override
	        public void onFailure(Throwable caught) {
	            //getView().setServerResponse("An error occurred: " + caught.getMessage());
	        }
	
	        @Override
	        public void onSuccess(GetCategoriesResult result) {
	        	getView().setCategories(result.getCategories());
	        }
	    });
    }
    
    public void loadBrands() {
        dispatcher.execute(new GetBrandsRequest("textToServer"), new AsyncCallback<GetBrandsResult>() {
            @Override
            public void onFailure(Throwable caught) {
                //getView().setServerResponse("An error occurred: " + caught.getMessage());
            }

            @Override
            public void onSuccess(GetBrandsResult result) {
	        	getView().setBrands(result.getBrands());
            }
        });
    }
    
    public static final Slot<ProductPresenter> SLOT_RECOMMENDED1 = new Slot<ProductPresenter>();
    public static final Slot<ProductPresenter> SLOT_RECOMMENDED2 = new Slot<ProductPresenter>();

    @Inject Provider<ProductPresenter> productPresenterProvider;
    public void loadRecommended() {
        dispatcher.execute(new GetRecommendedRequest("textToServer"), new AsyncCallback<GetRecommendedResult>() {
            @Override
            public void onFailure(Throwable caught) {
                //getView().setServerResponse("An error occurred: " + caught.getMessage());
            }

            @Override
            public void onSuccess(GetRecommendedResult result) {
            	
            	for(int i = 0; i < 3; i++) {
            		ProductPresenter productPresenter1 = productPresenterProvider.get();
    				productPresenter1.setProduct(result.getProducts().get(i), 4);
    				getView().addToSlot(SLOT_RECOMMENDED1, productPresenter1);
    				
            		ProductPresenter productPresenter2 = productPresenterProvider.get();
    				productPresenter2.setProduct(result.getProducts().get(i), 4);
    				getView().addToSlot(SLOT_RECOMMENDED2, productPresenter2);
            	}
            }
        });
    }
    
    @Override
    public void sendName(String name) {
    	// TODO Auto-generated method stub
    	
    }
}
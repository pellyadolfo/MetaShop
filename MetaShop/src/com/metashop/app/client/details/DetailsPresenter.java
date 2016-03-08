package com.metashop.app.client.details;

import java.util.logging.Level;
import java.util.logging.Logger;

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
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.metashop.app.client.NameTokens;
import com.metashop.app.client.application.ApplicationPresenter;
import com.metashop.app.client.events.ShowDetailsEvent;
import com.metashop.app.client.events.ShowDetailsEvent.ShowDetailsHandler;
import com.metashop.app.client.widget.brands.BrandsPresenter;
import com.metashop.app.client.widget.categories.CategoriesPresenter;
import com.metashop.app.client.widget.product.ProductPresenter;
import com.metashop.app.dispatch.GetBrandsRequest;
import com.metashop.app.dispatch.GetBrandsResult;
import com.metashop.app.dispatch.GetCategoriesRequest;
import com.metashop.app.dispatch.GetCategoriesResult;
import com.metashop.app.dispatch.GetRecommendedRequest;
import com.metashop.app.dispatch.GetRecommendedResult;

public class DetailsPresenter extends Presenter<DetailsPresenter.MyView, DetailsPresenter.MyProxy> implements DetailsUiHandlers, ShowDetailsHandler {
    @ProxyCodeSplit	
    @NameToken(NameTokens.PRODUCTDETAILS)
    public interface MyProxy extends ProxyPlace<DetailsPresenter> {
    }
    
    public interface MyView extends View, HasUiHandlers<DetailsUiHandlers> {
    }
    
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    @Inject
    public DetailsPresenter(final EventBus eventBus,
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

    @ProxyEvent
	@Override
	public void onShowDetailsEvent(ShowDetailsEvent event) {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger("ppp");
		logger.log(Level.SEVERE, "process event");
	}
}
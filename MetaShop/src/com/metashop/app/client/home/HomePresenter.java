package com.metashop.app.client.home;

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
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.metashop.app.client.NameTokens;
import com.metashop.app.client.application.ApplicationPresenter;
import com.metashop.app.client.widget.featured.FeaturedPresenter;
import com.metashop.app.client.widget.product.ProductPresenter;
import com.metashop.app.data.Brand;
import com.metashop.app.data.Category;
import com.metashop.app.data.SubCategory;
import com.metashop.app.dispatch.GetBrandsRequest;
import com.metashop.app.dispatch.GetBrandsResult;
import com.metashop.app.dispatch.GetCategoriesRequest;
import com.metashop.app.dispatch.GetCategoriesResult;
import com.metashop.app.dispatch.GetFeaturedRequest;
import com.metashop.app.dispatch.GetFeaturedResult;
import com.metashop.app.dispatch.GetRecommendedRequest;
import com.metashop.app.dispatch.GetRecommendedResult;
import com.metashop.app.dispatch.GetSubCategoriesRequest;
import com.metashop.app.dispatch.GetSubCategoriesResult;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> implements HomeUiHandlers {
    @ProxyStandard
    @NameToken(NameTokens.HOME)
    public interface MyProxy extends ProxyPlace<HomePresenter> {
    }
    
    public interface MyView extends View, HasUiHandlers<HomeUiHandlers> {
    	void setCategories(List<Category> categories);
    	void setBrands(List<Brand> brads);
    	void setSubCategories(List<SubCategory> subcategories);
    }
    
    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     */
    public static final NestedSlot TYPE_CATEGORY = new NestedSlot();
    
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    // presenter is provided with all the information required to work
    // Inject marks the non default constructor
    @Inject
    public HomePresenter(final EventBus eventBus,
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
        loadFeatured();
        loadRecommended();
        loadSubCategories();
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
    
    // load featured
    public static final Slot<FeaturedPresenter> SLOT_FEATURED = new Slot<FeaturedPresenter>();
    @Inject Provider<FeaturedPresenter> featuredPresenterProvider;
    public void loadFeatured() {
        dispatcher.execute(new GetFeaturedRequest(6), new AsyncCallback<GetFeaturedResult>() {
            @Override
            public void onFailure(Throwable caught) {
                //getView().setServerResponse("An error occurred: " + caught.getMessage());
            }

            @Override
            public void onSuccess(GetFeaturedResult result) {
            	//getView().setFeatureds(result.getProducts());
            	
            	for(int i = 0; i < result.getProducts().size(); i++) {
            		FeaturedPresenter featuredPresenter = featuredPresenterProvider.get();
            		featuredPresenter.setProduct(result.getProducts().get(i), 4);
    				getView().addToSlot(SLOT_FEATURED, featuredPresenter);
            	}
            }
        });
    }

    // load recommended
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
    
    public void loadSubCategories() {
        dispatcher.execute(new GetSubCategoriesRequest("textToServer"), new AsyncCallback<GetSubCategoriesResult>() {
	        @Override
	        public void onFailure(Throwable caught) {
	            //getView().setServerResponse("An error occurred: " + caught.getMessage());
	        }
	
	        @Override
	        public void onSuccess(GetSubCategoriesResult result) {
	        	getView().setSubCategories(result.getSubCategories());
	        }
	    });
    }
}

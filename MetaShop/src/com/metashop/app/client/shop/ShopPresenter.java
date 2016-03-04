package com.metashop.app.client.shop;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.metashop.app.client.NameTokens;
import com.metashop.app.client.application.ApplicationPresenter;
import com.metashop.app.data.Brand;
import com.metashop.app.data.Category;
import com.metashop.app.data.Product;
import com.metashop.app.dispatch.GetBrandsRequest;
import com.metashop.app.dispatch.GetBrandsResult;
import com.metashop.app.dispatch.GetCategoriesRequest;
import com.metashop.app.dispatch.GetCategoriesResult;
import com.metashop.app.dispatch.GetFeaturedRequest;
import com.metashop.app.dispatch.GetFeaturedResult;

public class ShopPresenter extends Presenter<ShopPresenter.MyView, ShopPresenter.MyProxy> implements ShopUiHandlers {
    @ProxyCodeSplit
    @NameToken(NameTokens.SHOP)
    public interface MyProxy extends ProxyPlace<ShopPresenter> {
    }
    
    public interface MyView extends View, HasUiHandlers<ShopUiHandlers> {
    	void setCategories(List<Category> categories);
    	void setBrands(List<Brand> brads);
    	void setFeatured(List<Product> featured);
    }
    
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    @Inject
    public ShopPresenter(final EventBus eventBus,
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
    
    public void loadFeatured() {
        dispatcher.execute(new GetFeaturedRequest("12"), new AsyncCallback<GetFeaturedResult>() {
            @Override
            public void onFailure(Throwable caught) {
                //getView().setServerResponse("An error occurred: " + caught.getMessage());
            }

            @Override
            public void onSuccess(GetFeaturedResult result) {
            	getView().setFeatured(result.getProducts());
            }
        });
    }
    
    @Override
    public void sendName(String name) {
    	// TODO Auto-generated method stub
    	
    }
}
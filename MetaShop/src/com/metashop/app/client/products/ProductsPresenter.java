package com.metashop.app.client.products;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
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
import com.metashop.app.client.widget.featured.FeaturedPresenter;
import com.metashop.app.dispatch.GetBrandsRequest;
import com.metashop.app.dispatch.GetBrandsResult;
import com.metashop.app.dispatch.GetCategoriesRequest;
import com.metashop.app.dispatch.GetCategoriesResult;
import com.metashop.app.dispatch.GetFeaturedRequest;
import com.metashop.app.dispatch.GetFeaturedResult;

public class ProductsPresenter extends Presenter<ProductsPresenter.MyView, ProductsPresenter.MyProxy> implements ProductsUiHandlers {
    @ProxyCodeSplit
    @NameToken(NameTokens.SHOP)
    public interface MyProxy extends ProxyPlace<ProductsPresenter> {
    }
    
    public interface MyView extends View, HasUiHandlers<ProductsUiHandlers> {
    }
    
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    @Inject
    public ProductsPresenter(final EventBus eventBus,
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
            	Logger rootLogger = Logger.getLogger("");
            	rootLogger.log(Level.SEVERE, "pageIndex tres: ");	
            	
            	for(int i = 0; i < result.getBrands().size(); i++) {
            		BrandsPresenter brandsPresenter = brandsPresenterProvider.get();
            		brandsPresenter.setBrand(result.getBrands().get(i));
    				getView().addToSlot(SLOT_BRANDS, brandsPresenter);
            	}
            }
        });
    }
    
    // load featured
    public static final Slot<FeaturedPresenter> SLOT_FEATURED = new Slot<FeaturedPresenter>();
    @Inject Provider<FeaturedPresenter> featuredPresenterProvider;
    public void loadFeatured() {
        dispatcher.execute(new GetFeaturedRequest(12), new AsyncCallback<GetFeaturedResult>() {
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
    
    @Override
    public void sendName(String name) {
    	// TODO Auto-generated method stub
    	
    }
}
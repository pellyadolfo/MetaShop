package com.metashop.app.client.widget.brands;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.metashop.app.data.Brand;

public class BrandsPresenter extends PresenterWidget<BrandsPresenter.MyView> implements BrandsUiHandlers {
 
    public interface MyView extends View, HasUiHandlers<BrandsUiHandlers> {
    	void setBrand(Brand brandVO);
    }
    
    public void setBrand(Brand brand) {
        getView().setBrand(brand);
    }

    @Inject
    public BrandsPresenter(final EventBus eventBus, final MyView view) {
        super(eventBus, view);
        
        getView().setUiHandlers(this);
    }
    
    @Override
    public void showProductDetails(String name) {
    	// TODO Auto-generated method stub
    	
    }
}
package com.metashop.app.client.widget.featured;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.metashop.app.data.Product;

public class FeaturedPresenter extends PresenterWidget<FeaturedPresenter.MyView> implements FeaturedUiHandlers {
 
    public interface MyView extends View, HasUiHandlers<FeaturedUiHandlers> {
    	void setProduct(Product productVO, int slotsOf12);
    }
    
    public void setProduct(Product product, int slotsOf12) {
        getView().setProduct(product, slotsOf12);
    }

    @Inject
    public FeaturedPresenter(final EventBus eventBus, final MyView view) {
        super(eventBus, view);
        
        getView().setUiHandlers(this);
    }
    
    @Override
    public void showProductDetails(String name) {
    	// TODO Auto-generated method stub
    	
    }
}
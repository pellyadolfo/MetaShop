package com.metashop.app.client.widget.product;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.metashop.app.data.Product;

public class ProductPresenter extends PresenterWidget<ProductPresenter.MyView> implements ProductUiHandlers {
 
    public interface MyView extends View, HasUiHandlers<ProductUiHandlers> {
    	void setProduct(Product productVO, int slotsOf12);
    }
    
    public void setProduct(Product product, int slotsOf12) {
        getView().setProduct(product, slotsOf12);
    }

    @Inject
    public ProductPresenter(final EventBus eventBus, final MyView view) {
        super(eventBus, view);
        
        getView().setUiHandlers(this);
    }
    
    @Override
    public void showProductDetails() {
    	// TODO Auto-generated method stub
    	
    }
}
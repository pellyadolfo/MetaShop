package com.metashop.app.client.widget.product;

import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.metashop.app.data.Product;

public class ProductView extends ViewImpl implements ProductPresenter.MyView {
    
	interface Binder extends UiBinder<Widget, ProductView> {
    }
    
    @Inject
	public ProductView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }
    
	@Override
	public void setUiHandlers(ProductUiHandlers uiHandlers) {
		// TODO Auto-generated method stub
	}

    @UiField
    HTMLPanel productcell;
    
    @UiField
    ParagraphElement name;
    
    @UiField
    HeadingElement price;
    
    @UiField
    ImageElement image;
    
    public void setProduct(Product productVO, int slotsOf12) {
    	
    	productcell.setStyleName("col-sm-" + slotsOf12);
    	name.setInnerText(productVO.getName());
    	price.setInnerText(productVO.getPrice() + productVO.getCurrency());
    	image.setSrc(productVO.getUrl());
    }
}
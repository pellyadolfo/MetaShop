package com.metashop.app.client.widget.product;

import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.metashop.app.data.Product;

public class ProductView extends ViewWithUiHandlers<ProductUiHandlers> implements ProductPresenter.MyView {
    
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
    Label name;
    
    @UiField
    HeadingElement price;
    
    @UiField
    ImageElement image;
    
    @UiHandler("name")
    public void onClick(ClickEvent event) {
    	getUiHandlers().showProductDetails();
    }
    
    public void setProduct(Product productVO, int slotsOf12) {
    	
    	productcell.setStyleName("col-sm-" + slotsOf12);
    	name.setText(productVO.getName());
    	price.setInnerText(productVO.getPrice() + productVO.getCurrency());
    	image.setSrc(productVO.getUrl());
    }
}
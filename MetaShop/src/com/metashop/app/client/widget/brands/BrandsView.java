package com.metashop.app.client.widget.brands;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.metashop.app.data.Brand;

public class BrandsView extends Composite {
	
    interface Binder extends UiBinder<Widget, BrandsView> {
    }
    
    private static Binder binder = GWT.create(Binder.class);

    public BrandsView() {
        initWidget(binder.createAndBindUi(this));
    }
	
	@UiField 
	AnchorElement anchor;

    public BrandsView setBrand(Brand brandVO) {
    	
    	// need to do the getChild(0) because anchor cannot contain GWT elements
    	anchor.getChild(0).appendChild(Document.get().createTextNode("(" + brandVO.getCount() + ")"));
    	anchor.appendChild(Document.get().createTextNode(brandVO.getName()));

    	return this;
    }
}
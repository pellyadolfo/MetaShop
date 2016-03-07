package com.metashop.app.client.widget.brands;

import org.gwtbootstrap3.client.ui.html.Span;
import org.gwtbootstrap3.client.ui.html.Text;
import org.gwtbootstrap3.client.ui.html.UnorderedList;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.metashop.app.data.Brand;

public class BrandsView extends ViewImpl implements BrandsPresenter.MyView {
	
    interface Binder extends UiBinder<Widget, BrandsView> {
    }
    
    @Inject
    public BrandsView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }
    
	@Override
	public void setUiHandlers(BrandsUiHandlers uiHandlers) {
		// TODO Auto-generated method stub
		
	}
	
	@UiField
	Text amount;
	
	@UiField
	Text brand;

    public void setBrand(Brand brandVO) {
       	amount.setText("(" + brandVO.getCount() + ")");
    	brand.setText(brandVO.getName());
    }
}
package com.metashop.app.client.details;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.html.Text;

import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.metashop.app.data.Product;

public class DetailsView extends ViewWithUiHandlers<DetailsUiHandlers> implements DetailsPresenter.MyView {
    interface Binder extends UiBinder<Widget, DetailsView> {
    }

    @Inject
    DetailsView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    @UiField
    Text name;
    
    @UiField
    Text id;
    
    @UiField
    Text price;
    
    @UiField
    Text availability;
    
    @UiField
    Text condition;
    
    @UiField
    Text brand;
    
    @Override
    public void setProduct(Product product) {
		Logger logger = Logger.getLogger("ppp");
		logger.log(Level.SEVERE, "processing event" + product.getCondition());
    	
    	// TODO Auto-generated method stub
    	name.setText(product.getName());
    	id.setText("Web ID: " + product.getId());
    	price.setText(product.getPrice() + " " + product.getCurrency());
    	availability.setText(product.getAvailability());
    	condition.setText(product.getCondition());
    	brand.setText(product.getBrand());
    }
    
    // ********************************************************************************************
    // **************************************** Slots *********************************************
    // ********************************************************************************************
    
    @UiField
    FlowPanel categoriesPanel;
    
    @UiField
    UListElement brands;
    
    @UiField
    FlowPanel divrecommended1;
    
    @UiField
    FlowPanel divrecommended2;
    
    @Override
    public void addToSlot(final Object slot, final IsWidget content) {
    	if (slot == DetailsPresenter.SLOT_CATEGORIES)
        	categoriesPanel.add(content);
    	else if (slot == DetailsPresenter.SLOT_BRANDS)
    		brands.appendChild(content.asWidget().getElement().getChild(0));
    	else if (slot == DetailsPresenter.SLOT_RECOMMENDED1)	
        	divrecommended1.add(content);
        else if (slot == DetailsPresenter.SLOT_RECOMMENDED2)
        	divrecommended2.add(content);
        else super.addToSlot(slot, content);
    }
}

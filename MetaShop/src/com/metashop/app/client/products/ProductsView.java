package com.metashop.app.client.products;

import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ProductsView extends ViewWithUiHandlers<ProductsUiHandlers> implements ProductsPresenter.MyView {
    interface Binder extends UiBinder<Widget, ProductsView> {
    }

    @Inject
    ProductsView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    // ********************************************************************************************
    // **************************************** Slots *********************************************
    // ********************************************************************************************
    
    @UiField
    FlowPanel categoriesPanel;
    
    @UiField
    UListElement brands;
    
    @UiField
    FlowPanel featured;
    
    @Override
    public void addToSlot(final Object slot, final IsWidget content) {
    	if (slot == ProductsPresenter.SLOT_CATEGORIES)
        	categoriesPanel.add(content);
    	else if (slot == ProductsPresenter.SLOT_BRANDS)
    		brands.appendChild(content.asWidget().getElement().getChild(0));
    	else if (slot == ProductsPresenter.SLOT_FEATURED)
        	featured.add(content);
        else super.addToSlot(slot, content);
    }
}
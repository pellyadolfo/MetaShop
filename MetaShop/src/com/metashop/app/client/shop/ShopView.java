package com.metashop.app.client.shop;

import java.util.List;

import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.metashop.app.client.widgets.BrandsViewWidget;
import com.metashop.app.client.widgets.CategoriesViewWidget;
import com.metashop.app.data.Brand;
import com.metashop.app.data.Category;

public class ShopView extends ViewWithUiHandlers<ShopUiHandlers> implements ShopPresenter.MyView {
    interface Binder extends UiBinder<Widget, ShopView> {
    }

    @Inject
    ShopView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    // ********************************************************************************************
    // ************************************** Categories ******************************************
    // ********************************************************************************************
    
    @UiField
    FlowPanel categoriesPanel;
    
    @Override
    public void setCategories(List<Category> categories) {
		for (int i = 0; i < categories.size(); i++)
			categoriesPanel.add(new CategoriesViewWidget().setCategory(categories.get(i)));
    }

    // ********************************************************************************************
    // **************************************** Brands ********************************************
    // ********************************************************************************************
    
    @UiField
    UListElement ul;
    
    @Override
    public void setBrands(List<Brand> brands) {    	
		for (int i = 0; i < brands.size(); i++)
			ul.appendChild(new BrandsViewWidget().setBrand(brands.get(i)).getElement().getChild(0));
    }
}
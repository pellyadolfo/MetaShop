package com.metashop.app.client.home;

import java.util.List;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.UListElement;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.metashop.app.client.widgets.BrandsViewWidget;
import com.metashop.app.client.widgets.CategoriesViewWidget;
import com.metashop.app.client.widgets.FeaturedViewWidget;
import com.metashop.app.client.widgets.ProductViewWidget;
import com.metashop.app.data.Brand;
import com.metashop.app.data.Category;
import com.metashop.app.data.Product;

public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @Inject
    HomeView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    // ********************************************************************************************
    // ****************************************** Slot ********************************************
    // ********************************************************************************************
    
    @Override
    public void addToSlot(final Object slot, final IsWidget content) {
        if (slot == HomePresenter.TYPE_CATEGORY) {
        	categoriesPanel.add(content);
        } else {
            super.addToSlot(slot, content);
        }
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
    
    // ********************************************************************************************
    // **************************************** Featured ******************************************
    // ********************************************************************************************
    
    @UiField
    DivElement div;
    
    @Override
    public void setFeatureds(List<Product> featureds) {    	
		for (int i = 0; i < featureds.size(); i++)
			div.appendChild(new FeaturedViewWidget().setFeatured(featureds.get(i)).getElement());
    }
    
    // ********************************************************************************************
    // ************************************* Recommended ******************************************
    // ********************************************************************************************
    
    @UiField
    DivElement divrecommended1;
    DivElement divrecommended2;
    
    @Override
    public void setRecommended(List<Product> recommended) {
		for (int i = 0; i < recommended.size(); i++)
			divrecommended1.appendChild(new ProductViewWidget().setRecommended(recommended.get(i)).getElement());
		for (int i = 0; i < recommended.size(); i++)
			divrecommended2.appendChild(new ProductViewWidget().setRecommended(recommended.get(i)).getElement());
    }
}
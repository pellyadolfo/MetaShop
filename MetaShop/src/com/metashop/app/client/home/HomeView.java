package com.metashop.app.client.home;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.NavTabs;
import org.gwtbootstrap3.client.ui.TabContent;
import org.gwtbootstrap3.client.ui.TabListItem;
import org.gwtbootstrap3.client.ui.TabPane;
import org.gwtbootstrap3.client.ui.constants.Toggle;

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
import com.metashop.app.client.widget.brands.BrandsView;
import com.metashop.app.client.widget.categories.CategoriesView;
import com.metashop.app.client.widget.featured.FeaturedView;
import com.metashop.app.data.Brand;
import com.metashop.app.data.Category;
import com.metashop.app.data.Product;
import com.metashop.app.data.SubCategory;

public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @Inject
    HomeView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    // ********************************************************************************************
    // ****************************************** Slots *******************************************
    // ********************************************************************************************
    
    @UiField
    FlowPanel divrecommended1;
    
    @UiField
    FlowPanel divrecommended2;
    
    @Override
    public void addToSlot(final Object slot, final IsWidget content) {
        if (slot == HomePresenter.TYPE_CATEGORY)
        	categoriesPanel.add(content);
        else if (slot == HomePresenter.SLOT_RECOMMENDED1) {
        	Logger rootLogger = Logger.getLogger("pipo");
        	rootLogger.log(Level.SEVERE, "pageIndex selected45 " + content);	
        	divrecommended1.add(content);
        } else if (slot == HomePresenter.SLOT_RECOMMENDED2) {
        	Logger rootLogger = Logger.getLogger("pipo");
        	rootLogger.log(Level.SEVERE, "pageIndex selected45 " + content);	
        	divrecommended2.add(content);
        } else super.addToSlot(slot, content);
    }
    
    // ********************************************************************************************
    // ************************************** Categories ******************************************
    // ********************************************************************************************
    
    @UiField
    FlowPanel categoriesPanel;
    
    @Override
    public void setCategories(List<Category> categories) {
		for (int i = 0; i < categories.size(); i++)
			categoriesPanel.add(new CategoriesView().setCategory(categories.get(i)));
    }
    
    // ********************************************************************************************
    // **************************************** Brands ********************************************
    // ********************************************************************************************
    
    @UiField
    UListElement ul;
    
    @Override
    public void setBrands(List<Brand> brands) {    	
		for (int i = 0; i < brands.size(); i++)
			ul.appendChild(new BrandsView().setBrand(brands.get(i)).getElement().getChild(0));
    }
    
    // ********************************************************************************************
    // **************************************** Featured ******************************************
    // ********************************************************************************************
    
    @UiField
    DivElement div;
    
    @Override
    public void setFeatureds(List<Product> featureds) {    	
		for (int i = 0; i < featureds.size(); i++)
			div.appendChild(new FeaturedView().setFeatured(featureds.get(i)).getElement());
    }
    
    // ********************************************************************************************
    // ************************************ SubCategories *****************************************
    // ********************************************************************************************
    
    @UiField 
    NavTabs navs;
    
    @UiField
    TabContent tabContent;
    
    @Override
    public void setSubCategories(List<SubCategory> subcategories) {
    	for(int i = 0; i < subcategories.size(); i++) {
    		
    		// tablistitem
    		TabListItem tabListItem = new TabListItem();
    		tabListItem.setText(subcategories.get(i).getName());
    		tabListItem.setHref("#" + subcategories.get(i).getName().replace(" ", "").toLowerCase());
    		tabListItem.setDataToggle(Toggle.TAB);
    		navs.add(tabListItem);
    		
    		// add products below
			TabPane tabPane = new TabPane();
			tabPane.setFade(true);
			tabPane.setActive(i == 0);
			tabPane.setIn(i == 0);
			tabPane.setId(subcategories.get(i).getName().replace(" ", "").toLowerCase());
    		//for (int j = 0; j < subcategories.get(i).getProduct().size(); j++)
    		//	tabPane.add(new ProductView().setProduct(subcategories.get(i).getProduct().get(j), 3));
			//tabContent.add(tabPane);
    	}
    }
}
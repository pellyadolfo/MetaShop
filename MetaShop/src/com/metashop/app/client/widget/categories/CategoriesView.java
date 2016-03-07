package com.metashop.app.client.widget.categories;

import java.util.List;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.html.Text;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LIElement;
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
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.metashop.app.data.Brand;
import com.metashop.app.data.Category;

public class CategoriesView extends ViewImpl implements CategoriesPresenter.MyView {
	
    interface Binder extends UiBinder<Widget, CategoriesView> {
    }
    
    @Inject
    public CategoriesView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }
    
	@Override
	public void setUiHandlers(CategoriesUiHandlers uiHandlers) {
		// TODO Auto-generated method stub
		
	}
	
	@UiField 
	UListElement brands;
	
	@UiField 
	Anchor anchor;
	
	@UiField
	HTMLPanel subpanel;
	
	@UiField
	Element cross;

    public void setCategory(Category categoryVO) {
    	
    	// text
    	anchor.add(new Text(categoryVO.getName()));    	
    	
    	// collapsible effect
    	anchor.setHref("#" + categoryVO.getName().toLowerCase());
    	subpanel.getElement().setId(categoryVO.getName().toLowerCase());
    	
    	// brands
    	brands.removeAllChildren();
    	List<Brand> brandsVO = categoryVO.getBrands();
    	for (Brand brand : brandsVO) {    		
	    	LIElement li = Document.get().createLIElement();
	    	AnchorElement anchor = Document.get().createAnchorElement();
	    	anchor.setHref("#");
	    	anchor.setInnerHTML(brand.getName());
	    	li.appendChild(anchor);
	    	brands.appendChild(li);
    	}
    	
    	// cross
    	if(brandsVO.size() == 0)
    		cross.removeFromParent();
    }
}
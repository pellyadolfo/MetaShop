package com.metashop.app.client.home.categories;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
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
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.metashop.app.data.Brand;
import com.metashop.app.data.Category;

public class CategoriesViewWidget extends Composite {
	
	
    interface Binder extends UiBinder<Widget, CategoriesViewWidget> {
    }
    
    private static Binder binder = GWT.create(Binder.class);

    public CategoriesViewWidget() {
        initWidget(binder.createAndBindUi(this));
    }

	@UiField 
	Label category;
	
	@UiField 
	UListElement brands;

    public CategoriesViewWidget setCategory(Category categoryVO) {
    	category.setText(categoryVO.getName());
    	
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
    	
    	return this;
    }
}
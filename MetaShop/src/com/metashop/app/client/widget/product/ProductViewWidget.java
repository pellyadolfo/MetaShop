package com.metashop.app.client.widget.product;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.ParagraphElement;

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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.metashop.app.data.Product;

public class ProductViewWidget extends Composite {
	
    interface Binder extends UiBinder<Widget, ProductViewWidget> {
    }
    
    private static Binder binder = GWT.create(Binder.class);

    public ProductViewWidget() {
        initWidget(binder.createAndBindUi(this));
    }

    @UiField
    HTMLPanel productcell;
    
    @UiField
    ParagraphElement name;
    
    @UiField
    HeadingElement price;
    
    @UiField
    ImageElement image;
    
    public ProductViewWidget setRecommended(Product productVO, int slotsOf12) {
    	
    	productcell.setStyleName("col-sm-" + slotsOf12);
    	name.setInnerText(productVO.getName());
    	price.setInnerText(productVO.getPrice() + productVO.getCurrency());
    	image.setSrc(productVO.getUrl());
    	
    	return this;
    }
}
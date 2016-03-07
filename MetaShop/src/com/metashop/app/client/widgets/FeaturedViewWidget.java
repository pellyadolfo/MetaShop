package com.metashop.app.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

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
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.metashop.app.data.Product;

public class FeaturedViewWidget extends Composite {
	
	
    interface Binder extends UiBinder<Widget, FeaturedViewWidget> {
    }
    
    private static Binder binder = GWT.create(Binder.class);

    public FeaturedViewWidget() {
        initWidget(binder.createAndBindUi(this));
    }
	
    @UiField
    Label name;
    
    @UiField
    HeadingElement price;
    
    @UiField
    ImageElement image;
    
    @UiField
    ImageElement newItem;
    
    @UiField
    ImageElement sale;

    public FeaturedViewWidget setFeatured(Product productVO) {
    	
    	//name.setInnerText(productVO.getName());
    	name.setText(productVO.getName());
    	name.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				//placeManager.
			}
		});
    	price.setInnerText(productVO.getPrice() + productVO.getCurrency());
    	image.setSrc(productVO.getUrl());
    	
    	if (!productVO.isNew())
    		newItem.removeFromParent();
    	if (!productVO.isSale())
    		sale.removeFromParent();
    	
    	return this;
    }
}
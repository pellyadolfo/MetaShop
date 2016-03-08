package com.metashop.app.client.productdetails;

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
import com.metashop.app.client.home.HomePresenter;

public class ProductDetailsView extends ViewWithUiHandlers<ProductDetailsUiHandlers> implements ProductDetailsPresenter.MyView {
    interface Binder extends UiBinder<Widget, ProductDetailsView> {
    }

    @Inject
    ProductDetailsView(final Binder uiBinder) {
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
    FlowPanel divrecommended1;
    
    @UiField
    FlowPanel divrecommended2;
    
    @Override
    public void addToSlot(final Object slot, final IsWidget content) {
    	if (slot == ProductDetailsPresenter.SLOT_CATEGORIES)
        	categoriesPanel.add(content);
    	else if (slot == ProductDetailsPresenter.SLOT_BRANDS)
    		brands.appendChild(content.asWidget().getElement().getChild(0));
    	else if (slot == ProductDetailsPresenter.SLOT_RECOMMENDED1)	
        	divrecommended1.add(content);
        else if (slot == ProductDetailsPresenter.SLOT_RECOMMENDED2)
        	divrecommended2.add(content);
        else super.addToSlot(slot, content);
    }
}

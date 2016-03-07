package com.metashop.app.client.home;

import java.util.List;

import org.gwtbootstrap3.client.ui.NavTabs;
import org.gwtbootstrap3.client.ui.TabContent;
import org.gwtbootstrap3.client.ui.TabListItem;
import org.gwtbootstrap3.client.ui.TabPane;
import org.gwtbootstrap3.client.ui.constants.Toggle;

import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
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
    FlowPanel categoriesPanel;
    
    @UiField
    UListElement brands;
    
    @UiField
    FlowPanel divrecommended1;
    
    @UiField
    FlowPanel divrecommended2;
    
    @UiField
    FlowPanel featured;
    
    @Override
    public void addToSlot(final Object slot, final IsWidget content) {
    	if (slot == HomePresenter.SLOT_CATEGORIES)
        	categoriesPanel.add(content);
    	else if (slot == HomePresenter.SLOT_BRANDS)
    		brands.appendChild(content.asWidget().getElement().getChild(0));
    	else if (slot == HomePresenter.SLOT_RECOMMENDED1)
        	divrecommended1.add(content);
        else if (slot == HomePresenter.SLOT_RECOMMENDED2)
        	divrecommended2.add(content);
        else if (slot == HomePresenter.SLOT_FEATURED)
        	featured.add(content);
        else super.addToSlot(slot, content);
    }
    
    // ********************************************************************************************
    // ************************************ SubCategories *****************************************
    // ********************************************************************************************
    
    @UiField 
    NavTabs subcategoriesnavs;
    
    @UiField
    TabContent subcategoriesTabContent;
    
    @Override
    public void setSubCategories(List<SubCategory> subcategories) {
    	for(int i = 0; i < subcategories.size(); i++) {
    		
    		// tablistitem
    		TabListItem tabListItem = new TabListItem();
    		tabListItem.setText(subcategories.get(i).getName());
    		tabListItem.setHref("#" + subcategories.get(i).getName().replace(" ", "").toLowerCase());
    		tabListItem.setDataToggle(Toggle.TAB);
    		subcategoriesnavs.add(tabListItem);
    		
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
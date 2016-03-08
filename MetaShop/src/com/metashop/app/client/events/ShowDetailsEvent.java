package com.metashop.app.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.metashop.app.data.Product;

public class ShowDetailsEvent extends GwtEvent<ShowDetailsEvent.ShowDetailsHandler> {
    public interface ShowDetailsHandler extends EventHandler {
        void onShowDetailsEvent(ShowDetailsEvent event);
    }

    public static final Type<ShowDetailsHandler> TYPE = new Type<>();
    
    public static Type<ShowDetailsHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ShowDetailsHandler> getAssociatedType() {
        return TYPE;
    }
    
    private Product product;

    public Product getProduct() {
		return product;
	}

	public ShowDetailsEvent(Product product) {
        this.product = product;
    }
    
    public static void fire(HasHandlers source, Product product) {
        source.fireEvent(new ShowDetailsEvent(product));
    }

    @Override
    protected void dispatch(ShowDetailsHandler handler) {
        handler.onShowDetailsEvent(this);
    }
}
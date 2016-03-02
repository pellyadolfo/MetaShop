package com.metashop.app.client;

import com.arcbees.analytics.client.AnalyticsModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.metashop.app.client.application.ApplicationModule;

/**
 * @author Joshua Godi
 */
public class ClientModule extends AbstractPresenterModule {
    private static final String ANALYTICS_ACCOUNT = "UA-46636703-1";

    @Override
    protected void configure() {
        install(new DefaultModule());
        install(new ApplicationModule());
        install(new AnalyticsModule.Builder(ANALYTICS_ACCOUNT).build());
    }
}

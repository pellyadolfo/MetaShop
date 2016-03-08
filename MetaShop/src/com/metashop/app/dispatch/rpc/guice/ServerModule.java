/*
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.metashop.app.dispatch.rpc.guice;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;
import com.metashop.app.dispatch.GetBrandsRequest;
import com.metashop.app.dispatch.GetCategoriesRequest;
import com.metashop.app.dispatch.GetDetailsRequest;
import com.metashop.app.dispatch.GetFeaturedRequest;
import com.metashop.app.dispatch.GetRecommendedRequest;
import com.metashop.app.dispatch.GetSubCategoriesRequest;
import com.metashop.app.middleware.GetBrandsHandler;
import com.metashop.app.middleware.GetCategoriesHandler;
import com.metashop.app.middleware.GetDetailsHandler;
import com.metashop.app.middleware.GetFeaturedHandler;
import com.metashop.app.middleware.GetRecommendedHandler;
import com.metashop.app.middleware.GetSubCategoriesHandler;

/**
 * Module which binds the handlers and configurations. Here is GIN in action, in the XXXModules
 */
public class ServerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
        bindHandler(GetCategoriesRequest.class, GetCategoriesHandler.class);
        bindHandler(GetBrandsRequest.class, GetBrandsHandler.class);
        bindHandler(GetFeaturedRequest.class, GetFeaturedHandler.class);
        bindHandler(GetRecommendedRequest.class, GetRecommendedHandler.class);
        bindHandler(GetSubCategoriesRequest.class, GetSubCategoriesHandler.class);
        bindHandler(GetDetailsRequest.class, GetDetailsHandler.class);
    }
}

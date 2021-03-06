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

package com.metashop.app.middleware;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.metashop.app.data.Product;
import com.metashop.app.dispatch.GetFeaturedRequest;
import com.metashop.app.dispatch.GetFeaturedResult;

public class GetFeaturedHandler extends AHandler implements ActionHandler<GetFeaturedRequest, GetFeaturedResult> {
    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;

    @Inject
    GetFeaturedHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
    }

    @Override
    public GetFeaturedResult execute(GetFeaturedRequest action, ExecutionContext context) throws ActionException {
        List<Product> featured = getDao().getFeatured(action);
        GetFeaturedResult result = new GetFeaturedResult(featured);
        return result;
    }

    @Override
    public Class<GetFeaturedRequest> getActionType() {
        return GetFeaturedRequest.class;
    }

    @Override
    public void undo(GetFeaturedRequest action, GetFeaturedResult result, ExecutionContext context) throws ActionException {
        // Not undoable
    }
}

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
import com.metashop.app.data.SubCategory;
import com.metashop.app.dispatch.GetSubCategoriesRequest;
import com.metashop.app.dispatch.GetSubCategoriesResult;

public class GetSubCategoriesHandler extends AHandler implements ActionHandler<GetSubCategoriesRequest, GetSubCategoriesResult> {
    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;
    
    @Inject
    GetSubCategoriesHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
    }

    @Override
    public GetSubCategoriesResult execute(GetSubCategoriesRequest action, ExecutionContext context) throws ActionException {

        // add categories
        List<SubCategory> subCategories = getDao().getSubCategories(action);
        GetSubCategoriesResult result = new GetSubCategoriesResult(subCategories);

        return result;
    }

    @Override
    public Class<GetSubCategoriesRequest> getActionType() {
        return GetSubCategoriesRequest.class;
    }

    @Override
    public void undo(GetSubCategoriesRequest action, GetSubCategoriesResult result, ExecutionContext context) throws ActionException {
        // Not undoable
    }
}

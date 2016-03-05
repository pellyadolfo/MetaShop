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
import com.metashop.app.data.Brand;
import com.metashop.app.dispatch.GetBrandsRequest;
import com.metashop.app.dispatch.GetBrandsResult;
import com.metashop.app.server.dao.HardcodedDao;

public class GetBrandsHandler implements ActionHandler<GetBrandsRequest, GetBrandsResult> {
    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;

    @Inject
    GetBrandsHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
    }

    @Override
    public GetBrandsResult execute(GetBrandsRequest action, ExecutionContext context) throws ActionException {
        // fetch brands
        List<Brand> brands = new HardcodedDao().getBrands(action);
        GetBrandsResult result = new GetBrandsResult(brands);

        return result;
    }

    @Override
    public Class<GetBrandsRequest> getActionType() {
        return GetBrandsRequest.class;
    }

    @Override
    public void undo(GetBrandsRequest action, GetBrandsResult result, ExecutionContext context) throws ActionException {
        // Not undoable
    }
}

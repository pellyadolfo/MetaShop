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

package com.metashop.app.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.metashop.app.data.Product;
import com.metashop.app.dispatch.GetRecommendedRequest;
import com.metashop.app.dispatch.GetRecommendedResult;

public class GetRecommendedHandler implements ActionHandler<GetRecommendedRequest, GetRecommendedResult> {
    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;

    @Inject
    GetRecommendedHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
    }

    @Override
    public GetRecommendedResult execute(GetRecommendedRequest action, ExecutionContext context) throws ActionException {
        Logger logger = Logger.getLogger("clase");
        logger.log(Level.SEVERE, "count: " + action.getTextToServer());
        System.out.println("count: " + action.getTextToServer());
    	
        List<Product> recommended = new ArrayList<Product>();
 		recommended.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/recommend1.jpg"));
 		recommended.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/recommend2.jpg"));   
    	recommended.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/recommend3.jpg"));
   	
    	GetRecommendedResult result = new GetRecommendedResult(recommended);
        return result;
    }

    @Override
    public Class<GetRecommendedRequest> getActionType() {
        return GetRecommendedRequest.class;
    }

    @Override
    public void undo(GetRecommendedRequest action, GetRecommendedResult result, ExecutionContext context) throws ActionException {
        // Not undoable
    }
}

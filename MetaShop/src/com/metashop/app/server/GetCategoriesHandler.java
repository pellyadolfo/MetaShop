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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.metashop.app.data.Brand;
import com.metashop.app.data.Category;
import com.metashop.app.dispatch.GetCategoriesRequest;
import com.metashop.app.dispatch.GetCategoriesResult;

public class GetCategoriesHandler implements ActionHandler<GetCategoriesRequest, GetCategoriesResult> {
    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;

    @Inject
    GetCategoriesHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
    }

    @Override
    public GetCategoriesResult execute(GetCategoriesRequest action, ExecutionContext context) throws ActionException {
        String input = action.getTextToServer();

        // add categories
        List<Category> categories = new ArrayList<Category>();
        categories.add(new Category().setName("Sportswear").addBrand(new Brand().setName("Nike")).addBrand(new Brand().setName("Nike")).addBrand(new Brand().setName("Under Armour")).addBrand(new Brand().setName("Adidas")).addBrand(new Brand().setName("Puma")).addBrand(new Brand().setName("ASICS")));
        categories.add(new Category().setName("Mens").addBrand(new Brand().setName("Fendi")).addBrand(new Brand().setName("Guess")).addBrand(new Brand().setName("Valentino")).addBrand(new Brand().setName("Dior")).addBrand(new Brand().setName("Versace")).addBrand(new Brand().setName("Armani")).addBrand(new Brand().setName("Prada")).addBrand(new Brand().setName("Dolce and Gabbana")).addBrand(new Brand().setName("Chanel")).addBrand(new Brand().setName("Gucci")));
        categories.add(new Category().setName("Womens").addBrand(new Brand().setName("Fendi")).addBrand(new Brand().setName("Guess")).addBrand(new Brand().setName("Valentino")).addBrand(new Brand().setName("Dior")).addBrand(new Brand().setName("Versace")));
        categories.add(new Category().setName("Kids "));
        categories.add(new Category().setName("Fashion"));        
        categories.add(new Category().setName("Households"));        
        categories.add(new Category().setName("Interiors"));        
        categories.add(new Category().setName("Clothing"));        
        categories.add(new Category().setName("Bags"));        
        categories.add(new Category().setName("Shoes"));        
        GetCategoriesResult result = new GetCategoriesResult(categories);

        return result;
    }

    @Override
    public Class<GetCategoriesRequest> getActionType() {
        return GetCategoriesRequest.class;
    }

    @Override
    public void undo(GetCategoriesRequest action, GetCategoriesResult result, ExecutionContext context) throws ActionException {
        // Not undoable
    }
}

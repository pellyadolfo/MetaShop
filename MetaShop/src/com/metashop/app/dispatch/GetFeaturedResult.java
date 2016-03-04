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

package com.metashop.app.dispatch;

import java.util.List;

import com.gwtplatform.dispatch.rpc.shared.Result;
import com.metashop.app.data.Product;

/**
 * The result of a {@link GetCategoriesRequest} action.
 */
public class GetFeaturedResult implements Result {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Product> products;

    public GetFeaturedResult(final List<Product> products) {
        this.products = products;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private GetFeaturedResult() {
    }

    public List<Product> getProducts() {
        return products;
    }
}

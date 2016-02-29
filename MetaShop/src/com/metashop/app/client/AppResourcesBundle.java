/*
 * Copyright 2015 ArcBees Inc.
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

package com.metashop.app.client;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2016 GwtBootstrap3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface AppResourcesBundle extends ClientBundle {
	
    static final AppResourcesBundle INSTANCE = GWT.create(AppResourcesBundle.class);

    // **************************************************************************************************************************
    // ***************************************************************** CSS ****************************************************
    // **************************************************************************************************************************
    /*interface Style extends CssResource {
    }

    @Source("css/animate.css")
    Style animateCSS();
    
    @Source("css/bootstrap.min.css")
    Style bootstrapCSS();
    
    @Source("css/demo.css")
    Style demoCSS();
    
    @Source("css/font-awesome.min.css")
    Style fontAwesomeCSS();
    
    @Source("css/main.css")
    Style mainCSS();

    @Source("css/prettify.css")
    Style prettifyCSS();
    
    @Source("css/prettyPhoto.css")
    Style prettyPhotoCSS();
    
    @Source("css/price-range.css")
    Style priceRangeCSS();
    
    @Source("css/responsive.css")
    Style responsiveCSS();
    
    // **************************************************************************************************************************
    // ****************************************************************** JS ****************************************************
    // **************************************************************************************************************************
    @Source("js/prettify.js")
    TextResource prettifyJS();
    
    @Source("js/contact.js")
    TextResource contactJS();
    
    @Source("js/gmaps.js")
    TextResource gmapsJS();
    
    @Source("js/html5shiv.js")
    TextResource html5shivJS();
    
    @Source("js/jquery.js")
    TextResource jqueryJS();
    
    @Source("js/jquery.prettyPhoto.js")
    TextResource prettyPhotoJS();
    
    @Source("js/jquery.scrollUp.min.js")
    TextResource scrollUpJS();
    
    @Source("js/lang-css.js")
    TextResource langcssJS();
    
    @Source("js/main.js")
    TextResource mainJS();
    
    @Source("js/price-range.js")
    TextResource pricerangeJS();*/
}
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

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

// requires:
//	- guice-servlet-3.0jar
//	- gwtp-dispatch-rpc-client-1.5.jar
//	- gwtp-dispatch-common-client-1.5.jar
//	- gwtp-dispatch-rpc-server-1.5.jar
//	- gwtp-dispatch-rpc-server-guice-1.5
//	- gwtp-dispatch-rpc-shared-1.5.jar
//	- gwtp-dispatch-common-shared-1.5.jar
//	- listener and filter in web.xml
//	- install(new RpcDispatchAsyncModule());
//	- <inherits name='com.gwtplatform.dispatch.Dispatch'/>
// 	- GIN for client side, guice for server side
public class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServerModule(), new DispatchServletModule());
    }
}

package com.metashop.app.middleware;

import com.metashop.app.server.IServicesFacade;
import com.metashop.app.server.dao.MongoDBDao;

public class AHandler {

	protected IServicesFacade getDao() {
		//return new HardcodedDao();
		return new MongoDBDao();
	}
}
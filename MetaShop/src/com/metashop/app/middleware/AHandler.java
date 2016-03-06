package com.metashop.app.middleware;

import com.metashop.app.server.AServicesFacade;
import com.metashop.app.server.dao.MongoDBDao;

public class AHandler {
	
	AServicesFacade dao = null;

	protected AServicesFacade getDao() {
		if (dao == null)
			dao = new MongoDBDao();
		return dao;
	}
}
package com.metashop.app.server.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.metashop.app.data.Brand;
import com.metashop.app.data.Category;
import com.metashop.app.data.Product;
import com.metashop.app.data.SubCategory;
import com.metashop.app.dispatch.GetBrandsRequest;
import com.metashop.app.dispatch.GetCategoriesRequest;
import com.metashop.app.dispatch.GetFeaturedRequest;
import com.metashop.app.dispatch.GetRecommendedRequest;
import com.metashop.app.dispatch.GetSubCategoriesRequest;
import com.metashop.app.server.AServicesFacade;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBDao extends AServicesFacade {
	
	@Override
	public List<Brand> getBrandsImpl(GetBrandsRequest action) {
		// run query
		DBCollection collection = getDatabase().getCollection("product");
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$maker.name").append("count", new BasicDBObject("$sum", 1)));
		BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("count", -1));
		BasicDBObject limit = new BasicDBObject("$limit", 7);
	    Iterable<DBObject> output = collection.aggregate(Arrays.asList(group, sort, limit)).results();
	    
	    // create output
	    final List<Brand> result = new ArrayList<Brand>();
	    for (DBObject dbObject : output)
			result.add(new Brand().setName("" + dbObject.get("_id")).setCount(Integer.parseInt(dbObject.get("count") + "")));

		return result;
	}
	
	@Override
	public List<Category> getCategoriesImpl(GetCategoriesRequest action) {
		// run query
		DBCollection collection = getDatabase().getCollection("product");
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$os.ver").append("count", new BasicDBObject("$sum", 1)));
		BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("count", -1));
		BasicDBObject limit = new BasicDBObject("$limit", 10);		
	    Iterable<DBObject> output = collection.aggregate(Arrays.asList(group, sort, limit)).results();
	    
	    // create output
		final List<Category> result = new ArrayList<Category>();
	    for (DBObject dbObject : output)
			result.add(new Category().setName("Android " + dbObject.get("_id") + " (" + dbObject.get("count") + ")"));

		return result;
	}
	
	@Override
	public List<Product> getFeaturedImpl(GetFeaturedRequest action) {
		// run query
		DBCollection collection = getDatabase().getCollection("product");
		DBCursor cursor = collection.find().limit(6);
		
		// create output
		List<Product> products = new ArrayList<Product>();
		while (cursor.hasNext()) {
			DBObject record = cursor.next();
			String name = (String) ((DBObject) record.get("name")).get("id");			
			products.add(new Product().setName(name).setPrice(56).setCurrency("$").setUrl("images/photo/" + name.replace(" ", "_") +  ".jpg"));
		}

		return products;
	}
	
	@Override
	public List<Product> getRecommendedImpl(GetRecommendedRequest action) {
		// run query
		DBCollection collection = getDatabase().getCollection("product");
		DBCursor cursor = collection.find().limit(3);

		// create output
		List<Product> products = new ArrayList<Product>();
		while (cursor.hasNext()) {
			DBObject record = cursor.next();
			String name = (String) ((DBObject) record.get("name")).get("id");
			products.add(new Product().setName(name).setPrice(56).setCurrency("$").setUrl("images/photo/" + name.replace(" ", "_") +  ".jpg"));
		}
		
		return products;
	}
	
	@Override
	public List<SubCategory> getSubCategoriesImpl(GetSubCategoriesRequest action) {
		// run query
		DBCollection collection = getDatabase().getCollection("product");
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$os.type").append("docs", new BasicDBObject("$push", "$$ROOT")).append("count", new BasicDBObject("$sum", 1)));
		BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("count", -1));
		BasicDBObject limit = new BasicDBObject("$limit", 5);		
	    Iterable<DBObject> output = collection.aggregate(Arrays.asList(group, sort, limit)).results();

	    // create output
	    final List<SubCategory> result = new ArrayList<SubCategory>();
	    for (DBObject dbObject : output) {
	        SubCategory subCategory = new SubCategory().setName("" + dbObject.get("_id"));
	        for (int i = 0; i < 4; i++) {
	        	BasicDBObject item = (BasicDBObject) ((BasicDBList)dbObject.get("docs")).get(i);
	        	BasicDBObject name = (BasicDBObject) item.get("name");
	        	subCategory.addProduct(new Product().setName(name.get("id") + ""));
	        }
			result.add(subCategory);
	    }

		return result;
	}
	
	// *************************************************************************************************
	// Manage Connection
	// *************************************************************************************************
	private static MongoClient mongoClient = null;
	private static DB db = null;
	@Override
	protected void pre() {
		System.out.println("open");
		if (mongoClient == null)
			mongoClient = new MongoClient( "localhost" , 27017 );
		
	}
	@Override
	protected void post() {
		mongoClient.close();
		db = null;
	}
	private DB getDatabase() {
		if (db == null)
			db = mongoClient.getDB( "comparephone" );
		return db;
	}
}
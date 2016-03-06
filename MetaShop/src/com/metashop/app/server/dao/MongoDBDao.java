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
import com.metashop.app.server.IServicesFacade;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBDao implements IServicesFacade {
	
	@Override
	public List<Brand> getBrands(GetBrandsRequest action) {
		final List<Brand> result = new ArrayList<Brand>();
		
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		DB db = mongoClient.getDB( "comparephone" );
		DBCollection collection = db.getCollection("product");
		
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$maker.name").append("count", new BasicDBObject("$sum", 1)));
		BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("count", -1));
		BasicDBObject limit = new BasicDBObject("$limit", 7);
	    
	    Iterable<DBObject> output = collection.aggregate(Arrays.asList(group, sort, limit)).results();
	    for (DBObject dbObject : output) {
	        System.out.println(dbObject);
			result.add(new Brand().setName("" + dbObject.get("_id")).setCount(Integer.parseInt(dbObject.get("count") + "")));
	    }
	    
	    mongoClient.close();

		return result;
	}
	
	@Override
	public List<Category> getCategories(GetCategoriesRequest action) {
		final List<Category> result = new ArrayList<Category>();
		
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		DB db = mongoClient.getDB("comparephone");
		DBCollection collection = db.getCollection("product");
		
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$os.ver").append("count", new BasicDBObject("$sum", 1)));
		BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("count", -1));
		BasicDBObject limit = new BasicDBObject("$limit", 10);
		
	    Iterable<DBObject> output = collection.aggregate(Arrays.asList(group, sort, limit)).results();
	    for (DBObject dbObject : output) {
	        System.out.println(dbObject);
			result.add(new Category().setName("Android " + dbObject.get("_id") + " (" + dbObject.get("count") + ")"));
	    }

	    mongoClient.close();

		return result;
	}
	
	@Override
	public List<Product> getFeatured(GetFeaturedRequest action) {
		List<Product> products = new ArrayList<Product>();
		
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		DB db = mongoClient.getDB( "comparephone" );
		DBCollection collection = db.getCollection("product");
		
		DBCursor cursor = collection.find().limit(6);
		while (cursor.hasNext()) {
			DBObject record = cursor.next();
			String name = (String) ((DBObject) record.get("name")).get("id");
			
			products.add(new Product().setName(name).setPrice(56).setCurrency("$").setUrl("images/photo/" + name.replace(" ", "_") +  ".jpg"));
		}

	    mongoClient.close();

		return products;
	}
	
	@Override
	public List<Product> getRecommended(GetRecommendedRequest action) {
		List<Product> products = new ArrayList<Product>();
		
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		DB db = mongoClient.getDB( "comparephone" );
		DBCollection collection = db.getCollection("product");
		
		DBCursor cursor = collection.find().limit(3);
		while (cursor.hasNext()) {
			DBObject record = cursor.next();
			String name = (String) ((DBObject) record.get("name")).get("id");
			
			products.add(new Product().setName(name).setPrice(56).setCurrency("$").setUrl("images/photo/" + name.replace(" ", "_") +  ".jpg"));
		}
		
	    mongoClient.close();
		
		return products;
	}
	
	@Override
	public List<SubCategory> getSubCategories(GetSubCategoriesRequest action) {
		final List<SubCategory> result = new ArrayList<SubCategory>();
		
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		DB db = mongoClient.getDB( "comparephone" );
		DBCollection collection = db.getCollection("product");
		
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$os.type").append("docs", new BasicDBObject("$push", "$$ROOT")).append("count", new BasicDBObject("$sum", 1)));
		BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("count", -1));
		BasicDBObject limit = new BasicDBObject("$limit", 5);
				
	    Iterable<DBObject> output = collection.aggregate(Arrays.asList(group, sort, limit)).results();
	    for (DBObject dbObject : output) {
	        SubCategory subCategory = new SubCategory().setName("" + dbObject.get("_id"));
	        for (int i = 0; i < 4; i++) {
	        	BasicDBObject item = (BasicDBObject) ((BasicDBList)dbObject.get("docs")).get(i);
	        	BasicDBObject name = (BasicDBObject) item.get("name");
	        	subCategory.addProduct(new Product().setName(name.get("id") + ""));
	        }
			result.add(subCategory);
	    }

	    mongoClient.close();

		return result;
	}
}
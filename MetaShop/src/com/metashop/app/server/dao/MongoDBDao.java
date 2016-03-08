package com.metashop.app.server.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

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
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBDao extends AServicesFacade {
	
	@Override
	public List<Brand> getBrandsImpl(GetBrandsRequest action) {
		// run query
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$maker.name").append("count", new BasicDBObject("$sum", 1)));
		BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("count", -1));
		BasicDBObject limit = new BasicDBObject("$limit", 7);
		AggregateIterable<Document> output = getCollection("product").aggregate(Arrays.asList(group, sort, limit));
	    
	    // create output
	    final List<Brand> result = new ArrayList<Brand>();
	    for (Document document : output)
			result.add(new Brand().setName("" + document.get("_id")).setCount(Integer.parseInt(document.get("count") + "")));

		return result;
	}
	
	@Override
	public List<Category> getCategoriesImpl(GetCategoriesRequest action) {
		// run query
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$os.ver").append("count", new BasicDBObject("$sum", 1)));
		BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("count", -1));
		BasicDBObject limit = new BasicDBObject("$limit", 10);		
		AggregateIterable<Document> output = getCollection("product").aggregate(Arrays.asList(group, sort, limit));
	    
	    // create output
		final List<Category> result = new ArrayList<Category>();
	    for (Document document : output)
			result.add(new Category().setName("Android " + document.get("_id") + " (" + document.get("count") + ")"));

		return result;
	}
	
	@Override
	public List<Product> getFeaturedImpl(GetFeaturedRequest action) {
		// run query
		FindIterable<Document> iterable = getCollection("product").find().limit(action.getCount());
		
		// create output
		final List<Product> products = new ArrayList<Product>();
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
				String name = (String) ((Document) document.get("name")).get("id");			
				products.add(new Product().setName(name).setPrice(56).setCurrency("$").setUrl("images/photo/F-12C.jpg"));
		    }
		});

		return products;
	}
	
	@Override
	public List<Product> getRecommendedImpl(GetRecommendedRequest action) {
		// run query
		FindIterable<Document> iterable = getCollection("product").find().limit(3);

		// create output
		final List<Product> products = new ArrayList<Product>();
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
				String name = (String) ((Document) document.get("name")).get("id");			
				products.add(new Product().setName(name).setPrice(56).setCurrency("$").setUrl("images/photo/F-12C.jpg"));
		    }
		});
		
		return products;
	}
	
	@Override
	public List<SubCategory> getSubCategoriesImpl(GetSubCategoriesRequest action) {
		// run query
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$os.type").append("docs", new BasicDBObject("$push", "$$ROOT")).append("count", new BasicDBObject("$sum", 1)));
		BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("count", -1));
		BasicDBObject limit = new BasicDBObject("$limit", 5);		
		AggregateIterable<Document> output = getCollection("product").aggregate(Arrays.asList(group, sort, limit));

	    // create output
	    final List<SubCategory> result = new ArrayList<SubCategory>();
	    for (Document document : output) {
	        SubCategory subCategory = new SubCategory().setName("" + document.get("_id"));
	        for (int i = 0; i < 4; i++) {
	        	Document item = (Document) ((List<Document>) document.get("docs")).get(i);
	        	Document name = (Document) item.get("name");
	        	subCategory.addProduct(new Product().setName(name.get("id") + "").setPrice(56).setCurrency("$").setUrl("images/photo/F-12C.jpg"));
	        }
			result.add(subCategory);
	    }

		return result;
	}
	
	// *************************************************************************************************
	// Manage Connection
	// *************************************************************************************************
	private MongoClient mongoClient = null;
	private MongoDatabase db = null;
	
	@Override
	protected void pre() {
	}
	
	@Override
	protected void post() {
		System.out.println("closing");
		mongoClient.close();
		db = null;
	}
	
	private MongoCollection<Document> getCollection(String name) {
		if (db == null || mongoClient == null) {
			System.out.println("opening");
			mongoClient = new MongoClient( "localhost" , 27017 );
			db = mongoClient.getDatabase( "comparephone" );
		}
				
		return db.getCollection(name);
	}
}
package com.metashop.app.server.dao;

import java.util.ArrayList;
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

public class HardcodedDao extends AServicesFacade {

	public List<Brand> getBrandsImpl(GetBrandsRequest action) {
        List<Brand> brands = new ArrayList<Brand>();
        brands.add(new Brand().setName("Acne").setCount(50));
        brands.add(new Brand().setName("Grune Erde").setCount(56));   
        brands.add(new Brand().setName("Albiro").setCount(27));
        brands.add(new Brand().setName("Ronhill").setCount(32));
        brands.add(new Brand().setName("Oddmolly").setCount(5));
        brands.add(new Brand().setName("Boudestijn").setCount(9));
        brands.add(new Brand().setName("Rosch Creative Culture").setCount(4));
        return brands;
	}
	
	@Override
	public List<Category> getCategoriesImpl(GetCategoriesRequest action) {
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
		return categories;
	}
	
	@Override
	public List<Product> getFeaturedImpl(GetFeaturedRequest action) {
        List<Product> featured = new ArrayList<Product>();
    	if (action.getTextToServer().equals("6")) {
	        // add categories
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product1.jpg"));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product2.jpg"));   
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product3.jpg"));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product4.jpg").setNew(true));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product5.jpg").setSale(true));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product6.jpg"));
    	} else {
	        // add categories
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/shop/product12.jpg"));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/shop/product11.jpg"));   
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/shop/product10.jpg"));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/shop/product9.jpg").setNew(true));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/shop/product8.jpg").setSale(true));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/shop/product7.jpg"));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product6.jpg"));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product5.jpg"));   
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product4.jpg"));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product3.jpg"));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product2.jpg"));
	        featured.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/product1.jpg"));
    	}
		return featured;
	}
	
	@Override
	public List<Product> getRecommendedImpl(GetRecommendedRequest action) {
        List<Product> recommended = new ArrayList<Product>();
 		recommended.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/recommend1.jpg"));
 		recommended.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/recommend2.jpg"));   
    	recommended.add(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/recommend3.jpg"));
		return recommended;
	}
	
	@Override
	public List<SubCategory> getSubCategoriesImpl(GetSubCategoriesRequest action) {
        List<SubCategory> subCategories = new ArrayList<SubCategory>();
        subCategories.add(new SubCategory().setName("T-shirt")
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery1.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery2.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery3.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery4.jpg"))
        );
        subCategories.add(new SubCategory().setName("Blazers")
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery4.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery3.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery2.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery1.jpg"))
        );
        subCategories.add(new SubCategory().setName("Sunglass")
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery3.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery4.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery1.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery2.jpg"))
        );
        subCategories.add(new SubCategory().setName("Kids")
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery1.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery2.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery3.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery4.jpg"))
        );
        subCategories.add(new SubCategory().setName("Polo Shirt")
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery2.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery4.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery3.jpg"))
        		.addProduct(new Product().setName("Easy Polo Black Edition").setPrice(56).setCurrency("$").setUrl("images/home/gallery1.jpg"))
        );
		return subCategories;
	}
	
	// *************************************************************************************************
	// Manage Connection
	// *************************************************************************************************
	@Override
	protected void pre() {
	}
	@Override
	protected void post() {
	}
}
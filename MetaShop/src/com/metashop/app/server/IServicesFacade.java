package com.metashop.app.server;

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

public interface IServicesFacade {
	public List<Brand> getBrands(GetBrandsRequest action);
	public List<Category> getCategories(GetCategoriesRequest action);
	public List<Product> getFeatured(GetFeaturedRequest action);
	public List<Product> getRecommended(GetRecommendedRequest action);
	public List<SubCategory> getSubCategories(GetSubCategoriesRequest action);
}
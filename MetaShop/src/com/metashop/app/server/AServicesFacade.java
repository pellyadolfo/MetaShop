package com.metashop.app.server;

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

public abstract class AServicesFacade {
	
	public List<Brand> getBrands(GetBrandsRequest action) {
    	try { pre(); return getBrandsImpl(action); } catch (Throwable e) { handleException(e, action); } finally {post();} return new ArrayList<Brand>();
	}
	public List<Category> getCategories(GetCategoriesRequest action) {
    	try { pre(); return getCategoriesImpl(action); } catch (Throwable e) { handleException(e, action); } finally {post();} return new ArrayList<Category>();
	}
	public List<Product> getFeatured(GetFeaturedRequest action) {
    	try { pre(); return getFeaturedImpl(action); } catch (Throwable e) { handleException(e, action); } finally {post();} return new ArrayList<Product>();
	}
	public List<Product> getRecommended(GetRecommendedRequest action) {
    	try { pre(); return getRecommendedImpl(action); } catch (Throwable e) { handleException(e, action); } finally {post();} return new ArrayList<Product>();
	}
	public List<SubCategory> getSubCategories(GetSubCategoriesRequest action) {
    	try { pre(); return getSubCategoriesImpl(action); } catch (Throwable e) { handleException(e, action); } finally {post();} return new ArrayList<SubCategory>();
	}
    protected abstract List<Brand> getBrandsImpl(GetBrandsRequest action) throws Throwable;
    protected abstract List<Category> getCategoriesImpl(GetCategoriesRequest action) throws Throwable;
    protected abstract List<Product> getFeaturedImpl(GetFeaturedRequest action) throws Throwable;
    protected abstract List<Product> getRecommendedImpl(GetRecommendedRequest action) throws Throwable;
    protected abstract List<SubCategory> getSubCategoriesImpl(GetSubCategoriesRequest action) throws Throwable;
	
    // connection management
    protected abstract void pre();
    protected abstract void post();
	protected void handleException (Throwable e, Object config) {
		e.printStackTrace();
	}
}
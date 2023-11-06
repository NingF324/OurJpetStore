package org.ningf.ourpetstore.service;

import org.ningf.ourpetstore.domain.Category;
import org.ningf.ourpetstore.domain.Item;
import org.ningf.ourpetstore.domain.Product;
import org.ningf.ourpetstore.persistence.CategoryDao;
import org.ningf.ourpetstore.persistence.ItemDao;
import org.ningf.ourpetstore.persistence.ProductDao;
import org.ningf.ourpetstore.persistence.impl.CategoryDapImpl;
import org.ningf.ourpetstore.persistence.impl.ItemDaoImpl;
import org.ningf.ourpetstore.persistence.impl.ProductDaoImpl;

import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/5 20:25
 */
public class CatalogService {
    private CategoryDao categoryDao;
    private ProductDao productDao;
    private ItemDao itemDao;

    public CatalogService(){
        this.categoryDao=new CategoryDapImpl();
        this.productDao=new ProductDaoImpl();
        this.itemDao=new ItemDaoImpl();
    }

    public List<Category> getCategoryList() {
        return categoryDao.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDao.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDao.getProductListByCategory(categoryId);
    }

    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        return productDao.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemDao.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDao.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDao.getInventoryQuantity(itemId) > 0;
    }
}

package org.ningf.ourpetstore.persistence;

import org.ningf.ourpetstore.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getCategoryList();

    Category getCategory(String categoryId);
}

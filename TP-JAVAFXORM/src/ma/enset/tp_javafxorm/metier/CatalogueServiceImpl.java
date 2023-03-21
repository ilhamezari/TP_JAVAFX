package ma.enset.tp_javafxorm.metier;

import ma.enset.tp_javafxorm.dao.CategoryDAO;
import ma.enset.tp_javafxorm.dao.productDAO;
import ma.enset.tp_javafxorm.dao.entities.Category;
import ma.enset.tp_javafxorm.dao.entities.Product;

import java.util.List;

public class CatalogueServiceImpl implements CatalogueService {
    private productDAO productDAO;
     public CategoryDAO categoryDAO;

    public CatalogueServiceImpl(ma.enset.tp_javafxorm.dao.productDAO productDAO, CategoryDAO categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Product> getAllProducts() {

        return productDAO.findAll();
    }

    @Override
    public void addProduct(Product p) {
      productDAO.save(p);

    }

    @Override
    public void UpdateProduct(Product p) {
        productDAO.Update(p);
    }



    @Override
    public void deleteProduct(Long id) {
    productDAO.delete(id);
    }

    @Override
    public Long findP(Long id) {
        productDAO.findById(id);
        return id;
    }

    @Override
    public List<Product> findByQuery(String query) {
        return productDAO.finbyquery(query);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    @Override
    public void addcategory(Category c) {
      categoryDAO.save(c);
    }

    @Override
    public void deleteCategory(Category c) {
        categoryDAO.delete(c.getId());

    }
}

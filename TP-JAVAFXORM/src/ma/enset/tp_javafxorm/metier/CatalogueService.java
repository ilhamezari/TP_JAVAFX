package ma.enset.tp_javafxorm.metier;

import ma.enset.tp_javafxorm.dao.entities.Category;
import ma.enset.tp_javafxorm.dao.entities.Product;

import java.util.List;

public interface CatalogueService {
  List<Product> getAllProducts();
  void addProduct(Product p);
  void UpdateProduct(Product p);
  void deleteProduct(Long id);
  Long findP(Long id);
  List<Product> findByQuery(String query);
  List<Category> getAllCategories();
  void addcategory(Category c);
  void deleteCategory (Category c);
}

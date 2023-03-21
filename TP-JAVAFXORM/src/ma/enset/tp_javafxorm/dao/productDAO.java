package ma.enset.tp_javafxorm.dao;

import ma.enset.tp_javafxorm.dao.entities.Product;

import java.util.List;

public interface productDAO extends Dao<Product, Long>{
  // Product find(Long id);

   List<Product> finbyquery(String query);
}

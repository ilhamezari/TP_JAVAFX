package ma.enset.tp_javafxorm.dao;

import ma.enset.tp_javafxorm.dao.entities.Category;

import java.util.List;

public interface CategoryDAO extends Dao<Category,Long> {

    List<Category> findAll();
}

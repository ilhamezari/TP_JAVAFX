package ma.enset.tp_javafxorm.dao;

import ma.enset.tp_javafxorm.dao.entities.Product;

import java.util.List;

public interface Dao <T,U>{

     T findById(U id);
     List<T> findAll();
     Product save(T o);
     void delete(U id);
     void Update(T o);


}

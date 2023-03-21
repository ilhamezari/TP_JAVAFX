package ma.enset.tp_javafxorm.metier;

import ma.enset.tp_javafxorm.dao.ProductDaoImpl;
import ma.enset.tp_javafxorm.dao.entities.Category;
import ma.enset.tp_javafxorm.dao.entities.CategoryDAOImpl;
import ma.enset.tp_javafxorm.dao.entities.Product;
import ma.enset.tp_javafxorm.presentation.controllers.Productcontroller;

public class Application {
    public static void main(String[] args){
        CatalogueService catalogueService = new CatalogueServiceImpl( new ProductDaoImpl(),new CategoryDAOImpl());
        Category c1 = new Category();
        c1.setName("Gaming");
        c1.setId(1);

        Product p1 = new Product();
        p1.setName("N2");
        p1.setReference("ref2");
        p1.setPrix(2000);
        p1.setCategory(c1);

        Long Products = catalogueService.findP(2L);

            System.out.println(Products.toString());
        System.out.println(catalogueService.getAllProducts());


    }
}

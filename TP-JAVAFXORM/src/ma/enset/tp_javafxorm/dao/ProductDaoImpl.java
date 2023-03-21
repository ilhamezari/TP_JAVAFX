package ma.enset.tp_javafxorm.dao;

import ma.enset.tp_javafxorm.dao.entities.Category;
import ma.enset.tp_javafxorm.dao.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements productDAO {

    @Override
    public Product findById(Long id) {
        Connection connection = connexion_DBSingleton.getConnection();
       List<Product> products = new ArrayList<>();

        Product product = null;
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM product WHERE ID = ?");

            pstm.setLong(1,id);
            ResultSet rs =pstm.executeQuery();
            while (rs.next()){


               product  = new Product(
                rs.getLong("ID"),
                rs.getString("NAME"),
                rs.getString("REFERENCE"),
                rs.getDouble("PRIX"));

                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    @Override
    public List<Product> findAll() {
        Connection connection = connexion_DBSingleton.getConnection();
        //Product product = null;

        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT P.*, C.NAME as categoryName FROM product P INNER JOIN category C on P.ID_CAT=C.ID");
            //pstm.setLong(1,id);
            ResultSet rs =pstm.executeQuery();
             while (rs.next()){
                 products.add(new Product(
                         rs.getLong("ID"),
                         rs.getString("NAME"),
                         rs.getString("REFERENCE"),
                         rs.getDouble("PRIX"),
                         new Category(
                                 rs.getLong("ID"),
                                 rs.getString("categoryName")
                         )
                 ));
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product save(Product o) {
        Connection connection = connexion_DBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into product(NAME,REFERENCE,PRIX,ID_CAT)values(?,?,?,?)");
            pstm.setString(1,o.getName());
            pstm.setString(2,o.getReference());
            pstm.setDouble(3,o.getPrix());
            pstm.setLong(4,o.getCategory().getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return o;
    }

    @Override
    public void delete(Long id) {
        Connection connection = connexion_DBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("delete from product where ID = ?");
            pstm.setLong(1,id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public void Update(Product o) {
        Connection connection = connexion_DBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("UPDATE product SET NAME=?,REFERENCE=?,PRIX=?,ID_CAT=? where ID=?");
            pstm.setString(1,o.getName());
            pstm.setString(2,o.getReference());
            pstm.setDouble(3,o.getPrix());
            pstm.setLong(4,o.getCategory().getId());
            pstm.setLong(5,o.getId());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> finbyquery(String query) {
        Connection connection = connexion_DBSingleton.getConnection();
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT P.*, C.NAME as categoryname FROM product P INNER JOIN category C on P.ID_CAT = C.ID where P.NAME like ? or REFERENCE like ? or C.NAME like ?");
            pstm.setString(1,"%"+query+"%");
            pstm.setString(2,"%"+query+"%");
            pstm.setString(3,"%"+query+"%");
            ResultSet rs =pstm.executeQuery();
            while (rs.next()){
                products.add(new Product(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("REFERENCE"),
                        rs.getDouble("PRIX"),
                        new Category(
                                rs.getLong("ID"),
                                rs.getString("categoryname")
                        )
                      ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

}


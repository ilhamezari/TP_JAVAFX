package ma.enset.tp_javafxorm.dao.entities;

import ma.enset.tp_javafxorm.dao.CategoryDAO;
import ma.enset.tp_javafxorm.dao.connexion_DBSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public Category findById(Long id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        Connection connection = connexion_DBSingleton.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sqlQuery = "SELECT * FROM category";
        List<Category> categories = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                categories.add(new Category(
                        resultSet.getLong("ID"),
                        resultSet.getString("NAME")
                ));
            }

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Product save(Category o) {

        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void Update(Category o) {

    }
}

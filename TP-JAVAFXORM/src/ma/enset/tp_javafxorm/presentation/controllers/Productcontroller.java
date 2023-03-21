package ma.enset.tp_javafxorm.presentation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.tp_javafxorm.dao.ProductDaoImpl;
import ma.enset.tp_javafxorm.dao.entities.Category;
import ma.enset.tp_javafxorm.dao.entities.CategoryDAOImpl;
import ma.enset.tp_javafxorm.dao.entities.Product;
import ma.enset.tp_javafxorm.metier.CatalogueService;
import ma.enset.tp_javafxorm.metier.CatalogueServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Productcontroller implements Initializable {
    @FXML private ListView<Product> productList;
    @FXML private TextField textrech;

    @FXML
    private TextField textNom;
    @FXML
    private TextField textref;
    @FXML
    private TextField textprix;
    @FXML
    private ComboBox<Category> comboCateg;

    @FXML private TableView<Product> tableV;
    @FXML
    private TableColumn<Long, Product> tableId;
    @FXML
    private TableColumn<String, Product> tableName;
    @FXML
    private TableColumn<String, Product> tableRef;
    @FXML
    private TableColumn<Double, Product> tablePrix;
    @FXML
    private TableColumn<Category, Product> tableCat;

    ObservableList<Product> Pdata = FXCollections.observableArrayList();
    ObservableList<Category> categoriesData = FXCollections.observableArrayList();

    private CatalogueService catalogService;

    public Productcontroller() {
    }
    private void LoadTable(){

        tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
        tablePrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tableCat.setCellValueFactory(new PropertyValueFactory<>("category"));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadTable();
        catalogService = new CatalogueServiceImpl(new ProductDaoImpl(), new CategoryDAOImpl());

        comboCateg.setItems(categoriesData);
        tableV.setItems(Pdata);

        categoriesData.setAll(catalogService.getAllCategories());
        Pdata.setAll(catalogService.getAllProducts());

        tableV.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, product) -> {
            if (product != null) {
                textNom.setText(product.getName());
                textref.setText(product.getReference());
                textprix.setText(String.valueOf(product.getPrix()));
                comboCateg.setValue(product.getCategory());
            }
        });}
            @FXML
    public void addProduct() {
        validationTest();
     // créer le nouveau produit et l'ajouter à la base de données
        Product newProduct = new Product();//new Product();

        newProduct.setName(textNom.getText());
        newProduct.setReference(textref.getText());
        newProduct.setPrix(Double.parseDouble(textprix.getText()));
        newProduct.setCategory(comboCateg.getSelectionModel().getSelectedItem());
        catalogService.addProduct(newProduct);
        //clear
        textNom.setText("");
        textref.setText("");
        textprix.setText("");
        comboCateg.setValue(null);

        // actualiser la liste des produits affichés

       List<Product> allProducts = catalogService.getAllProducts();
         Pdata.clear();
        Pdata.addAll(allProducts);

    }
    @FXML
   public void updateP()
    {
        validationTest();
        Product product = tableV.getSelectionModel().getSelectedItem();
        product.setName(textNom.getText());
        product.setReference(textref.getText());
        product.setPrix(Double.parseDouble(textprix.getText()));
        product.setCategory(comboCateg.getSelectionModel().getSelectedItem());
        catalogService.UpdateProduct(product);
        //clear
        textNom.setText("");
        textref.setText("");
        textprix.setText("");
        comboCateg.setValue(null);

        // actualiser la liste des produits affichés

        List<Product> allProducts = catalogService.getAllProducts();
        Pdata.clear();
        Pdata.addAll(allProducts);
    }
@FXML
    public void deleteP()
    {
        Product product = tableV.getSelectionModel().getSelectedItem();
        catalogService.deleteProduct(product.getId());
        //clear
        textNom.setText("");
        textref.setText("");
        textprix.setText("");
        comboCateg.setValue(null);

        // actualiser la liste des produits affichés

        List<Product> allProducts = catalogService.getAllProducts();
        Pdata.clear();
        Pdata.addAll(allProducts);
    }
    @FXML
    public void  searchtext()
    {
        String keyword = textrech.getText();
        List<Product> searchByquery = catalogService.findByQuery(keyword);
        Pdata.setAll(searchByquery);
    }

    private void validationTest(){
        String Name = textNom.getText();
        String Reference = textref.getText();
        String Prix = textprix.getText();
        Category cat = comboCateg.getSelectionModel().getSelectedItem();


        if(Name.isEmpty() || Name.isBlank() ||
                Reference.isEmpty() || Reference.isBlank() ||
                Prix.isEmpty() || Prix.isBlank()
                || cat==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("name=["+Name+"] ref=["+Reference+"] prix=["+Prix+"] category=["+cat+"]");
            alert.show();
            throw new IllegalArgumentException();
        }

        try{
            Double.parseDouble(Prix);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
            throw new IllegalArgumentException();
        }


    }}



package ma.enset.tp_javafxorm.dao.entities;

import java.io.Serializable;
import java.sql.Date;

//une classe persistante
public class Product implements Serializable {
    private long id;
    private String name;
    //private List<Product>;
    private String reference;
    private Double prix;
    private Category category;

    public Product(long id, String name, String reference, Double prix, Category category) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.prix=prix;
        this.category = category;
    }

    public Product(long id, String name, String reference, Double prix) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.prix = prix;
    }

    public Product(String name, String reference, Double prix,Category category) {
        this.name = name;
        this.reference = reference;
        this.prix = prix;
        this.category = category;
    }

    public Product() {

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", prix=" + prix +
                ", category=" + category +
                '}';
    }
}

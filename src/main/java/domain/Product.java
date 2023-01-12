package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="products")
@NamedQueries({
        @NamedQuery(name="Product.retrieveAll", query = "select e from Product e"),
        @NamedQuery(name="Product.retrieveById", query = "select e from Product e where e.id = :id"),
        @NamedQuery(name="Product.deleteById", query = "delete from Product e where e.id = :id")
})
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="orders")
@NamedQueries({
        @NamedQuery(name = "Order.retrieveAll", query = "select e from Order e"),
        @NamedQuery(name = "Order.retrieveById", query = "select e from Order e where e.id = :id"),
        @NamedQuery(name = "Order.deleteById", query = "delete from Order e where e.id = :id")
})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinColumn(name="product_id")
    private List<Product> products;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

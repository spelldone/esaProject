package dao.impl;

import dao.ProductRepository;
import domain.Product;


import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
@LocalBean
@Remote(ProductRepository.class)
public class ProductRepositoryBean extends TransactionBean implements ProductRepository {


    public ProductRepositoryBean() {
    }

    @Override
    public Optional<Product> retrieveById(UUID id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    @Override
    public List<Product> retrieveAll() {
        TypedQuery<Product> query = entityManager.createNamedQuery(
                "Product.retrieveAll", Product.class
        );

        return query.getResultList();
    }

    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Override
    public void delete(Product product) {
        entityManager.remove(
                entityManager.contains(product) ? product : entityManager.merge(product)
        );
    }
}

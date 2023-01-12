package dao.impl;

import dao.CustomerRepository;
import domain.Customer;


import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
@LocalBean
@Remote(CustomerRepository.class)
public class CustomerRepositoryBean extends TransactionBean implements CustomerRepository {


    public CustomerRepositoryBean() {
    }

    @Override
    public Optional<Customer> retrieveById(UUID id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

    @Override
    public List<Customer> retrieveAll() {
        TypedQuery<Customer> query = entityManager.createNamedQuery(
                "Customer.retrieveAll", Customer.class
        );

        return query.getResultList();
    }

    @Override
    public void save(Customer customer) {
       entityManager.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    public void delete(Customer customer) {
        entityManager.remove(
              entityManager.contains(customer) ? customer : entityManager.merge(customer)
        );
    }
}

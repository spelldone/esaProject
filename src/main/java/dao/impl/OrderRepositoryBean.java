package dao.impl;

import dao.OrderRepository;
import domain.Order;


import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
@LocalBean
@Remote(OrderRepository.class)
public class OrderRepositoryBean extends TransactionBean implements OrderRepository {


    public OrderRepositoryBean() {
    }

    @Override
    public Optional<Order> retrieveById(UUID id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public List<Order> retrieveAll() {
        TypedQuery<Order> query = entityManager.createNamedQuery(
                "Order.retrieveAll", Order.class
        );

        return query.getResultList();
    }

    @Override
    public void save(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }

    @Override
    public void delete(Order order) {
        entityManager.remove(
                entityManager.contains(order) ? order : entityManager.merge(order)
        );
    }
}

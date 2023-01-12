package rest;

import dao.impl.OrderRepositoryBean;
import domain.Order;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet
@Path("/orders")
public class OrderRestService {

    @EJB
    private OrderRepositoryBean repos;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersInfo() {
        List<Order> orders = repos.retrieveAll();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(orders)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order) {
        repos.save(order);

        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PUT
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("orderId") String orderId, Order newOrder) {
        Optional<Order> existingOrder = repos.retrieveById(UUID.fromString(orderId));

        if (!existingOrder.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity("Order with such id is not found")
                    .build();
        }

        Order order = existingOrder.get();
        if (!newOrder.getProducts().isEmpty()) {
            order.setProducts(newOrder.getProducts());
        }

        if (newOrder.getCustomer() != null) {
            order.setCustomer(newOrder.getCustomer());
        }

        repos.update(order);

        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("orderId") String orderId) {
        Optional<Order> existingOrder = repos.retrieveById(UUID.fromString(orderId));

        if (!existingOrder.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity("Order with such id is not found")
                    .build();
        }

        repos.delete(existingOrder.get());

        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}

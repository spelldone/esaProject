package rest;

import dao.impl.CustomerRepositoryBean;
import domain.Customer;

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
@Path("/customers")
public class CustomerRestService {

    @EJB
    private CustomerRepositoryBean repos;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        List<Customer> customers = repos.retrieveAll();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(customers)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        repos.save(customer);

        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PUT
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("customerId") String customerId, Customer newCustomer) {
        Optional<Customer> existingCustomer = repos.retrieveById(UUID.fromString(customerId));

        if (!existingCustomer.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity("Customer with such id is not found")
                    .build();
        }

        Customer customer = existingCustomer.get();
        if (!newCustomer.getName().isEmpty()) {
            customer.setName(newCustomer.getName());
        }

        if (!newCustomer.getMiddleName().isEmpty()) {
            customer.setMiddleName(newCustomer.getMiddleName());
        }

        if (!newCustomer.getSurname().isEmpty()) {
            customer.setSurname(newCustomer.getSurname());
        }

        repos.update(customer);

        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("customerId") String customerId) {
        Optional<Customer> existingCustomer = repos.retrieveById(UUID.fromString(customerId));

        if (!existingCustomer.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity("Customer with such id is not found")
                    .build();
        }

        repos.delete(existingCustomer.get());

        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

}

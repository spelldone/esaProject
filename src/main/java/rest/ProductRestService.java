package rest;

import dao.impl.ProductRepositoryBean;
import domain.Product;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet
@Path("/products")
public class ProductRestService {

    @EJB
    private ProductRepositoryBean repos;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsInfo() {
        List<Product> products = repos.retrieveAll();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(products)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        repos.save(product);

        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PUT
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("productId") String productId, Product newProduct) {
        Optional<Product> existingProduct = repos.retrieveById(UUID.fromString(productId));

        if (!existingProduct.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity("Product with such id is not found")
                    .build();
        }

        Product product = existingProduct.get();
        if (!newProduct.getName().isEmpty()) {
            product.setName(newProduct.getName());
        }

        repos.update(product);

        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("productId") String productId) {
        Optional<Product> existingProduct = repos.retrieveById(UUID.fromString(productId));

        if (!existingProduct.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity("Product with such id is not found")
                    .build();
        }

        repos.delete(existingProduct.get());

        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}

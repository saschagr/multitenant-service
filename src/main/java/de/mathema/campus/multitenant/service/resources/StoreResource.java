package de.mathema.campus.multitenant.service.resources;

import de.mathema.campus.multitenant.service.persitence.Store;
import de.mathema.campus.multitenant.service.tenant.TenantService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("store")
@Transactional
public class StoreResource {
    @Inject
    private TenantService tenantService;

    @Inject
    private StoreService storeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Store> get() {
        return storeService.get();
    }

    @PUT
    @Path("{value}")
    public void put(@PathParam("value") String value) {
        storeService.setValue(value);
    }

}

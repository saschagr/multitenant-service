package de.mathema.campus.multitenant.service.resources;

import de.mathema.campus.multitenant.service.persitence.Store;
import de.mathema.campus.multitenant.service.persitence.TenantInterceptor;
import de.mathema.campus.multitenant.service.tenant.TenantService;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Dependent
@Interceptors(TenantInterceptor.class)
public class StoreService {
    @Inject
    private TenantService tenantService;

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Store> get() {
        TypedQuery<Store> query = entityManager.createNamedQuery(Store.FIND_ALL, Store.class);
        return query.getResultList();
    }

    public void setValue(@PathParam("value") String value) {
        entityManager.persist(Store.builder().value(value).build());
    }
}

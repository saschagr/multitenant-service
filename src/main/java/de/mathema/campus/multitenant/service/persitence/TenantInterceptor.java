package de.mathema.campus.multitenant.service.persitence;

import de.mathema.campus.multitenant.service.tenant.TenantService;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Interceptor
public class TenantInterceptor {

    static final String QUERY_REMOVE_CURRENT_TENANT = "removeCurrentTenant";

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private TenantService tenantService;

    @AroundInvoke
    Object surroundWithTenant(InvocationContext invocationContext) throws Exception {
        try {
            entityManager
                    .createNativeQuery("SET app.current_tenant = " + tenantService.getCurrentTenant())
                    .executeUpdate();

            return invocationContext.proceed();
        } catch (Exception e ) {
            throw new RuntimeException(e);
        } finally {
            entityManager.createNamedQuery(QUERY_REMOVE_CURRENT_TENANT).executeUpdate();
        }
    }
}

package de.mathema.campus.multitenant.service.tenant;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;

@Provider
public class TenantFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantFilter.class);
    @Inject
    private TenantService tenantService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        try {
            var headers = requestContext.getHeaders();
            var tenantList = headers.get("tenant");
            if (tenantList == null) {
                tenantList = Collections.emptyList();
            }

            var tenant = tenantList.stream().findFirst();

            if (tenant.isPresent()) {
                tenantService.setCurrentTenant(tenant.get());
                LOGGER.info("TenantFilter called for " + tenant);
            } else {
                //tenantService.remove();
                tenantService.setCurrentTenant("default");
                LOGGER.warn("TenantFilter - There is no tenant set in the header,  using 'default' tenant");
            }
        } catch (Exception e) {
            LOGGER.error("TenantFilter - Failed to add filter for tenant", e);
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        tenantService.remove();
    }
}

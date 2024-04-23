package de.mathema.campus.multitenant.service.tenant;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TenantService implements AutoCloseable {
    ThreadLocal<String> TLS_TENANT = new InheritableThreadLocal<>();

    public AutoCloseable setCurrentTenant(String tenant) {
        TLS_TENANT.set(tenant);
        return this;
    }
    public void remove() {
        TLS_TENANT.remove();
    }

    public String getCurrentTenant() {
        return TLS_TENANT.get();
    }

    @Override
    public void close() {
        remove();
    }
}

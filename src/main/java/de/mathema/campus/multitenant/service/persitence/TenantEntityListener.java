package de.mathema.campus.multitenant.service.persitence;

import de.mathema.campus.multitenant.service.tenant.TenantService;
import jakarta.inject.Inject;
import jakarta.persistence.PrePersist;

public class TenantEntityListener {

    @Inject
    private TenantService tenantService;

    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof Tenantable tenantable) {
            tenantable.setTenant(tenantService.getCurrentTenant());
        }
    }
}

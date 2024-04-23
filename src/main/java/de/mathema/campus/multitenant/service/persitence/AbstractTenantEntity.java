package de.mathema.campus.multitenant.service.persitence;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Data
public abstract class AbstractTenantEntity implements Tenantable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Embedded
    private TenantEmbeddable tenant = new TenantEmbeddable();

    public String getTenant() {
        return this.tenant.getTenant();
    }

    public void setTenant(String tenant) {
        this.tenant.setTenant(tenant);
    }
}

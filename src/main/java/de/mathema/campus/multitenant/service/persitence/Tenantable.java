package de.mathema.campus.multitenant.service.persitence;

public interface Tenantable {

    public String getTenant();
    public void setTenant(String tenant);
}

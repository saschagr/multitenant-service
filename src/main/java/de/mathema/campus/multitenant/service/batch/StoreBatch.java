package de.mathema.campus.multitenant.service.batch;

import de.mathema.campus.multitenant.service.resources.StoreService;
import de.mathema.campus.multitenant.service.tenant.TenantService;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@Dependent
@Transactional
public class StoreBatch {

    private static final List<String> TENANTS = List.of("mathema", "campus", "jax", "sascha");

    @Inject
    private StoreService storeService;

    @Inject
    private TenantService tenantService;


    public void start() {
        TENANTS.forEach(this::doIt);
    }

    private void doIt(String tenant) {
        try (var autoCloseable = tenantService.setCurrentTenant(tenant)) {
            doIt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void doIt() {
        System.out.println("-----------------------------------------");
        System.out.println("Batch working for tenant " + tenantService.getCurrentTenant());
        System.out.println(storeService.get());
        System.out.println("-----------------------------------------");
    }
}

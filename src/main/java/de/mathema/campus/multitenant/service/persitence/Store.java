package de.mathema.campus.multitenant.service.persitence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NamedNativeQuery(name = TenantInterceptor.QUERY_REMOVE_CURRENT_TENANT, query = "SET app.current_tenant = ''")
@NamedQuery(name = Store.FIND_ALL, query = "select s from Store s")

@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Store extends AbstractTenantEntity {

    public static final String FIND_ALL = "storeFindAll";
    @Id
    @GeneratedValue
    private Long id;

    private String value;
}

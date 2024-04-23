package de.mathema.campus.multitenant.service.persitence;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class TenantEmbeddable {

    public String tenant;
}

package de.stuckenbrockhm.demo.monitoring.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
public class BillingFullApi extends BillingBaseApi {

    private static final long serialVersionUID = 8362822088781916124L;

    public BillingFullApi(SubscriptionLevel subscription) {
        super(subscription);
    }
}

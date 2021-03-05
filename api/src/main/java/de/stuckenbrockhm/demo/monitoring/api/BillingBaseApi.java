package de.stuckenbrockhm.demo.monitoring.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillingBaseApi  implements java.io.Serializable {

    private static final long serialVersionUID = 8242822368781916108L;

	private SubscriptionLevel subscription;

}

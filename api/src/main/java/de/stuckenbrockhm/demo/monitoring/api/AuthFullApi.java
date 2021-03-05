package de.stuckenbrockhm.demo.monitoring.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
public class AuthFullApi extends AuthBaseApi {

    private static final long serialVersionUID = 8161236878024282198L;

    public AuthFullApi(Boolean authenticated, String message) {
        super(authenticated, message);
    }
}

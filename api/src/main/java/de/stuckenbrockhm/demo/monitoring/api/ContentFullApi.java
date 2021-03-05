package de.stuckenbrockhm.demo.monitoring.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
public class ContentFullApi extends ContentBaseApi {

    private static final long serialVersionUID = 8819161243628220887L;

    public ContentFullApi(Drink[] drinks) {
        super(drinks);
    }
}

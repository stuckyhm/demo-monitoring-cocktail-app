package de.stuckenbrockhm.demo.monitoring.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentBaseApi  implements java.io.Serializable {

    private static final long serialVersionUID = 8368617824282219108L;

	private Drink[] drinks;

}

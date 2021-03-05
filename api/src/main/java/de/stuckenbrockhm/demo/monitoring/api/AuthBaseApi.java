package de.stuckenbrockhm.demo.monitoring.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthBaseApi implements java.io.Serializable {

    private static final long serialVersionUID = -3944660477279242968L;

    private Boolean authenticated;

    private String message;

}
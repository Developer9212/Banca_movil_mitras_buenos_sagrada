package com.fenoreste.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BackendOperationResultDTO {

	 private String integrationProperties;
	    private String backendCode;
	    private String backendMessage;
	    private String backendReference;
	    private boolean isError;
	    private String transactionIdenty;

	    
}

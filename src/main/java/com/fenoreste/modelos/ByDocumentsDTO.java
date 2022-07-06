package com.fenoreste.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ByDocumentsDTO {

    private String ClientBankIdentifier;
    private String ClientName;
    private String ClientType;
    private String DocumentId;
}

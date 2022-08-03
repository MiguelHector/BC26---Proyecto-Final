package com.banco.vendor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document(collection = "exchangeRate")
public class Vendor implements Serializable {
    @Transient
    public static final String SEQUENCE_NAME = "vendor_sequence";
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String documentType;
    private String documentNumber;
    private String mobileWallet;
    private String currentAccount;
    private String saveAccount;
    private Float amount;
    private static final long serialVersionUID=1L;
}

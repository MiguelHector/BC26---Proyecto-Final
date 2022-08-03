package com.banco.productConfiguration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "productConfiguration")
public class ProductConfiguration {
    @Transient
    public static final String SEQUENCE_NAME = "productConfiguration_sequence";
    @Id
    private Long id;
    private String typeClient;
    private Long idProduct;
    private Long maintenanceCommission;
    private Long limitMovement;
    private Long productQuantity;
    private Long amountMinimum ;
}

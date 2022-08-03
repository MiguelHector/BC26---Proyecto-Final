package com.banco.productClientFixed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "productClientFixed")
public class ProductClientFixed {

    @Transient
    public static final String SEQUENCE_NAME = "productClientFixed_sequence";
    @Id
    private Long id;
    private Long idClientProduct;
    private Float amountMinimum;
    private Integer dayMovement;
    private Integer deadline;
    private Timestamp dateEnd;
    private Float initialCapital;

}

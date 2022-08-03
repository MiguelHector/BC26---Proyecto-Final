package com.banco.productClientCreditCard.model;

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

@Document(collection = "productClientCreditCard")
public class ProductClientCreditCard {
    @Transient
    public static final String SEQUENCE_NAME = "productClientCreditCard_sequence";
    @Id
    private Long id;
    private Long idClientProduct;
    private Float creditLine;
    private Timestamp dateEnd;
    private Timestamp datePayment;

}

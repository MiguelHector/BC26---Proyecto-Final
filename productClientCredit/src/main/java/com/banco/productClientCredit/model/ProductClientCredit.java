package com.banco.productClientCredit.model;

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

@Document(collection = "productClientCredit")
public class ProductClientCredit {

    @Transient
    public static final String SEQUENCE_NAME = "productClientCredit_sequence";
    @Id
    private Long id;
    private Long idClientProduct;
    private Float amountMinimum;
    private Integer quantityShare;
    private Float rate;
    private Float fullInterest;
    private Float arrearsRate;

}

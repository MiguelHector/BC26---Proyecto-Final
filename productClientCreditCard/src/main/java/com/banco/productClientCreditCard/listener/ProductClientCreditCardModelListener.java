package com.banco.productClientCreditCard.listener;

import com.banco.productClientCreditCard.model.ProductClientCreditCard;
import com.banco.productClientCreditCard.service.ISequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class ProductClientCreditCardModelListener extends AbstractMongoEventListener<ProductClientCreditCard> {
    private static final Logger logger = LoggerFactory.getLogger(ProductClientCreditCardModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public ProductClientCreditCardModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ProductClientCreditCard> event) {
        try {
            event.getSource().setId(sequenceGenerator.generateSequence(ProductClientCreditCard.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}
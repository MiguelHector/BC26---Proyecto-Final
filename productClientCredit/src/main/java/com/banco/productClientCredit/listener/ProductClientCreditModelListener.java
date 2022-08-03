package com.banco.productClientCredit.listener;

import com.banco.productClientCredit.model.ProductClientCredit;
import com.banco.productClientCredit.service.ISequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class ProductClientCreditModelListener extends AbstractMongoEventListener<ProductClientCredit> {
    private static final Logger logger = LoggerFactory.getLogger(ProductClientCreditModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public ProductClientCreditModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ProductClientCredit> event) {
        try {
            event.getSource().setId(sequenceGenerator.generateSequence(ProductClientCredit.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}
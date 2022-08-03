package com.banco.productClientFixed.listener;

import com.banco.productClientFixed.model.ProductClientFixed;
import com.banco.productClientFixed.service.ISequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class ProductClientFixedModelListener extends AbstractMongoEventListener<ProductClientFixed> {
    private static final Logger logger = LoggerFactory.getLogger(ProductClientFixedModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public ProductClientFixedModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ProductClientFixed> event) {
        try {
            event.getSource().setId(sequenceGenerator.generateSequence(ProductClientFixed.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}
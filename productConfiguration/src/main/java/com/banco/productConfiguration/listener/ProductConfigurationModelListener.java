package com.banco.productConfiguration.listener;

import com.banco.productConfiguration.model.ProductConfiguration;
import com.banco.productConfiguration.service.ISequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class ProductConfigurationModelListener extends AbstractMongoEventListener<ProductConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(ProductConfigurationModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public ProductConfigurationModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ProductConfiguration> event) {
        try {
            event.getSource().setId(sequenceGenerator.generateSequence(ProductConfiguration.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}
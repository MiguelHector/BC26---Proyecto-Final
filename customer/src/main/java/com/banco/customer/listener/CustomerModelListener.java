package com.banco.customer.listener;

import com.banco.customer.model.Customer;
import com.banco.customer.service.ISequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class CustomerModelListener extends AbstractMongoEventListener<Customer> {
    private static final Logger logger = LoggerFactory.getLogger(CustomerModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public CustomerModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Customer> event) {
        try {
            event.getSource().setId(sequenceGenerator.generateSequence(Customer.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}
package com.banco.client.listener;

import com.banco.client.model.Client;
import com.banco.client.service.ISequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class EmployeeModelListener extends AbstractMongoEventListener<Client> {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public EmployeeModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Client> event) {
        try {
            event.getSource().setId(sequenceGenerator.generateSequence(Client.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}
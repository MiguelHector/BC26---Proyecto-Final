package com.banco.exchangerate.listener;

import com.banco.exchangerate.model.ExchangeRate;
import com.banco.exchangerate.service.ISequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class ExchangeRateModelListener extends AbstractMongoEventListener<ExchangeRate> {
    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public ExchangeRateModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ExchangeRate> event) {
        try {
            event.getSource().setId(sequenceGenerator.generateSequence(ExchangeRate.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}
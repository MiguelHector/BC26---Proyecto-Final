package com.banco.movement.listener;

import com.banco.movement.model.Movement;
import com.banco.movement.service.ISequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class MovementModelListener extends AbstractMongoEventListener<Movement> {
    private static final Logger logger = LoggerFactory.getLogger(MovementModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public MovementModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Movement> event) {
        try {
            event.getSource().setId(sequenceGenerator.generateSequence(Movement.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}
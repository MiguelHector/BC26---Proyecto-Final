package com.banco.vendor.listener;

import com.banco.vendor.model.Vendor;
import com.banco.vendor.service.ISequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class VendorModelListener extends AbstractMongoEventListener<Vendor> {
    private static final Logger logger = LoggerFactory.getLogger(VendorModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public VendorModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Vendor> event) {
        try {
            event.getSource().setId(sequenceGenerator.generateSequence(Vendor.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}
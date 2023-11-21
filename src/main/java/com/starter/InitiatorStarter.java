package com.starter;

import com.fix.Initiator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitiatorStarter {

    @PostConstruct
    public void startInitiator() {
        Initiator initiator = new Initiator();
        initiator.startServer();
    }
}

package com.fix;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import quickfix.*;

@Slf4j
public class Initiator {

    @Getter
    private static SocketInitiator initiator;
    private static SessionSettings settings;

    public Initiator() {
        try {
            settings = new SessionSettings("config.properties");
        } catch (ConfigError configError) {
            log.error("Warning: config error!" + configError);
        }

        InitiatorApplication application = new InitiatorApplication();
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        try {
            initiator = new SocketInitiator(application, storeFactory, settings, logFactory, messageFactory);
        } catch (ConfigError configError) {
            log.error("Warning: config error! " + configError);
        }
    }

    public void startServer() {
        try {
            initiator.start();
            log.info("===Initiator started!===");
        } catch (ConfigError configError) {
            configError.printStackTrace();
        }
    }

    public void stopServer() {
        initiator.stop();
    }
}

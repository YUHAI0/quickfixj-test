package com.fix;

import lombok.extern.slf4j.Slf4j;
import quickfix.*;
import quickfix.field.MsgType;
import quickfix.fix44.ExecutionReport;
import quickfix.fix44.MessageCracker;

import java.util.Objects;

@Slf4j
public class InitiatorApplication extends MessageCracker implements Application {

    @Override
    public void onCreate (SessionID sessionId){
        log.info("onCreate is called");
    }

    @Override
    public void onLogon (SessionID sessionId){
        log.info("onLogon is called");
    }

    @Override
    public void onLogout (SessionID sessionId){
        log.info("onLogout is called");
    }

    @Override
    public void toAdmin (Message message, SessionID sessionId){
        log.info("toAdmin is called");
    }

    @Override
    public void fromAdmin (Message message, SessionID sessionId) throws
    FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        log.info("fromAdmin is called");
    }

    @Override
    public void toApp (Message message, SessionID sessionId) throws DoNotSend {
        log.info("toApp is called: {}", message);
    }

    @Override
    public void fromApp (Message message, SessionID sessionId) throws
    FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        log.info("fromApp is called");
    }

    @Override
    public void onMessage (ExecutionReport message, SessionID sessionID) throws
    FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        String msgType = message.getHeader().getField(new MsgType()).getValue();
        if (Objects.equals(msgType, ExecutionReport.MSGTYPE)) {
            log.info("Received ExecutionReport: " + message + ", sessionID: " + sessionID);
            log.info(String.format("clOrderID: %s, symbol: %s, side: %s",
                    message.getClOrdID().getValue(),
                    message.getSymbol().getValue(),
                    message.getSide().getValue()));
        }
    }
}

package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.*;
import quickfix.fix44.NewOrderSingle;

import java.time.LocalDateTime;

@RequestMapping("/testFix")
@RestController
public class TestFixMessage {

    @GetMapping("/order")
    public void testOrder() throws SessionNotFound {
        SessionID sessionID = new SessionID("FIX.4.4", "QUICKFIX_INITIATOR1", "QUICKFIX_ACCEPTOR");
        NewOrderSingle order = new NewOrderSingle();
        LocalDateTime date = LocalDateTime.now();
        order.set(new ClOrdID("mockedClOrdID"));
        order.set(new Account("mockedAccount"));
        order.set(new HandlInst('1'));
        order.set(new OrderQty(45.00));
        order.set(new Price(25.88));
        order.set(new Symbol("mockedSymbol"));
        order.set(new Side(Side.BUY)); // 对于枚举型对象也可以这么设置
        order.set(new OrdType(OrdType.LIMIT));
        Session.sendToTarget(order, sessionID);
    }
}

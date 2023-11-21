package com.fix;

import com.test.FixInitiator;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.*;
import quickfix.fix44.MarketDataRequest;
import quickfix.fix44.NewOrderSingle;

import java.time.LocalDateTime;

public class Client {

    public static void sendMarketDataRequest(SessionID sessionID) throws SessionNotFound {
        // 具体set哪些字段，参考你的FIX44.modified.xml
        MarketDataRequest req = new MarketDataRequest();
        req.set(new MDReqID("mockedMDReqID"));
        req.set(new SubscriptionRequestType('1'));

        // 重复组的设置
        MarketDataRequest.NoRelatedSym symGroup1 = new MarketDataRequest.NoRelatedSym();
        symGroup1.set(new Symbol("mockedSymbol1"));
        req.addGroup(symGroup1);

        MarketDataRequest.NoRelatedSym symGroup2 = new MarketDataRequest.NoRelatedSym();
        symGroup2.set(new Symbol("mockedSymbol2"));
        req.addGroup(symGroup2);

        System.out.println("Sending MarketDataRequest");
        Session.sendToTarget(req, sessionID);
    }

    /**
     * 下单
     * @param sessionID
     * @throws SessionNotFound
     */
    public static void sendNewOrderRequest(SessionID sessionID) throws SessionNotFound {
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

    public static void main(String[] args) {
        Initiator initiator = new Initiator();
        initiator.startServer();

        // 启动一个Session，记得参考你的quickfix.properties设定
        SessionID sessionID = new SessionID("FIX.4.4", "QUICKFIX_INITIATOR1", "QUICKFIX_ACCEPTOR");

        // 开始发点消息
        try {
            sendMarketDataRequest(sessionID);
            Thread.sleep(5000);
            sendNewOrderRequest(sessionID);
            Thread.sleep(5000);
        } catch (SessionNotFound | InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}

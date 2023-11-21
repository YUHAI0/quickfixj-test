package com.controller.order;

import com.fix.ClOrdIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.*;
import quickfix.fix50.NewOrderSingle;
import quickfix.fix50.component.Instrument;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/order")
public class OrderHandling {

    @Autowired
    private ClOrdIDGenerator clOrdIDGenerator;

    @Value("${fix.initiator.brokerNumber}")
    private String brokerNumber;

    @GetMapping("/newOrderSingle")
    public void newOrderSingle(
            @RequestParam String securityId,
            @RequestParam char orderType /* 1 market, 2 limit*/,
            @RequestParam char side /* 1 buy, 2 sell, 5 sell short*/,
            @RequestParam double orderQuantity
            ) throws SessionNotFound {
        NewOrderSingle order = new NewOrderSingle();
        order.setField(new ClOrdID(clOrdIDGenerator.doGenerate()));

        /* parties */
        NewOrderSingle.NoPartyIDs noPartyIDs = new NewOrderSingle.NoPartyIDs();
        noPartyIDs.setField(new PartyID(brokerNumber));
        noPartyIDs.setField(new PartyIDSource('D'));
        noPartyIDs.setField(new PartyRole(1));
        order.addGroup(noPartyIDs);

        //TODO add BCAN FIELD
        noPartyIDs.setField(new PartyID());
        noPartyIDs.setField(new PartyIDSource('D'));
        noPartyIDs.setField(new PartyRole(3));
        order.addGroup(noPartyIDs);

        /* instrument */
        Instrument instrument = new Instrument();
        instrument.setField(new SecurityID(securityId));
        instrument.setField(new SecurityIDSource("8"));
        order.setFields(instrument);

        order.setField(new OrdType(orderType));
        order.setField(new Side(side));
        order.setField(new OrderQty(orderQuantity));
        order.setField(new TransactTime(LocalDateTime.now()));

        /* disclosureInstruction */
        //TODO

        SessionID sessionID = new SessionID("FIX.4.4", "QUICKFIX_INITIATOR1", "QUICKFIX_ACCEPTOR");
        Session.sendToTarget(order, sessionID);
    }
}

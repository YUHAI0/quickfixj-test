package com.test;
import quickfix.*;
import quickfix.field.NoPartyIDs;
import quickfix.field.SecurityExchange;
import quickfix.field.SecurityID;
import quickfix.fix44.MarketDataSnapshotFullRefresh.NoMDEntries;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.Quote;
import quickfix.fix44.Message;
import quickfix.fix44.component.Instrument;
import quickfix.fix44.component.Parties;

public class Test {

    public static void main(String[] args) {
        new SecurityID();
        new NoMDEntries();
        new Quote.NoPartyIDs();
        Message message = new NewOrderSingle();
        message.setField(new SecurityID("AA"));
        MessageComponent comp = new Instrument();
        MessageComponent c = new Parties();
        NewOrderSingle.NoPartyIDs group = new NewOrderSingle.NoPartyIDs();
        group.setField(new SecurityExchange("AA"));
        group.setField(new SecurityID("AA"));
        c.setGroups(group);
        c.setField(new NoPartyIDs(1));
        message.addGroup(group);
        message.setFields(comp);


    }
}

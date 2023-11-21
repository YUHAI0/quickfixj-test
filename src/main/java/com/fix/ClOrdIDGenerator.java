package com.fix;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ClOrdIDGenerator {

    private String date;

    private int id;

    private void resetId() {
        id = 1000;
    }

    public String doGenerate() {
        String thisDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (thisDate.equals(date)) {
            date = thisDate;
            resetId();
        }
        String gen = String.valueOf(id);
        id++;
        return gen;
    }

    @PostConstruct
    public void init() {
        date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        resetId();
    }
}

package com.galiev.sprg.core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    private  int id;
    private String msg;
    private Date date;
    private DateFormat df;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
        this.id = AUTO_ID.getAndIncrement();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

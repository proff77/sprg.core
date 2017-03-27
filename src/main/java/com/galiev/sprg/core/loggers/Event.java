package com.galiev.sprg.core.loggers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
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

    Random random = new Random(10000);

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
        this.id = random.nextInt();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

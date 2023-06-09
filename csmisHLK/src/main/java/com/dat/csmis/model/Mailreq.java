package com.dat.csmis.model;


public class Mailreq {
    
    private String name;
    private String to;
    private String from;
    private String subject;

    
    public Mailreq() {
    }
    
    @Override
    public String toString() {
        return "Mailreq [name=" + name + ", to=" + to + ", from=" + from + ", subject=" + subject + "]";
    }

    public Mailreq(String name, String to, String from, String subject) {
        this.name = name;
        this.to = to;
        this.from = from;
        this.subject = subject;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    
}

package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vente {
    private int id;
    private double total;
    private Client client;
    private List<LigneCmd> lcmds;
    private String date;

    public Vente(int id, double total, Client client) {
        this.id = id;
        this.total = total;
        this.client = client;
        this.date= String.valueOf(LocalDate.now());
    }
    public Vente(Client client,List<LigneCmd> cmd) {
        this.id = id;
        this.total = total;
        this.client = client;
        this.date= String.valueOf(LocalDate.now());
    }

    public Vente() {
        this.lcmds=new ArrayList<>();this.date= String.valueOf(LocalDate.now());
    }

    public Vente(int id, double total, Client client,String date,List<LigneCmd> cmd) {
        this.id = id;
        this.total = total;
        this.client = client;
        this.lcmds=cmd;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneCmd> getLcmds() {
        return lcmds;
    }
    public void setLcmds(List<LigneCmd> lcmds) {
        this.lcmds = lcmds;
    }
}

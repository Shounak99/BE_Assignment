package com.util.Inventory.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String name;
    private String batch;
    private Long stock;
    private Long deal;
    private Long free;
    private Double mrp;
    private Double rate;
    private Date expiry;
    private String company;
    private String supplier;

    public Item() {
    }

    public Item(Long id, String code, String name, String batch, Long stock, Long deal, Long free, Double mrp, Double rate, Date expiry, String company, String supplier) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.batch = batch;
        this.stock = stock;
        this.deal = deal;
        this.free = free;
        this.mrp = mrp;
        this.rate = rate;
        this.expiry = expiry;
        this.company = company;
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getBatch() {
        return batch;
    }

    public Long getStock() {
        return stock;
    }

    public Long getDeal() {
        return deal;
    }

    public Long getFree() {
        return free;
    }

    public Double getMrp() {
        return mrp;
    }

    public Double getRate() {
        return rate;
    }

    public Date getExpiry() {
        return expiry;
    }

    public String getCompany() {
        return company;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public void setDeal(Long deal) {
        this.deal = deal;
    }

    public void setFree(Long free) {
        this.free = free;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", batch='" + batch + '\'' +
                ", stock=" + stock +
                ", deal=" + deal +
                ", free=" + free +
                ", mrp=" + mrp +
                ", rate=" + rate +
                ", expiry=" + expiry +
                ", company='" + company + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}

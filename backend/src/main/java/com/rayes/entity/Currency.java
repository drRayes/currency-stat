package com.rayes.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Currency {
    @Id
    private Long id;

    private String stringId;
    private Long numCode;
    private String charCode;
    private Long nominal;
    private String name;
    private BigDecimal currentValue;
    private BigDecimal previousValue;

    public Currency() {

    }

    public Currency(String stringId, Long numCode, String charCode, Long nominal, String name,
                    BigDecimal currentValue, BigDecimal previousValue) {
        this.stringId = stringId;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.currentValue = currentValue;
        this.previousValue = previousValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public Long getNumCode() {
        return numCode;
    }

    public void setNumCode(Long numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Long getNominal() {
        return nominal;
    }

    public void setNominal(Long nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public BigDecimal getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(BigDecimal previousValue) {
        this.previousValue = previousValue;
    }

    @Override
    public String toString() {
        return "ID = " + stringId
                + " numCode " + numCode
                + " charCode " + charCode
                + " nominal " + nominal
                + " name " + name
                + " currentValue " + currentValue
                + " previousValue " + previousValue;
    }
}

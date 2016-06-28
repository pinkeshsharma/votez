/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.model;

/**
 *
 * @author Pinkesh
 */
public class Party {

    private Address address;
    private String partyName;
    private String PartySymbol;
    private String PartyIdeology;
    private String PhoneNumber;
    private String Email;
    private String PartyChairperson;
    private String Founded;
    private String count;

    public Party() {
    }

    public Party(Address address, String partyName, String PartySymbol, String PartyIdeology, String PhoneNumber, String Email, String PartyChairperson, String Founded) {
        this.address = address;
        this.partyName = partyName;
        this.PartySymbol = PartySymbol;
        this.PartyIdeology = PartyIdeology;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.PartyChairperson = PartyChairperson;
        this.Founded = Founded;
    }
    
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartySymbol() {
        return PartySymbol;
    }

    public void setPartySymbol(String PartySymbol) {
        this.PartySymbol = PartySymbol;
    }

    public String getPartyIdeology() {
        return PartyIdeology;
    }

    public void setPartyIdeology(String PartyIdeology) {
        this.PartyIdeology = PartyIdeology;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPartyChairperson() {
        return PartyChairperson;
    }

    public void setPartyChairperson(String PartyChairperson) {
        this.PartyChairperson = PartyChairperson;
    }

    public String getFounded() {
        return Founded;
    }

    public void setFounded(String Founded) {
        this.Founded = Founded;
    }
    
    
}

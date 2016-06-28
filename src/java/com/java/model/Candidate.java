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
public class Candidate {

    private String candidateID;
    private String firstName;
    private String lastName;
    private String addressID;
    private String phoneNumber;
    private String email;
    private String partyID;
    private String totalAssets;
    private String accountNumber;
    private String accountHoldingBank;
    private Address address;
    private Party party;
    private Election election;
    private String count;

    public Candidate() {
    }

    public Candidate(String candidateID, String firstName, String lastName, String addressID, String phoneNumber, String email, String partyID, String totalAssets, String accountNumber, String accountHoldingBank) {
        this.candidateID = candidateID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressID = addressID;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.partyID = partyID;
        this.totalAssets = totalAssets;
        this.accountNumber = accountNumber;
        this.accountHoldingBank = accountHoldingBank;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public String getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(String candidateID) {
        this.candidateID = candidateID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPartyID() {
        return partyID;
    }

    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    public String getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(String totalAssets) {
        this.totalAssets = totalAssets;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHoldingBank() {
        return accountHoldingBank;
    }

    public void setAccountHoldingBank(String accountHoldingBank) {
        this.accountHoldingBank = accountHoldingBank;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}

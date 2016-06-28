/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.model;

import java.io.Serializable;

/**
 *
 * @author Pinkesh
 */
public class Election implements Serializable {
    private String electionID;
    private String electionType;
    private String district;
    private String timePeriod;
    private String state;
    private String electionWinner;
    private Candidate candiate;
    
    public Election() {
    }

    public Election(String electionID, String electionType, String district, String timePeriod, String state, String electionWinner) {
        this.electionID = electionID;
        this.electionType = electionType;
        this.district = district;
        this.timePeriod = timePeriod;
        this.state = state;
        this.electionWinner = electionWinner;
    }

    public Candidate getCandiate() {
        return candiate;
    }

    public void setCandiate(Candidate candiate) {
        this.candiate = candiate;
    }

    public String getElectionID() {
        return electionID;
    }

    public void setElectionID(String ElectionID) {
        this.electionID = ElectionID;
    }

    public String getElectionType() {
        return electionType;
    }

    public void setElectionType(String ElectionType) {
        this.electionType = ElectionType;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String District) {
        this.district = District;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String TimePeriod) {
        this.timePeriod = TimePeriod;
    }

    public String getState() {
        return state;
    }

    public void setState(String State) {
        this.state = State;
    }


    public String getElectionWinner() {
        return electionWinner;
    }

    public void setElectionWinner(String ElectionWinner) {
        this.electionWinner = ElectionWinner;
    }
    
   
}

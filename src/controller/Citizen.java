package controller;

import data.DocPath;
import data.Nif;
import data.PINcode;
import data.Password;

import java.util.Date;

public class Citizen {
    private Nif nif;
    private Date valdate;
    private DocPath savePath;


    // Optional
    private Password password;
    private String phoneNumber;
    private PINcode PIN;


    private boolean reinforcedPINActivated = false;

    public Citizen() {
        this.nif = null;
        this.valdate = null;
        this.savePath = null;

        // Optional
        this.password = null;
        this.phoneNumber = null;
    }

    public void setNif(Nif nif) {
        this.nif = nif;
    }

    public void setValDate(Date valdate) {
        this.valdate = valdate;
    }

    public void setSavePath(DocPath savePath) {
        this.savePath = savePath;
    }

    public Nif getNif() {
        return this.nif;
    }

    public Date getValDate() {
        return this.valdate;
    }

    public DocPath getSavePath() {
        return this.savePath;
    }

    // Optional
    public void setPassword(Password passwd) {
        this.password = passwd;
    }

    public Password getPassword() {
        return this.password;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() { return this.phoneNumber; }

    public PINcode getPIN() {
        return PIN;
    }

    public void setPIN(PINcode PIN) {
        this.PIN = PIN;
    }

    public boolean hasReinforcedPINActivated() {
        return reinforcedPINActivated;
    }

    public void setReinforcedPINActivated(boolean reinforcedPINActivated) {
        this.reinforcedPINActivated = reinforcedPINActivated;
    }
}

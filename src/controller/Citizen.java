package controller;

import controller.interfaces.CitizenInterface;
import data.*;

import java.util.Date;

public class Citizen implements CitizenInterface {
    private Nif nif;
    private Date valdate;
    private DocPath savePath;


    // Optional
    private Password password;
    private String phoneNumber;
    private PINcode PIN;
    private AccredNumb accredNumb;
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

    public Nif getNif() {
        return this.nif;
    }

    public void setValDate(Date valdate) {
        this.valdate = valdate;
    }

    public Date getValDate() {
        return this.valdate;
    }

    public void setSavePath(DocPath savePath) {
        this.savePath = savePath;
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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPIN(PINcode PIN) {
        this.PIN = PIN;
    }

    public PINcode getPIN() {
        return PIN;
    }

    public void setAccredNumb(AccredNumb accredNumb) {
        this.accredNumb = accredNumb;
    }

    public AccredNumb getAccredNumb() {
        return accredNumb;
    }

    public void setReinforcedPINActivated(boolean reinforcedPINActivated) {
        this.reinforcedPINActivated = reinforcedPINActivated;
    }

    public boolean hasReinforcedPINActivated() {
        return reinforcedPINActivated;
    }
}

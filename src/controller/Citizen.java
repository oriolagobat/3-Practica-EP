package controller;

import data.DocPath;
import data.Nif;
import data.Password;

import java.util.Date;

public class Citizen {
    Nif nif;
    Date valdate;
    DocPath savePath;

    // Optional
    Password password;

    public Citizen() {
        this.nif = null;
        this.valdate = null;
        this.savePath = null;

        // Optional
        this.password = null;
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
}

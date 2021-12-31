package controller.interfaces;

import data.*;

import java.util.Date;

public interface CitizenInterface {
    void setNif(Nif nif);

    Nif getNif();

    void setValDate(Date valdate);

    Date getValDate();

    void setSavePath(DocPath savepath);

    // Optional

    void setPassword(Password passwd);

    Password getPassword();

    void setPhoneNumber(String phoneNumber);

    String getPhoneNumber();

    void setPIN(PINcode PIN);

    PINcode getPIN();

    void setAccredNumb(AccredNumb accredNumb);

    AccredNumb getAccredNumb();

    void setReinforcedPINActivated(boolean reinforcedPINActivated);

    boolean hasReinforcedPINActivated();
}

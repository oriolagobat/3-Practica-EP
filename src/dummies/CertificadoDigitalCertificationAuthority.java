package dummies;

import controller.Citizen;
import controller.exceptions.*;
import data.*;
import data.exceptions.NotValidCertificateException;
import services.CertificationAuthorityInterface;

import javax.xml.crypto.dsig.keyinfo.X509IssuerSerial;
import java.net.ConnectException;
import java.nio.charset.Charset;
import java.util.Date;

public class CertificadoDigitalCertificationAuthority implements CertificationAuthorityInterface {

    Citizen citizen;

    public CertificadoDigitalCertificationAuthority(Citizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
        return false;
    }

    @Override
    public boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException {
        return false;
    }

    @Override
    public byte ckeckCredent(Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
        return 0;
    }

    @Override
    public EncryptedData sendCertfAuth(EncryptingKey pubKey) throws NotValidCertificateException, ConnectException {

        byte[] data = citizen.getNif().getNif().getBytes(Charset.defaultCharset());
        for (int i = 0; i < data.length; i++) {
            int tmp = data[i];
            tmp += pubKey.getEncryptingKey().intValue();
            data[i] = (byte) tmp;
        }

        return new EncryptedData(data);
    }
}

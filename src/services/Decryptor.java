package services;

import data.EncryptedData;
import data.EncryptingKey;
import data.Nif;
import data.exceptions.WrongNifFormatException;
import services.exceptions.DecryptationException;

import java.nio.charset.Charset;

public class Decryptor {
    public static Nif decryptIDdata(EncryptedData encrypData, EncryptingKey privKey)
            throws DecryptationException, WrongNifFormatException {

        if (encrypData == null || privKey == null)
            throw new DecryptationException("Error en el desxifratge");

        byte[] data = encrypData.getEncryptedData();
        for(int i = 0; i < data.length; i++){
            int tmp = data[i];
            tmp -= privKey.getEncryptingKey().intValue();
            data[i] = (byte) tmp;
        }

        return new Nif(new String(data, Charset.defaultCharset()));
    }
}

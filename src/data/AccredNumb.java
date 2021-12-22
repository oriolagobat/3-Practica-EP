package data;

import exceptions.WrongAccredNumbFormatException;

public class AccredNumb {
    // The accreditation number in the Spanish state.
    private final String accred_num;

    public AccredNumb(String number) throws WrongAccredNumbFormatException {
        if (number == null) throw new NullPointerException("El nombre d'acreditació és null");

        if (number.length() != 9)
            throw new WrongAccredNumbFormatException("El nombre d'acreditació no és de la llargada correcta");

        if (lettersInAccredNumb(number))
            throw new WrongAccredNumbFormatException("El nombre d'acreditació conté lletres");
        this.accred_num = number;
    }

    private boolean lettersInAccredNumb(String accred_num) {
        char[] num = accred_num.toCharArray();
        for (char c : num) {
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    public String getAccredNum() {
        return accred_num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccredNumb number = (AccredNumb) o;
        return accred_num.equals(number.accred_num);
    }

    @Override
    public int hashCode() {
        return accred_num.hashCode();
    }

    @Override
    public String toString() {
        return "AccredNum{" + "número acreditación ciudadano='" + accred_num + '\'' + '}';
    }
}

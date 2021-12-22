package data;

import exceptions.WrongAccredNumbFormatException;

/**
 * Essential data classes
 */
final public class AccredNumb {
    // The accreditation number in the Spanish state.
    private final String accredNum;

    public AccredNumb(String number) throws WrongAccredNumbFormatException {
        checkAccredNum(number);

        this.accredNum = number;
    }

    private void checkAccredNum(String number) throws WrongAccredNumbFormatException {
        if (number == null) throw new NullPointerException("El nombre d'acreditació és null");

        if (number.length() != 9)
            throw new WrongAccredNumbFormatException("El nombre d'acreditació no és de la llargada correcta");

        if (lettersInAccredNumb(number))
            throw new WrongAccredNumbFormatException("El nombre d'acreditació conté lletres");
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
        return accredNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccredNumb number = (AccredNumb) o;
        return accredNum.equals(number.accredNum);
    }

    @Override
    public int hashCode() {
        return accredNum.hashCode();
    }

    @Override
    public String toString() {
        return "AccredNum{" + "número acreditación ciudadano='" + accredNum + '\'' + '}';
    }
}

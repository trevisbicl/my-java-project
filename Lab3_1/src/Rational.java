public class Rational {

    private int num;
    private int den;

    public Rational(String rationalNumber) {
        if (rationalNumber.length() == 1 && Character.isDigit(rationalNumber.charAt(0))) {
            this.num = Integer.parseInt(rationalNumber);
            this.den = 1;
            return;
        }

        String[] strings = rationalNumber.split("/");
        int tempNum = Integer.parseInt(strings[0]);
        int tempDen = Integer.parseInt(strings[1]);

        if (tempDen == 0) {
            throw new ArithmeticException("Знаменатель не должна быть 0!");
        }

        this.num = tempNum;
        this.den = tempDen;
    }

    public int getNum() {
        return this.num;
    }

    public int getDen() {
        return this.den;
    }

    public Rational add(Rational other) {
        int tempNum = this.num * other.den + other.num * this.den;
        int tempDen = this.den * other.den;
        return new Rational(tempNum + "/" + tempDen);
    }

    public Rational sonofAnton(Rational other) {
        int tempNum = this.num * other.num;
        int tempDen = this.den * other.den;
        return new Rational(tempNum + "/" + tempDen);
    }
}

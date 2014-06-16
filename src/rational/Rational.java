package rational;

import java.math.*;

public class Rational {
	private BigInteger denominator, numerator;

	/**
	 * Constructs a Fraction n/d.
	 * 
	 * @param n
	 * @param d
	 */
	public Rational(BigInteger n, BigInteger d) {
		super();
		this.denominator = d;
		this.numerator = n;
	}

	/**
	 * Constructs a Fraction 0/1.
	 */
	public Rational() {
		super();
		denominator = BigInteger.ONE;
		numerator = BigInteger.ZERO;
	}

	/**
	 * Constructs a Fraction i/1.
	 * 
	 * @param i
	 */
	public Rational(BigInteger i) {
		super();
		denominator = BigInteger.ONE;
		numerator = i;
	}

	/**
	 * Constructs a Fraction n/d.
	 * 
	 * @param n
	 * @param d
	 */
	public Rational(double d) {
		super();
		String s = String.valueOf(d);
		int digitsDec = s.length() - 1 - s.indexOf('.');

		BigInteger denominator = BigInteger.ONE;
		for (int i = 0; i < digitsDec; i++) {
			d *= 10;
			denominator.multiply(BigInteger.TEN);
		}
		BigInteger numerator = BigInteger.valueOf((long) Math.round(d));

		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * getter for denominator
	 */
	public BigInteger getDenominator() {
		return denominator;
	}
	/**
	 * getter for numerator
	 */
	public BigInteger getNumerator() {
		return numerator;
	}
	/**
	 * setter for rational
	 */
	public void setValue(BigInteger numerator, BigInteger denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * Computes the greatest common divisor (gcd) of the two inputs.
	 * 
	 * @param x
	 * @param y
	 * @return the absolute value gcd of x and y
	 */
	static private BigInteger gcd(BigInteger x, BigInteger y) {
		if(x.multiply(y)==BigInteger.ZERO){
			return BigInteger.ZERO;
		}
		x = x.abs();
		y = y.abs();
		while(x!=BigInteger.ONE){
			BigInteger temp = y.mod(x);
			if (temp == BigInteger.ZERO){
			    break;
			}
			y = x;
			x = temp;
		}
		return x;
	}

	/**
	 * Adds a Rational r to this
	 * @param r 
	 */
	public void add(Rational r) {
		BigInteger x = (this.numerator.multiply(r.getDenominator())).add(r.getNumerator().multiply(this.denominator));
		BigInteger y = r.getDenominator().multiply(this.denominator);
		BigInteger xygcd = gcd( x, y);
		this.denominator = y.divide(xygcd);
		this.numerator = x.divide(xygcd);
	}
	/**
	 * Subtracts a Rational r from this
	 * @param r 
	 */
	public void subtract(Rational r) {
		BigInteger x = (this.numerator.multiply(r.getDenominator())).subtract(r.getNumerator().multiply(this.denominator));
		BigInteger y = r.getDenominator().multiply(this.denominator);
		BigInteger xygcd = gcd( x, y);
		this.denominator = y.divide(xygcd);
		this.numerator = x.divide(xygcd);
	}
	/**
	 * Multiples a Rational r with this
	 * @param r 
	 */
	public void multiple(Rational r) {
		BigInteger x = this.numerator.multiply(r.getNumerator());
		BigInteger y = r.getDenominator().multiply(this.denominator);
		BigInteger xygcd = gcd( x, y);
		this.denominator = y.divide(xygcd);
		this.numerator = x.divide(xygcd);
	}
	
	/**
	 * Divides this by r
	 * @param r 
	 */
	public void divide(Rational r) {
		BigInteger x = this.numerator.multiply(r.getDenominator());
		BigInteger y = this.denominator.multiply(r.getDenominator());
		BigInteger xygcd = gcd( x, y);
		this.denominator = y.divide(xygcd);
		this.numerator = x.divide(xygcd);
	}
	
	/**
	 * return value in the form of double
	 * @param r 
	 */
	public double toDouble() {
		return numerator.divide(denominator).doubleValue();
	}
	
	
}

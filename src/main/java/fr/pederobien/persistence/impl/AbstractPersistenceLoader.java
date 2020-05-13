package fr.pederobien.persistence.impl;


import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import fr.pederobien.persistence.interfaces.IPersistenceLoader;
import fr.pederobien.persistence.interfaces.IUnmodifiableNominable;

public class AbstractPersistenceLoader<T extends IUnmodifiableNominable> implements IPersistenceLoader<T> {
	private Double version;
	private T elt;

	protected AbstractPersistenceLoader(Double version, T elt) {
		this.version = version;
		this.elt = elt;
	}

	@Override
	public Double getVersion() {
		return version;
	}

	@Override
	public T get() {
		return elt;
	}

	/**
	 * Parses the string argument as a signed decimal integer. The characters in the string must all be decimal digits, except that
	 * the first character may be an ASCII minus sign {@code '-'} ({@code '\u005Cu002D'}) to indicate a negative value or an ASCII
	 * plus sign {@code '+'} ({@code '\u005Cu002B'}) to indicate a positive value. The resulting integer value is returned, exactly as
	 * if the argument and the radix 10 were given as arguments to the {@link #parseInt(java.lang.String, int)} method.
	 *
	 * @param number a {@code String} containing the {@code int} representation to be parsed.
	 * 
	 * @return the integer value represented by the argument in decimal.
	 * 
	 * @exception NumberFormatException if the string does not contain a parsable integer.
	 */
	protected int toInt(String number) {
		return Integer.parseInt(number);
	}

	/**
	 * Returns a new {@code double} initialized to the value represented by the specified {@code String}, as performed by the
	 * {@code valueOf} method of class {@code Double}.
	 *
	 * @param s the string to be parsed.
	 * 
	 * @return the {@code double} value represented by the string argument.
	 * 
	 * @throws NullPointerException  if the string is null.
	 * @throws NumberFormatException if the string does not contain a parsable {@code double}.
	 * 
	 * @see java.lang.Double#valueOf(String)
	 */
	protected double toDouble(String number) {
		return Double.parseDouble(number);
	}

	/**
	 * Parses the string argument as a boolean. The {@code boolean} returned represents the value {@code true} if the string argument
	 * is not {@code null} and is equal, ignoring case, to the string {@code "true"}.
	 * <p>
	 * Example: {@code Boolean.parseBoolean("True")} returns {@code true}.<br>
	 * Example: {@code Boolean.parseBoolean("yes")} returns {@code false}.
	 *
	 * @param bool the {@code String} containing the boolean representation to be parsed.
	 * @return the boolean represented by the string argument.
	 * @since 1.5
	 */
	protected boolean toBool(String bool) {
		return Boolean.parseBoolean(bool);
	}

	/**
	 * Obtains an instance of {@code LocalTime} from a text string such as {@code 10:15}.
	 * <p>
	 * The string must represent a valid time and is parsed using {@link java.time.format.DateTimeFormatter#ISO_LOCAL_TIME}.
	 *
	 * @param text the text to parse such as "10:15:30", not null.
	 * 
	 * @return the parsed local time, not null.
	 * 
	 * @throws DateTimeParseException if the text cannot be parsed.
	 */
	protected LocalTime toLocalTime(String time) {
		return LocalTime.parse(time);
	}
}

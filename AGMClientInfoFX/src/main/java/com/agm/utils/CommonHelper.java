package com.agm.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonHelper {

	/**
	 * 
	 * @param obj
	 *            any object to test if the value is valid
	 * @return boolean true if value is valude and false if not
	 */
	public static boolean hasValidValue(final Object obj) {
		if (obj != null) {
			if (obj.getClass() == String.class) {
				final String strValue = obj.toString();
				if (strValue.trim().length() != 0 && strValue.equalsIgnoreCase(Constants.EMPTY_STRING) == false) {
					return true;
				}
			} else if (isNumber(obj.toString())) {
				double number = toDecimalNumber(obj.toString());
				if (number > 0) {
					return true;
				}
			} else if (obj.getClass() == Date.class) {
				return true;
			} else if (obj.getClass() == ArrayList.class) {
				return true;
			} else if (obj.getClass() == Timestamp.class) {
				return true;
			} else if (obj.getClass() == Boolean.class) {
				return true;
			} else if (obj.getClass() == Double.class) {
				return obj.toString().length() > 0;
			}
		}

		return false;
	}
	
	/**
	 * 
	 * @param value
	 * @return return true if entered String is a valid Number
	 */

	public static boolean isNumber(Object value) {
		if (value != null) {
			// String regex = "[0-9]+(\\.[0-9]+)?";
			final String regex = "^(\\-)?[0-9]+(\\.[0-9]+)?$";
			final Pattern pattern = Pattern.compile(regex);
			final Matcher matcher = pattern.matcher(value.toString());
			return matcher.find();
		}
		return false;
	}
	
	/**
	 * 
	 * @param strNum
	 *            - String to convert to a decimal number
	 * @return
	 */

	public static Double toDecimalNumber(Object strNum) {
		if (hasValidValue(strNum) && isNumber(strNum.toString())) {
			return Double.parseDouble(strNum.toString());
		} else {
			return new Double("0");
		}
	}
	
	/**
	 * Case-insensitive Matching
	 * 
	 * @param valueOne
	 * @param valueTwo
	 * @return returns true if two object has the same value
	 */
	public static boolean isEqual(final Object valueOne, final Object valueTwo) {
		if (hasValidValue(valueOne) == false && hasValidValue(valueTwo) == false) {
			return true;
		} else if ((valueOne == null && valueTwo != null) || (valueOne != null && valueTwo == null)) {
			return false;
		} else if (valueOne.getClass() == String.class && valueTwo.getClass() == String.class) {
			return valueOne.toString().trim().equalsIgnoreCase(valueTwo.toString().trim());
		} else if (isNumber(valueOne.toString()) && isNumber(valueTwo.toString())) {
			return toDecimalNumber(valueOne.toString()).equals(toDecimalNumber(valueTwo.toString()));
		} else if (valueOne.getClass() == valueTwo.getClass()) {
			return valueOne.toString().equals(valueTwo.toString());
		}
		return false;
	}
}

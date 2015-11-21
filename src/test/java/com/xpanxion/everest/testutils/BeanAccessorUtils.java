package com.xpanxion.everest.testutils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * <p>
 * A set of utilities for testing.
 * </p>
 * 
 * @author jon feauto, brian smith
 * @since Oct 9, 2013
 */
public class BeanAccessorUtils {

	private static final int FIELD_IDX = 1;

	public static final Random RAND = new Random();

	private static final int START_IDX = 0;

	/**
	 * <p>
	 * Creates a random string.
	 * </p>
	 * 
	 * @return a random string
	 */
	public static String createRandomString() {

		return Double.toString(RAND.nextDouble());
	}

	/**
	 * <p>
	 * Attempts to create a new object based on the class passed in. If one
	 * cannot be created either an exception will be thrown or null returned.
	 * </p>
	 * 
	 * @param clazz
	 *            the class to instantiate
	 * @return a new object of the passed in class.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static Object getNewValue(final Class<?> clazz)
			throws InstantiationException, IllegalAccessException {
		Object retVal = null;
		if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
			retVal = RAND.nextBoolean();
		} else if (clazz.equals(char.class) || clazz.equals(Character.class)) {
			retVal = createRandomString().charAt(0);
		} else if (clazz.equals(byte.class) || clazz.equals(Byte.class)) {
			final byte[] bytes = new byte[1];
			RAND.nextBytes(bytes);
			retVal = bytes[0];
		} else if (clazz.equals(short.class) || clazz.equals(Short.class)) {
			retVal = new Float(RAND.nextFloat()).shortValue();
		} else if (clazz.equals(int.class) || clazz.equals(Integer.class)) {
			retVal = RAND.nextInt();
		} else if (clazz.equals(float.class) || clazz.equals(Float.class)) {
			retVal = RAND.nextFloat();
		} else if (clazz.equals(double.class) || clazz.equals(Double.class)) {
			retVal = RAND.nextDouble();
		} else if (clazz.equals(long.class) || clazz.equals(Long.class)) {
			retVal = RAND.nextLong();
		} else if (clazz.equals(String.class)) {
			retVal = createRandomString();
		} else if (clazz.equals(java.sql.Date.class)) {
			retVal = new java.sql.Date(System.currentTimeMillis());
		} else {
			retVal = clazz.newInstance();
		}
		return retVal;
	}

	/**
	 * <p>
	 * Removes any failures that should be omitted from the testResults String
	 * by using the {@code String[][] removals} parameter.
	 * </p>
	 * <p>
	 * Each line that should be omitted should be passed in as a "row" in
	 * {@code String[][] removals}. The first string in a row denotes the
	 * "starting string" of the failure message while the second string in the
	 * row denotes the "ending string" of the failure message. The first
	 * instance of these two strings and all characters in between them will be
	 * removed, hence removing the failure string from the results.
	 * </p>
	 * 
	 * @param testResults
	 *            The string containing failure results.
	 * @param removals
	 *            The two dimensional string containing "rows" of start and end
	 *            strings to search for and remove from {@code testResults}.
	 * @return An updated testResults with the specified failures removed.
	 */
	public static String removeIgnoredFailures(String testResults,
			String[][] removals) {
		String rv = testResults;

		for (String[] removal : removals) {
			int startPos = rv.indexOf(removal[START_IDX]);
			int endPos = rv.indexOf(removal[FIELD_IDX]);

			if (startPos != -1 && endPos != -1 && endPos > startPos) {
				String contentToReplace = rv.substring(startPos, endPos
						+ removal[FIELD_IDX].length());
				rv = rv.replace(contentToReplace, "");
			}
		}

		return rv.trim();
	}

	/**
	 * <p>
	 * Tests an objects accessor methods.
	 * </p>
	 * 
	 * @param testee
	 *            the object to test
	 * @param field
	 *            the field on the object being tested
	 * @param getter
	 *            the getter to test
	 * @param setter
	 *            the setter to test
	 */
	private static String testBeanAccessor(final Object testee,
			final Field field, final Method getter, final Method setter) {
		final StringBuilder retVal = new StringBuilder("");
		boolean defaultTestException = false;
		try {
			// get the default value of the field being tested and verify that
			// is what
			// the default is from the getter.
			field.setAccessible(true);
			final Object defaultValue = field.get(testee);
			final Object returned = getter.invoke(testee);

			boolean failed = false;
			if (returned == null || defaultValue == null) {
				failed = returned != defaultValue;
			} else {
				failed = !returned.equals(defaultValue);
			}

			if (failed) {
				retVal.append("Expected a default value of " + defaultValue
						+ " but " + returned
						+ " was returned for default value test in class "
						+ testee.getClass().getName() + " for field "
						+ field.getName() + "\n");
			}
		} catch (final IllegalArgumentException e) {
			defaultTestException = true;
		} catch (final IllegalAccessException e) {
			defaultTestException = true;
		} catch (final InvocationTargetException e) {
			defaultTestException = true;
		}

		boolean setGetTestException = false;
		try {
			// Gets an object to set in the setter and then verifies that is
			// what is
			// returned.
			final Object setValue = getNewValue(field.getType());
			setter.invoke(testee, setValue);
			final Object returnedValue = getter.invoke(testee);
			if (returnedValue == null && setValue != null
					|| !returnedValue.equals(setValue)) {
				retVal.append("The value returned of " + setValue
						+ " is not equal to the returned value of "
						+ returnedValue + " for field " + field.getName()
						+ " in class " + testee.getClass().getName() + "\n");
			}
		} catch (final InstantiationException e) {
			setGetTestException = true;
		} catch (final IllegalAccessException e) {
			setGetTestException = true;
		} catch (final IllegalArgumentException e) {
			setGetTestException = true;
		} catch (final InvocationTargetException e) {
			setGetTestException = true;
		}
		return retVal.toString();
	}

	/**
	 * <p>
	 * Attempts to test a class' accessors and default values. If a particular
	 * accessor cannot be found or default value not found, the test will not
	 * fail, the utility will move onto the next field. Only actual failures
	 * should fail the test.
	 * </p>
	 * 
	 * @param clazz
	 *            the class to test
	 */
	public static String testBeanClass(final Class<?> clazz) {
		final StringBuilder results = new StringBuilder("");
		Object testee = null;
		// first verify that we can create the class being tested.
		try {
			testee = clazz.newInstance();
		} catch (final InstantiationException e) {
		} catch (final IllegalAccessException e) {
		}

		if (testee != null) {
			final Field[] fields = clazz.getDeclaredFields();
			for (final Field field : fields) {

				// for each field we want to create a property descriptor.
				PropertyDescriptor propDiscriptor = null;
				String fieldName = field.getName();
				// ignore the serial version ID as it dosen't have accessors
				if (fieldName == "serialVersionUID") {
					continue;
				}

				// take into account the possibility of someone placing 'm' in
				// front of
				// their field names
				if (fieldName.matches("m[A-Z].*")) {
					final StringBuilder fieldNameBuilder = new StringBuilder();
					fieldNameBuilder.append(Character.toLowerCase(fieldName
							.charAt(1)));
					fieldNameBuilder.append(fieldName.substring(2));
					fieldName = fieldNameBuilder.toString();
				}

				// Take into account that boolean values often times have an
				// 'is'
				// prefix.
				if (boolean.class.equals(field.getType())
						&& fieldName.matches("is[A-Z].*")) {
					final StringBuilder fieldNameBuilder = new StringBuilder();
					fieldNameBuilder.append(Character.toLowerCase(fieldName
							.charAt(2)));
					fieldNameBuilder.append(fieldName.substring(3));
					fieldName = fieldNameBuilder.toString();
				}

				// try to create the descriptor
				try {
					propDiscriptor = new PropertyDescriptor(fieldName, clazz);
				} catch (final IntrospectionException e) {
				}

				// get the reader and writer methods of the property and test
				// them.
				if (propDiscriptor != null) {
					final Method writeMethod = propDiscriptor.getWriteMethod();
					final Method readMethod = propDiscriptor.getReadMethod();
					if (writeMethod != null && readMethod != null) {
						results.append(testBeanAccessor(testee, field,
								readMethod, writeMethod));
					}
				}
			}
			results.append(testBeanToString(testee));
			results.append(testBeanEqualsAndHashCode(clazz));
		}
		return results.toString();
	}

	/**
	 * <p>
	 * checks for class's equals and hashCode() metehod's implementation
	 * </p>
	 * 
	 * @param clazz
	 * @return error string
	 */
	private static String testBeanEqualsAndHashCode(final Class<?> clazz) {
		Object testObjRef = null;
		Object testObjCmp = null;

		try {
			clazz.getDeclaredMethod("equals", Object.class);
			clazz.getDeclaredMethod("hashCode");
		} catch (SecurityException e1) {
			// This will never come
			return "";
		} catch (NoSuchMethodException e1) {
			// equals/hashcode not implemented, skip test
			return "";
		}

		try {
			testObjRef = clazz.newInstance();

			// hashcode() must return same number for multiple invocations
			if (testObjRef.hashCode() != testObjRef.hashCode()) {
				return "\nhashcode not implemented properly for " + clazz;
			}
			if (testObjRef.equals(new Object())) {
				return "\nequals not implemented properly for " + clazz;
			}

			// null should get handled by equals() properly while doing equality
			// check
			if (testObjRef.equals(null)) {
				return "\nequals not implemented properly for " + clazz;
			}

			// if object ref are same, equals must return true
			if (!testObjRef.equals(testObjRef)) {
				return "\nequals not implemented properly for " + clazz;
			}

			testObjCmp = clazz.newInstance();
			// equals must return true for same objects with default instance
			// values.
			if (!testObjRef.equals(testObjCmp)) {
				return "\nequals not implemented properly for " + clazz;
			}

		} catch (final Exception e) {
		}
		return "";
	}

	/**
	 * <p>
	 * checks if standard implementation of toString is getting used throughout
	 * the application.
	 * </p>
	 * 
	 * @param testee
	 * @return error string if toString implementation is improper
	 */
	private static String testBeanToString(final Object testee) {

		try {
			testee.getClass().getDeclaredMethod("toString");
		} catch (SecurityException e1) {
			// This will never come
			return "";
		} catch (NoSuchMethodException e1) {
			// toString not implemented, skip test
			return "";
		}

		if (testee.toString()
				.equals(ToStringBuilder.reflectionToString(testee))) {
			return "";
		} else {
			return "\nvalue returned by toString and reflectionToString does not match for "
					+ testee.getClass();
		}
	}

}

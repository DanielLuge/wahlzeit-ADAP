package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.CartesianCoordinateValues.X;
import org.wahlzeit.model.CartesianCoordinateValues.Y;
import org.wahlzeit.model.CartesianCoordinateValues.Z;
import org.wahlzeit.model.SphericCoordinateValues.Phi;
import org.wahlzeit.model.SphericCoordinateValues.Radius;
import org.wahlzeit.model.SphericCoordinateValues.Theta;

public class CartesianCoordinateTest {
	private final double decimalPlace = 1E-5;

	private CartesianCoordinate cartesianCoordinate1;
	private CartesianCoordinate cartesianCoordinate2;
	private SphericCoordinate sphericCoordinate1;
	private SphericCoordinate sphericCoordinate2;

	@Before
	public void init() {
		cartesianCoordinate1 = CartesianCoordinate.getInstance(10, 10, 10);
		cartesianCoordinate2 = CartesianCoordinate.getInstance(5, 5, 5);

		sphericCoordinate1 = SphericCoordinate.getInstance(Math.toRadians(10), Math.toRadians(10), 6360);
		sphericCoordinate2 =SphericCoordinate.getInstance(Math.toRadians(50), Math.toRadians(50), 6360);
	}

	@After
	public void finalize() {
		cartesianCoordinate1 = null;
		cartesianCoordinate2 = null;

		sphericCoordinate1 = null;
		sphericCoordinate2 = null;
	}

	@Test
	public void testDistanceIsCalculatedCorrectly() {

		assertTrue(Math.sqrt(75) == cartesianCoordinate1.getCartesianDistance(cartesianCoordinate2));
	}

	@Test
	public void testGetDistanceIsCommutative() {

		assertTrue(cartesianCoordinate2.getCartesianDistance(cartesianCoordinate1) == cartesianCoordinate1
				.getCartesianDistance(cartesianCoordinate2));
	}

	@Test
	public void testCartesianCoordinateEqualsIsTrue() {
		assertTrue(cartesianCoordinate2.equals(cartesianCoordinate2));
	}

	@Test
	public void testCartesianCoordinateEqualsIsFalse() {
		assertFalse(cartesianCoordinate2.equals(cartesianCoordinate1));
	}

	@Test
	public void testCartesianCoordinateIsEqualIsTrue() {
		assertTrue(cartesianCoordinate2.isEqual(cartesianCoordinate2));

	}

	@Test
	public void testCartesianEqualsGetsDifferentObjType() {
		assertFalse(cartesianCoordinate2.equals(123));
	}

	@Test
	public void testCartesianAndSphericCoordinateEqualsIsTrue() {
		SphericCoordinate sphericCoordinate = cartesianCoordinate2.asSphericCoordinate();
		assertTrue(cartesianCoordinate2.equals(sphericCoordinate));
	}

	@Test
	public void testGetDistanceInterchangeabilityForZeroDistance() {
		SphericCoordinate sphericCoordinate = cartesianCoordinate1.asSphericCoordinate();
		double distance = cartesianCoordinate1.getCartesianDistance(sphericCoordinate);
		assertTrue(compNumbers(distance, 0));
	}

	@Test
	public void testGetDistanceInterchangeabilityForNonZeroDistance() {
		SphericCoordinate sphericCoordinate = cartesianCoordinate2.asSphericCoordinate();
		double distanceInterchangeability = cartesianCoordinate1.getCartesianDistance(sphericCoordinate);
		double distanceCartesian = cartesianCoordinate1.getCartesianDistance(cartesianCoordinate2);

		assertTrue(compNumbers(distanceInterchangeability, distanceCartesian));
	}

	@Test
	public void testGetDistanceForSphericCoordinatesOnly() {
		double distance = sphericCoordinate1.getCartesianDistance(sphericCoordinate2);
		CartesianCoordinate cartesianCoordinate1 = sphericCoordinate1.asCastesianCoordinate();
		CartesianCoordinate cartesianCoordinate2 = sphericCoordinate2.asCastesianCoordinate();
		double cartesianDistance = cartesianCoordinate1.getCartesianDistance(cartesianCoordinate2);
		assertTrue(compNumbers(distance, cartesianDistance));
	}

	private boolean compNumbers(double x, double y) {
		return Math.abs(x - y) < decimalPlace;
	}
	@Test
	public void testAsCartesianDistance() {
		assertTrue(cartesianCoordinate1.equals(cartesianCoordinate1.asCastesianCoordinate()));
	}

	@Test
	public void testCartesianEqualsInterchangeability() {
		sphericCoordinate1 = cartesianCoordinate1.asSphericCoordinate();
		assertTrue(sphericCoordinate1.equals(cartesianCoordinate1));
	}
	@Test
	public void testCartesianCoordinateGetInstanceReturnsSameObjectWhenEqual() {
		cartesianCoordinate1 = CartesianCoordinate.getInstance(10, 10, 10);
		cartesianCoordinate2 = CartesianCoordinate.getInstance(10, 10, 10);
		
		
	assertTrue(cartesianCoordinate1.hashCode() == cartesianCoordinate2.hashCode());
	}
	@Test
	public void testCartesianCoordinateGetInstanceReturnsDifferentObjectWhenNotEqual() {
		
		cartesianCoordinate1 = CartesianCoordinate.getInstance(10, 10, 10);
		cartesianCoordinate2 = CartesianCoordinate.getInstance(5, 5, 5);
		assertTrue(cartesianCoordinate1.hashCode() != cartesianCoordinate2.hashCode());
	}
}

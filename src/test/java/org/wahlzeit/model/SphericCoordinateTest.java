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

public class SphericCoordinateTest {
	private final double decimalPlace = 1E-5;

	private CartesianCoordinate cartesianCoordinate1;
	private CartesianCoordinate cartesianCoordinate2;
	private SphericCoordinate sphericCoordinate1;
	private SphericCoordinate sphericCoordinate2;

	@Before
	public void init() {
		cartesianCoordinate1 = CartesianCoordinate.getInstance(10, 10, 10);
		cartesianCoordinate2 = CartesianCoordinate.getInstance(5, 5, 5);
		sphericCoordinate1 = SphericCoordinate.getInstance(Math.toRadians(45), Math.toRadians(45), 6360);
		sphericCoordinate2 = SphericCoordinate.getInstance(Math.toRadians(46), Math.toRadians(46), 6360);
	}

	@After
	public void finalize() {
		cartesianCoordinate1 = null;
		cartesianCoordinate2 = null;

		sphericCoordinate1 = null;
		sphericCoordinate2 = null;
	}

	@Test
	public void testConvertTwoWayConversion() {

		sphericCoordinate2 = cartesianCoordinate1.asSphericCoordinate();
		cartesianCoordinate1 = sphericCoordinate2.asCastesianCoordinate();
		assertTrue(cartesianCoordinate1.equals(cartesianCoordinate1));
	}

	@Test
	public void testGreatCentralAngle() {
		sphericCoordinate1 = SphericCoordinate.getInstance(Math.toRadians(10), Math.toRadians(10),
			6360);
		sphericCoordinate2 = SphericCoordinate.getInstance(Math.toRadians(50), Math.toRadians(50),
				6360);

		double centralAngle = sphericCoordinate1.getCentralAngle(sphericCoordinate2);
		assertTrue(compNumbers(centralAngle, 0.904669));
	}

	private boolean compNumbers(double x, double y) {
		return Math.abs(x - y) < decimalPlace;
	}

	@Test
	public void testSphericCoordinateIsEqualIsTrue() {

		sphericCoordinate2 = SphericCoordinate.getInstance(sphericCoordinate1.getPhi(), sphericCoordinate1.getTheta(),
				sphericCoordinate1.getRadius());
		assertTrue(sphericCoordinate1.equals(sphericCoordinate2));
	}

	@Test
	public void testSphericCoordinateIsEqualIsFalse() {

		assertFalse(sphericCoordinate1.equals(sphericCoordinate2));
	}

	@Test
	public void testSpericCoordinatesEqualsisInterchangeability() {
		CartesianCoordinate c = sphericCoordinate1.asCastesianCoordinate();
		assertTrue(sphericCoordinate1.equals(c));
	}

	@Test
	public void testSphericCoordinateGetInstanceReturnsSameObjectWhenEqual() {
		sphericCoordinate1 = SphericCoordinate.getInstance(Math.toRadians(50), Math.toRadians(50),
				6360);
		sphericCoordinate2 = SphericCoordinate.getInstance(Math.toRadians(50), Math.toRadians(50),
				6360);
		assertTrue(sphericCoordinate1.hashCode() == sphericCoordinate2.hashCode());
	}

	@Test
	public void testSphericCoordinateGetInstanceReturnsDifferentObjectWhenNotEqual() {
		sphericCoordinate1 = SphericCoordinate.getInstance(Math.toRadians(50), Math.toRadians(50),
				6360);
		sphericCoordinate2 = SphericCoordinate.getInstance(Math.toRadians(20), Math.toRadians(50),
				6360);
		assertTrue(sphericCoordinate1.hashCode() != sphericCoordinate2.hashCode());
	}
}

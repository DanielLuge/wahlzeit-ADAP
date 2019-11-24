package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {
	private final double decimalPlace = 1E-5;

	private CartesianCoordinate cartesianCoordinate1;
	private CartesianCoordinate cartesianCoordinate2;
	private SphericCoordinate sphericCoordinate1;
	private SphericCoordinate sphericCoordinate2;

	@Before
	public void init() {
		cartesianCoordinate1 = new CartesianCoordinate(10, 10, 10);
		cartesianCoordinate2 = new CartesianCoordinate(5, 5, 5);

		sphericCoordinate1 = new SphericCoordinate(10, 10,  6360);
		sphericCoordinate2 = new SphericCoordinate(50, 50,  6360);
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
		sphericCoordinate1 = new SphericCoordinate(10, 10, 0);
		sphericCoordinate2 = new SphericCoordinate(50, 50, 0);
		
		double centralAngle = sphericCoordinate1.getCentralAngle(sphericCoordinate2);
		assertTrue(compNumbers(centralAngle, 0.819288996637608));
	}

	private boolean compNumbers(double x, double y) {
		return Math.abs(x - y) < decimalPlace;
	}

	@Test
	public void testSphericCoordinateIsEqualIsTrue() {

		sphericCoordinate2 = new SphericCoordinate(sphericCoordinate1.getPhi(), sphericCoordinate1.getTheta(), sphericCoordinate1.getRadius());
		assertTrue(sphericCoordinate1.isEqual(sphericCoordinate2));
	}

	@Test
	public void testSphericCoordinateIsEqualIsFalse() {

		assertFalse(sphericCoordinate1.isEqual(sphericCoordinate2));
	}
	@Test
	public void testSphericIsEqualInterchangeability() {
		cartesianCoordinate1 = sphericCoordinate1.asCastesianCoordinate();
		assertTrue(cartesianCoordinate1.isEqual(sphericCoordinate1));
	}
}

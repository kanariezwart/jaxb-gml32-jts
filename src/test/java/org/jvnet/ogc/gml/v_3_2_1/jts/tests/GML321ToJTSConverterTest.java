package org.jvnet.ogc.gml.v_3_2_1.jts.tests;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.jvnet.jaxb2_commons.locator.DefaultRootObjectLocator;
import org.jvnet.ogc.gml.v_3_2_1.jts.ConversionFailedException;
import org.jvnet.ogc.gml.v_3_2_1.jts.GML321ToJTSConverterInterface;
import org.jvnet.ogc.gml.v_3_2_1.jts.GML321ToJTSGeometryConverter;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import junit.framework.Assert;
import net.opengis.gml.v_3_2_1.ObjectFactory;

public class GML321ToJTSConverterTest {
	private GML321ToJTSConverterInterface<Object, Object, Geometry> converter;
	private static JAXBContext CONTEXT;

	@BeforeClass
	public static void beforeClass() {
		try {
			CONTEXT = JAXBContext.newInstance(ObjectFactory.class.getPackage()
					.getName());
		} catch (JAXBException jaxbex) {
			throw new ExceptionInInitializerError(jaxbex);
		}
	}

	@Before
	public void setUp() throws Exception {
		converter = new GML321ToJTSGeometryConverter();
	}

	private Geometry unmarshal(String resourceName) throws JAXBException,
			ConversionFailedException {
		final URL url = getClass().getResource(resourceName);
		final Object object = CONTEXT.createUnmarshaller().unmarshal(
				new StreamSource(url.toString()));
		Object value = JAXBIntrospector.getValue(object);
		return converter.createGeometry(new DefaultRootObjectLocator(value), value);
	}
	
	@Test
	public void testPoint0() throws Exception {
		final Point point = (Point) unmarshal("Point[0].xml"); //$NON-NLS-1$
		Assert.assertFalse(point.isEmpty());
		Assert.assertTrue(point.isValid());
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testPoint1() throws Exception {
		final Point point = (Point) unmarshal("Point[1].xml"); //$NON-NLS-1$
		Assert.assertFalse(point.isEmpty());
		Assert.assertTrue(point.isValid());
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testPoint2() throws Exception {
		final Point point = (Point) unmarshal("Point[2].xml"); //$NON-NLS-1$
		Assert.assertFalse(point.isEmpty());
		Assert.assertTrue(point.isValid());
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testLineString0() throws Exception {
		final LineString lineString = (LineString) unmarshal("LineString[0].xml"); //$NON-NLS-1$
		Assert.assertFalse(lineString.isEmpty());
		Assert.assertTrue(lineString.isValid());
		Assert.assertEquals(4, lineString.getCoordinates().length);
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testLineString1() throws Exception {
		final LineString lineString = (LineString) unmarshal("LineString[1].xml"); //$NON-NLS-1$
		Assert.assertFalse(lineString.isEmpty());
		Assert.assertTrue(lineString.isValid());
		Assert.assertEquals(4, lineString.getCoordinates().length);
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testLineString2() throws Exception {
		final LineString lineString = (LineString) unmarshal("LineString[2].xml"); //$NON-NLS-1$
		Assert.assertFalse(lineString.isEmpty());
		Assert.assertTrue(lineString.isValid());
		Assert.assertEquals(4, lineString.getCoordinates().length);

	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testLineString3() throws Exception {
		final LineString lineString = (LineString) unmarshal("LineString[2].xml"); //$NON-NLS-1$
		Assert.assertFalse(lineString.isEmpty());
		Assert.assertTrue(lineString.isValid());
		Assert.assertEquals(4, lineString.getCoordinates().length);

	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testLineStrings() throws Exception {
		final LineString lineString0 = (LineString) unmarshal("LineString[0].xml"); //$NON-NLS-1$
		final LineString lineString1 = (LineString) unmarshal("LineString[1].xml"); //$NON-NLS-1$
		final LineString lineString2 = (LineString) unmarshal("LineString[2].xml"); //$NON-NLS-1$
		final LineString lineString3 = (LineString) unmarshal("LineString[2].xml"); //$NON-NLS-1$
		Assert.assertTrue(lineString0.equals(lineString1));
		Assert.assertTrue(lineString1.equals(lineString2));
		Assert.assertTrue(lineString2.equals(lineString3));
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testLinearRing0() throws Exception {
		final LinearRing linearRing = (LinearRing) unmarshal("LinearRing[0].xml"); //$NON-NLS-1$
		Assert.assertFalse(linearRing.isEmpty());
		Assert.assertTrue(linearRing.isValid());
		Assert.assertEquals(5, linearRing.getCoordinates().length);
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testLinearRing1() throws Exception {
		final LinearRing linearRing = (LinearRing) unmarshal("LinearRing[1].xml"); //$NON-NLS-1$
		Assert.assertFalse(linearRing.isEmpty());
		Assert.assertTrue(linearRing.isValid());
		Assert.assertEquals(5, linearRing.getCoordinates().length);
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testLinearRings() throws Exception {
		final LinearRing linearRing0 = (LinearRing) unmarshal("LinearRing[0].xml"); //$NON-NLS-1$
		final LinearRing linearRing1 = (LinearRing) unmarshal("LinearRing[1].xml"); //$NON-NLS-1$
		Assert.assertTrue(linearRing0.equals(linearRing1));
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testPolygon0() throws Exception {
		final Polygon polygon = (Polygon) unmarshal("Polygon[0].xml"); //$NON-NLS-1$
		Assert.assertEquals(5, polygon.getCoordinates().length);
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testPolygon1() throws Exception {
		final Polygon polygon = (Polygon) unmarshal("Polygon[1].xml"); //$NON-NLS-1$
		Assert.assertEquals(5, polygon.getCoordinates().length);
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testPolygon2() throws Exception {
		final Polygon polygon = (Polygon) unmarshal("Polygon[2].xml"); //$NON-NLS-1$
		final double area = polygon.getArea();
		Assert.assertEquals(3600, area, 1);
	}

	@Ignore(value="need to migrate test data to GML 3.2.1")
	public void testPolygons() throws Exception {
		final Polygon polygon0 = (Polygon) unmarshal("Polygon[0].xml"); //$NON-NLS-1$
		final Polygon polygon1 = (Polygon) unmarshal("Polygon[1].xml"); //$NON-NLS-1$
		Assert.assertTrue(polygon0.equals(polygon1));
	}
}

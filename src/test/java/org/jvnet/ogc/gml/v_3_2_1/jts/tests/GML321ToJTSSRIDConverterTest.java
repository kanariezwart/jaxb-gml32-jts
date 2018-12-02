package org.jvnet.ogc.gml.v_3_2_1.jts.tests;

import net.opengis.gml.v_3_2_1.PointType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.jvnet.jaxb2_commons.locator.DefaultRootObjectLocator;
import org.jvnet.ogc.gml.v_3_2_1.jts.ConversionFailedException;
import org.jvnet.ogc.gml.v_3_2_1.jts.GML321ToJTSConstants;
import org.jvnet.ogc.gml.v_3_2_1.jts.GML321ToJTSSRIDConverterInterface;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

public class GML321ToJTSSRIDConverterTest {

	String[][] examples = { {
			"http://www.opengis.net/gml/srs/epsg.xml#63266405", "63266405" } };

	String[] counterexamples = {
	};

	private GeometryFactory geometryFactory;
	private GML321ToJTSSRIDConverterInterface converter;

	@Before
	public void setUp() throws Exception {
		geometryFactory = GML321ToJTSConstants.DEFAULT_GEOMETRY_FACTORY;
		converter = GML321ToJTSConstants.DEFAULT_SRID_CONVERTER;

	}

	private void check(String srsName, int srid, Object userData)
			throws ConversionFailedException {
		final PointType source = new PointType();
		source.setSrsName(srsName);
		Geometry target = geometryFactory.createPoint(new Coordinate(0, 0));
		converter.convert(new DefaultRootObjectLocator(source), source, target);
		assertThat(target.getSRID()).isEqualTo(srid);
		assertThat(target.getUserData()).isEqualTo(userData);
	}

	@Test
	public void testConvert() throws ConversionFailedException {
		check("http://www.opengis.net/gml/srs/epsg.xml#4326", 4326,
				"http://www.opengis.net/gml/srs/epsg.xml#4326");
		check("EPSG:4326", 4326, "EPSG:4326");
		check("urn:ogc:def:crs:EPSG::4326", 4326, "urn:ogc:def:crs:EPSG::4326");
		check("urn:ogc:def:crs:EPSG:6.3:4326", 4326,
				"urn:ogc:def:crs:EPSG:6.3:4326");
		check("urn:x-ogc:def:crs:EPSG::4326", 4326,
				"urn:x-ogc:def:crs:EPSG::4326");
		check("urn:x-ogc:def:crs:EPSG:6.3:4326", 4326,
				"urn:x-ogc:def:crs:EPSG:6.3:4326");
	}

	@Test
	public void testConvertWithCorrectPoint() throws ConversionFailedException {
		Geometry point = geometryFactory.createPoint(new Coordinate(0, 0));

		final PointType source = new PointType();
		int srid = 63266405;

		source.setSrsName("http://www.opengis.net/gml/srs/epsg.xml#63266405");

		converter.convert(new DefaultRootObjectLocator(source), source, point);
		assertThat(srid).isEqualTo(point.getSRID());
	}

	@Test
	public void testConvertWithWrongFormatAndTargetUserDataNull()
			throws ConversionFailedException {
		Geometry point = geometryFactory.createPoint(new Coordinate(0, 0));

		final PointType source = new PointType();

		source.setSrsName("foo");

		converter.convert(new DefaultRootObjectLocator(source), source, point);
		assertThat("foo").isEqualTo(point.getUserData());
	}

	@Test
	public void testConvertWithWrongFormatAndTargetUserDataNotNull()
			throws ConversionFailedException {
		Geometry point = geometryFactory.createPoint(new Coordinate(0, 0));
		point.setUserData("");
		final PointType source = new PointType();

		source.setSrsName("foo");

		try {
			converter.convert(new DefaultRootObjectLocator(source), source,
					point);
			assertTrue(false);
		} catch (ConversionFailedException e) {
			assertTrue(true);
		}
	}
}

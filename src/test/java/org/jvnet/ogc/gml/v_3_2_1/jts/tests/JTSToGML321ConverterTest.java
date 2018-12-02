package org.jvnet.ogc.gml.v_3_2_1.jts.tests;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import static org.assertj.core.api.Assertions.assertThat;
import net.opengis.gml.v_3_2_1.AbstractGeometryType;

import org.junit.Before;
import org.junit.Test;
import org.jvnet.ogc.gml.v_3_2_1.ObjectFactory;
import org.jvnet.ogc.gml.v_3_2_1.jts.JTSToGML321GeometryConverter;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class JTSToGML321ConverterTest {

	private final GeometryFactory geometryFactory = new GeometryFactory();

	private JTSToGML321GeometryConverter converter;

	@Before
	public void setUp() throws Exception {
		converter = new JTSToGML321GeometryConverter();
	}

	private void marshal(Object object) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(object, new StreamResult(System.out));
	}

	@Test
	public void testPoint0() throws Exception {
		final Point point = geometryFactory.createPoint(new Coordinate(10, 20));
		point.setSRID(4326);
		final JAXBElement<? extends AbstractGeometryType> element = converter
				.createElement(point);
		assertThat("urn:ogc:def:crs:EPSG::4326").isEqualTo(element.getValue().getSrsName());
		// marshal(element);
	}

	@Test
	public void testPoint1() throws Exception {

		final Point point = geometryFactory.createPoint(new Coordinate(10, 20));
		point.setUserData("urn:ogc:def:crs:EPSG::4326"); //$NON-NLS-1$
		final JAXBElement<? extends AbstractGeometryType> element = converter
				.createElement(point);
		assertThat("urn:ogc:def:crs:EPSG::4326").isEqualTo(element.getValue().getSrsName());
	}

	@Test
	public void testPoint2() throws Exception {
		final Point point = geometryFactory.createPoint(new Coordinate(10, 20));
		point.setUserData("urn:ogc:def:crs:OGC:1.3:CRS1"); //$NON-NLS-1$
		final JAXBElement<? extends AbstractGeometryType> element = converter
				.createElement(point);
		assertThat("urn:ogc:def:crs:OGC:1.3:CRS1").isEqualTo(element.getValue().getSrsName());
	}

	@Test
	public void testPoint3() throws Exception {
		final Point point = geometryFactory.createPoint(new Coordinate(10, 20));
		final JAXBElement<? extends AbstractGeometryType> element = converter
				.createElement(point);
		assertThat(element.getValue().getSrsName()).isNull();
	}
}

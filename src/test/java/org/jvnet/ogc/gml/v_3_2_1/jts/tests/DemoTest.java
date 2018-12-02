package org.jvnet.ogc.gml.v_3_2_1.jts.tests;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

public class DemoTest {

	@Test
	public void demonstrateContext() throws JAXBException, IOException
	{
		JAXBContext context = JAXBContext.newInstance("org.jvnet.ogc.gml.v_3_2_1.jts");

		// Unmarshal
		Point point = (Point) context.createUnmarshaller().unmarshal(getClass().getResource("Point[0].xml"));
		
		Polygon polygon = (Polygon) context.createUnmarshaller().unmarshal(getClass().getResource("Polygon[0].xml"));
		
		// Marshal
		context.createMarshaller().marshal(point, System.out);
		System.out.println();
		context.createMarshaller().marshal(polygon, System.out);
	}
}

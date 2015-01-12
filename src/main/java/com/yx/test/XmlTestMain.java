package com.yx.test;

import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.yx.xmlbeans.ObjectFactory;
import com.yx.xmlbeans.Xml;

/**
 * @author James
 * JAXB 
 * xml to java
 * java to xml
 * 
 * xjc -d . xx.xsd
 */
public class XmlTestMain {

	public static void main(String[] args) throws JAXBException,
			InstantiationException, IllegalAccessException {
		XmlTestMain t = new XmlTestMain();
		Xml xml = new ObjectFactory().createXml();
		xml.setContent("hell0");
		xml.setCreateTime(new Date().getTime());
		xml.setFromUserName("from");
		xml.setMsgId(new BigDecimal(12));
		xml.setMsgType("text");
		xml.setToUserName("to ");
		// marshaller(Xml.class, xml);
		t.unmarshaller(Xml.class);
	}

	private void unmarshaller(Class<? extends Object> clazz)
			throws JAXBException, InstantiationException,
			IllegalAccessException {
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/yx/test/xml/msg.xml");
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object obj = clazz.newInstance();
		obj = unmarshaller.unmarshal(in);
		System.out.println(obj);

	}

	public <T> String marshaller(Class<? extends Object> clazz, Object obj)
			throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller marshaller = context.createMarshaller();
		StringWriter sw = new StringWriter();
		marshaller.marshal(obj, sw);
		System.out.println(sw.toString());
		return sw.toString();
	}

}

package com.gonghan.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class DBLP_parser {

	private final static String filename = "../SOC/dblp.xml";

	public DBLP_parser() {
		long start = System.currentTimeMillis();
		init();
		long time = System.currentTimeMillis() - start;
		System.out.println(time / 1000 + " seconds.");
	}

	private void init() {
		System.setProperty("entityExpansionLimit", "11111111");
		try {
			SAXHandler handler = new SAXHandler();
			FileReader reader = new FileReader(filename);
			XMLReader xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);
			xr.parse(new InputSource(reader));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class SAXHandler extends DefaultHandler {

	List<Publication> pubList = new ArrayList<Publication>();
	String content = null;
	Publication publication = null;
	final int LIST_SIZE = 5000;

	public void startElement(String namespaceURI, String localName,
			String rawName, Attributes atts) throws SAXException {
		if (rawName.equals("www")) {
			publication = new Publication();
			publication.setMdate(atts.getValue(0));
			publication.setKey(atts.getValue(1));
		}

	}

	public void endElement(String namespaceURI, String localName, String rawName)
			throws SAXException {
		if (rawName.equals("authors")) {
			publication.addAuthor(content);
		} else if (rawName.equals("title")) {
			publication.setTitle(content);
		} else if (rawName.equals("school")) {
			publication.setSchool(content);
		} else if (rawName.equals("url")) {
			publication.setUrl(content);
		} else if (rawName.equals("pages")) {
			publication.setPages(content);
		} else if (rawName.equals("booktitle")) {
			publication.setBooktitle(content);
		} else if (rawName.equals("www")) {
			pubList.add(publication);
			if (pubList.size() == LIST_SIZE) {
				DB_helper.addPublicationsIntoDatabase(pubList);
				pubList.clear();
			}
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}

}

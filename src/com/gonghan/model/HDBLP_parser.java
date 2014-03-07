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

public class HDBLP_parser {
	private final static String filename = "F:/SOC/hdblp.xml";

	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		new HDBLP_parser();
		long time = System.currentTimeMillis() - start;
		System.out.println(time / 1000 + " seconds.");
	}

	public HDBLP_parser() {
		System.setProperty("entityExpansionLimit", "11111111");
		try {
			BHTSAXHandler handler = new BHTSAXHandler();
			FileReader reader = new FileReader(filename);
			XMLReader xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);
			xr.parse(new InputSource(reader));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class BHTSAXHandler extends DefaultHandler {

	String content = null;
	final int LIST_SIZE = 50;
	List<Article> list = new ArrayList<Article>();
	Article article;

	public void startElement(String namespaceURI, String localName,
			String rawName, Attributes atts) throws SAXException {
		if (rawName.equals("article") || rawName.equals("inproceedings")) {
			article = new Article();
			article.setMkey(atts.getValue(0));
			article.setDate(atts.getValue(1));
		}
	}

	public void endElement(String namespaceURI, String localName, String rawName)
			throws SAXException {
		if (rawName.equals("author")) {
			article.addAuthor(content);
		} else if (rawName.equals("title")) {
			article.setTitle(content);
		} else if (rawName.equals("pages")) {
			article.setPages(content);
		} else if (rawName.equals("article") || rawName.equals("inproceedings")) {
			article.update();
			list.add(article);
			if (list.size() == LIST_SIZE) {
				DB_helper.addArticlesIntoDatabase(list);
				list.clear();
			}
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}

}

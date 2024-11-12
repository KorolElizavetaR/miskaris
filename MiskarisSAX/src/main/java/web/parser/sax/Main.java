package web.parser.sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {
	public static void main(String[] args) throws Exception {

		try {
			File xmlFile = new File("src/main/resources/books.xml");
			File xsdFile = new File("src/main/resources/books.xsd");
			File xslfile = new File("src/main/resources/books.xsl");
			File htmlfile = new File("src/main/resources/books.html");
			String xmlInputPath = "src/main/resources/books.xml";
			String xslt = "src/main/resources/books.xslt";
			String outputXmlPath = "src/main/resources/transformed_books.xml"; // выходной XML после XSLT
			String xslfoPath = "src/main/resources/books.xslfo"; // XSL-FO для создания PDF
			String pdfOutputPath = "src/main/resources/books.pdf";
			// validateXMLSchema(xsdFile, xmlFile);
			// DOMparser(xmlFile).stream().forEach(System.out::println);
			// XPath(xmlFile).stream().forEach(System.out::println);
			// XSLT(xmlFile, xslfile, htmlfile);
			transformXml(xmlInputPath, xslt, outputXmlPath);
			transformToPdf(outputXmlPath, xslfoPath, pdfOutputPath);
		} catch (Exception ex) {
			System.err.println(ex.getLocalizedMessage());
		}
	}

	public static void validateXMLSchema(File xsdFile, File xmlFile) throws SAXException, IOException {
		Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xsdFile);
		Validator validator = schema.newValidator();
		validator.validate(new StreamSource(xmlFile));
	}

	public static List<Book> DOMparser(File xmlfile) throws SAXException, IOException, ParserConfigurationException {
		List<Book> books = new ArrayList<>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlfile);
		doc.getDocumentElement().normalize();

		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("book");

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				Book book = new Book().setId(element.getAttribute("id"))
						.setTitle(element.getElementsByTagName("title").item(0).getTextContent())
						.setAuthor(element.getElementsByTagName("author").item(0).getTextContent())
						.setGenre(element.getElementsByTagName("genre").item(0).getTextContent())
						.setPrice(Double.valueOf(element.getElementsByTagName("price").item(0).getTextContent()))
						.setCurrency(element.getElementsByTagName("price").item(0).getAttributes()
								.getNamedItem("currency").getNodeValue());
				books.add(book);
			}
		}
		return books;
	}

	public static List<Book> XPath(File xmlfile)
			throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		List<Book> books = new ArrayList<>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlfile);
		doc.getDocumentElement().normalize();

		javax.xml.xpath.XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "//book";
		XPathExpression xPathExpression = xPath.compile(expression);
		NodeList bookList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);

		for (int i = 0; i < bookList.getLength(); i++) {
			Node node = bookList.item(i);
			Element element = (Element) node;
			Book book = new Book().setId(element.getAttribute("id"))
					.setTitle(element.getElementsByTagName("title").item(0).getTextContent())
					.setAuthor(element.getElementsByTagName("author").item(0).getTextContent())
					.setGenre(element.getElementsByTagName("genre").item(0).getTextContent())
					.setPrice(Double.valueOf(element.getElementsByTagName("price").item(0).getTextContent()))
					.setCurrency(element.getElementsByTagName("price").item(0).getAttributes().getNamedItem("currency")
							.getNodeValue());
			books.add(book);
		}
		return books;
	}

	public static void XSLT(File xmlfile, File xslfile, File htmlfile) throws TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource(xslfile));
		transformer.transform(new StreamSource(xmlfile), new StreamResult(htmlfile));
		System.out.println("Transformation completed.");
	}

	public static void transformXml(String xmlInputPath, String xsltPath, String outputXmlPath) throws Exception {
		TransformerFactory factory = TransformerFactory.newInstance();
		Source xslt = new StreamSource(new File(xsltPath));
		Source xml = new StreamSource(new File(xmlInputPath));
		Result result = new StreamResult(new File(outputXmlPath));

		Transformer transformer = factory.newTransformer(xslt);
		transformer.transform(xml, result);
	}

	public static void transformToPdf(String xmlInputPath, String xslfoPath, String pdfOutputPath) throws Exception {
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		File pdfFile = new File(pdfOutputPath);

		try (FileOutputStream out = new FileOutputStream(pdfFile)) {
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(new File(xslfoPath)));
			transformer.transform(new StreamSource(new File(xmlInputPath)), new SAXResult(fop.getDefaultHandler()));
		}
	}
}

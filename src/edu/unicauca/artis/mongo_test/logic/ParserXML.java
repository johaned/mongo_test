package edu.unicauca.artis.mongo_test.logic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;
import de.odysseus.staxon.xml.util.PrettyXMLStreamWriter;
import edu.unicauca.artis.mongo_test.model.mongoskeleton.ManRes;
import edu.unicauca.artis.mongo_test.model.mongoskeleton.Mcratr;

public class ParserXML {

	public ParserXML() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String to_convert_simple_xml(BasicDBObject dbo)
			throws XMLStreamException, FactoryConfigurationError,
			TransformerConfigurationException, TransformerException,
			TransformerFactoryConfigurationError, IOException {
		InputStream input = new ByteArrayInputStream(dbo.toString().getBytes(
				"UTF-8"));
		// input = ParserXML.class.getResourceAsStream("/input_.json");
		OutputStream output = System.out;
		// JsonXMLConfig config = new
		// JsonXMLConfigBuilder().multiplePI(false).prettyPrint(true).repairingNamespaces(true).build();
		JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false)
				.build();
		try {
			/*
			 * Create reader (JSON).
			 */
			XMLEventReader reader = new JsonXMLInputFactory(config)
					.createXMLEventReader(input);
			/*
			 * Create writer (XML).
			 */
			XMLEventWriter writer = XMLOutputFactory.newInstance()
					.createXMLEventWriter(output);
			writer = new PrettyXMLEventWriter(writer); // format output
			/*
			 * Copy events from reader to writer.
			 */
			writer.add(reader);
			/*
			 * Close reader/writer.
			 */
			reader.close();
			writer.close();
		} finally {
			/*
			 * As per StAX specification, XMLEventReader/Writer.close() doesn't
			 * close the underlying stream.
			 */
			output.close();
			input.close();
		}
		return null;
	}

	public static ManRes to_convert_object(
			BasicDBObject dbo) {
		ManRes mr = new Gson().fromJson(dbo.toString(), ManRes.class);
		System.out.println("ID MR: "+mr.getName());
		return null;

	}
}

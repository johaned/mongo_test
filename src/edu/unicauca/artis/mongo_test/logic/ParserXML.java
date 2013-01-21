package edu.unicauca.artis.mongo_test.logic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;
import de.odysseus.staxon.xml.util.PrettyXMLStreamWriter;
import edu.unicauca.artis.mongo_test.miscellaneus.Log;
import edu.unicauca.artis.mongo_test.model.mbeanskeleton.MyMBeanAttributeInfo;
import edu.unicauca.artis.mongo_test.model.mbeanskeleton.MyMBeanInfo;
import edu.unicauca.artis.mongo_test.model.mongoskeleton.Attr;
import edu.unicauca.artis.mongo_test.model.mongoskeleton.ManRes;
import edu.unicauca.artis.mongo_test.model.mongoskeleton.Mcratr;

public class ParserXML {

	public ParserXML() {
		super();
	}

	public static String to_convert_simple_xml(BasicDBObject dbo)
			throws XMLStreamException, FactoryConfigurationError,
			TransformerConfigurationException, TransformerException,
			TransformerFactoryConfigurationError, IOException {
		InputStream input = new ByteArrayInputStream(dbo.toString().getBytes(
				"UTF-8"));
		OutputStream output = System.out;
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
		return mr;

	}
	
	private static List get_myMBeanInfos_by_bdbo(ManRes mr){
		List<Mcratr> mcratrs = mr.getMcratr();
		List<MyMBeanInfo> mmbis = new ArrayList<MyMBeanInfo>();
		for (Mcratr mcratr : mcratrs) {
			mmbis.add(mcratr_to_myMBeanAttributeInfo(mcratr));
		}
		return mmbis;
		
	}
	
	private static MyMBeanInfo mcratr_to_myMBeanAttributeInfo(Mcratr ma){
		MyMBeanInfo mmbi = new MyMBeanInfo();
		mmbi.setClassName("mbean.MyDynamicMBean");
		mmbi.setDescription(ma.getDescr());
		
		MyMBeanAttributeInfo[] mmbais = new MyMBeanAttributeInfo[ma.getAttr().size()];
	
		int i = 0;
		for (Attr attr : ma.getAttr()) {
			//mmbais[i]=new MyMBeanAttributeInfo(attr.getName(), attr.getDataType(), "whatever", true, false, false);
			mmbais[i]=new MyMBeanAttributeInfo(attr.getId(), attr.getName(),attr.getDataType(), attr.getRefProt(),"whatever", true, false, false);
			i++;
		}
		mmbi.setAttributes(mmbais);
		return mmbi;
	} 

	public static void document_to_single_xml_myMBeanInfo(
			BasicDBObject bdbo) {
		List<MyMBeanInfo> mmbis = get_myMBeanInfos_by_bdbo(to_convert_object(bdbo));
		int i=0;
		for (MyMBeanInfo mmbi : mmbis) {
			try {
		          Serializer serializer = new Persister();
		          File result = new File("example_"+i+".xml");
		          serializer.write(mmbi, result);
		     } catch (Exception e) {
		          e.printStackTrace();
		     }
			i++;			
		}		
	}
}

package edu.unicauca.artis.mongo_test.main;

import java.awt.Color;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.Arrays;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.DB;

import edu.unicauca.artis.mongo_test.miscellaneus.Log;
import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Main {
	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient();
		DB db = mongoClient.getDB("test");
		Log.print(db);

		// BD instance Collections
		Set<String> colls = db.getCollectionNames();

		for (String s : colls) {
			Log.print(s);
		}

		DBCollection coll = db.getCollection("test");
		Log.print(coll);
		
		TextAttributes attrs = new TextAttributes(Color.GREEN, Color.BLACK);
		s_console.setTextAttributes(attrs);
		System.out.println("Hello Hello!");

	}

	private static final Console s_console;
	static {
		s_console = Enigma.getConsole("Hellow World!");
	}
}

package edu.unicauca.artis.mongo_test.model;

import java.io.WriteAbortedException;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MongoDBConnection {
	private static MongoDBConnection instance;
	private MongoClient mc;
	private DB db;
	private DBCollection coll;
	
	private MongoDBConnection() throws UnknownHostException{
		mc = new MongoClient();
		db = mc.getDB("test");
		coll = db.getCollection("test");
	}
	
	public static MongoDBConnection getInstance() throws UnknownHostException{
		if(instance==null){
			instance = new MongoDBConnection();
		}
		return instance;
	}
	
	public WriteResult insert_doc(DBObject dbo){
		return coll.insert(dbo);
	}
	
	public DBCursor get_cursor_by_coll(){
		return coll.find();
	}
	
	public void see_all_doc_by_coll(){
		DBCursor c = get_cursor_by_coll();
		try {
			while (c.hasNext()) {
				System.out.println(c.next());
			}
		} finally {
			c.close();
		}
	}
	public void remove_all_doc_by_coll(){
		DBCursor c = get_cursor_by_coll();
		try {
			while (c.hasNext()) {
				coll.remove(c.next());
			}
		} finally {
			c.close();
		}
	}

}

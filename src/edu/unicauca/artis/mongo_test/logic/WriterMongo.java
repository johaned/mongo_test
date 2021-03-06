package edu.unicauca.artis.mongo_test.logic;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import edu.unicauca.artis.mongo_test.model.MongObject;

public class WriterMongo {

	public static BasicDBObject create_mr(int index) {
		BasicDBObject bdbo = new BasicDBObject();
		bdbo.put("id", index);
		bdbo.put("lyr", "servicios");
		bdbo.put("name", "Gestion de contenidos multimedia");
		bdbo.put("refProt", "1.2.5.4.1.8."+index);
		bdbo.put("impact", "alto");
		bdbo.put("descr", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.");
		bdbo.put("regDate",System.currentTimeMillis());
		bdbo.put("alertable", "false");
		bdbo.put("mngable", "false");
		bdbo.put("mcratr", create_group_macattr(index, 2));
		bdbo.put("mother", 1);
		//bdbo.put("topCon", create_group_topCon(2));
		return bdbo;	
	}
	// - Topologic connections
	public static BasicDBList create_group_topCon(int num){
		BasicDBList bdbl = new BasicDBList();	
		for(int i=0; i<num; i++){
			bdbl.add(create_topCon(1));
		}
		return bdbl;
	}
	
	public static BasicDBObject create_topCon(int a){
		BasicDBObject bdbo = new BasicDBObject();
		bdbo.put("idManResA", a);
		bdbo.put("name", "bd");
		bdbo.put("descr", "Y, viéndole don Quijote de aquella manera, con muestras de tanta tristeza, le dijo: Sábete, Sancho, que no es un hombre más que otro si no hace más que otro. Todas estas borrascas que nos suceden son señales de que presto ha de serenar el tiempo y han de sucedernos bien las cosas");
		bdbo.put("regDate",System.currentTimeMillis());
		bdbo.put("type", "ethernet");
		return bdbo;
	}
	
	// - group macattr
	
	public static BasicDBList create_group_macattr(int index_dep,int num){
		BasicDBList bdbl = new BasicDBList();
		for(int i=0; i<num; i++){
			bdbl.add(create_macattr(index_dep, i));
		}
		return bdbl;
	}
	
	public static BasicDBObject create_macattr(int index_dep,int index){
		BasicDBObject bdbo = new BasicDBObject();
		bdbo.put("id", index);
		bdbo.put("name", "adaptación");
		bdbo.put("type", "cmp");
		bdbo.put("descr", "Y, viéndole don Quijote de aquella manera, con muestras de tanta tristeza, le dijo: Sábete, Sancho, que no es un hombre más que otro si no hace más que otro. Todas estas borrascas que nos suceden son señales de que presto ha de serenar el tiempo y han de sucedernos bien las cosas");
		bdbo.put("refProt", "1.2.5.4.1.8."+index_dep+"."+index);
		bdbo.put("attr", create_group_attr(index_dep, index, 2));
		bdbo.put("alrSpc", "Muy lejos, más allá de las montañas de palabras, alejados de los países de las vocales y las consonantes, viven los textos simulados");
		return bdbo;
	}
	
	// - group attr
	public static BasicDBList create_group_attr(int index_dep,int index_dep_,int num){
		BasicDBList bdbl = new BasicDBList();
		for(int i=0; i<num; i++){
			bdbl.add(create_attr(index_dep, index_dep_, i));
		}
		return bdbl;
	}
	
	public static BasicDBObject create_attr(int index_dep,int index_dep_,int index){
		BasicDBObject bdbo = new BasicDBObject();
		bdbo.put("id", index);
		bdbo.put("name", "adaptación");
		bdbo.put("datatype", "integer");
		bdbo.put("refProt", "1.2.5.4.1.8."+index_dep+"."+index_dep_+"."+index);
		bdbo.put("value", index*Math.random());
		bdbo.put("tol", 0.05);
		bdbo.put("qox", index*Math.random());		
		return bdbo;
	}
	
}

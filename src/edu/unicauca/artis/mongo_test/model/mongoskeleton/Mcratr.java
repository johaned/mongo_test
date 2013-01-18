package edu.unicauca.artis.mongo_test.model.mongoskeleton;

import java.util.List;
import java.util.Vector;

public class Mcratr {
	private long id;
	private String name;
	private String type;
	private String descr;
	private String refProt;
	private List<Attr> attr;
	private String alrSpc;

	public Mcratr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getRefProt() {
		return refProt;
	}

	public void setRefProt(String refProt) {
		this.refProt = refProt;
	}

	public List<Attr> getAttr() {
		return attr;
	}

	public void setAttr(Vector<Attr> attr) {
		this.attr = attr;
	}

	public String getAlrSpc() {
		return alrSpc;
	}

	public void setAlrSpc(String alrSpc) {
		this.alrSpc = alrSpc;
	}

}

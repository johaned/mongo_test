package edu.unicauca.artis.mongo_test.model.mongoskeleton;

public class Attr {
	private long id;
	private String name;
	private String dataType;
	private String refProt;
	private double value;
	private double tol;
	private double qox;

	public Attr() {
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

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getRefProt() {
		return refProt;
	}

	public void setRefProt(String refProt) {
		this.refProt = refProt;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getTol() {
		return tol;
	}

	public void setTol(double tol) {
		this.tol = tol;
	}

	public double getQox() {
		return qox;
	}

	public void setQox(double qox) {
		this.qox = qox;
	}

}

package edu.unicauca.artis.mongo_test.model.mongoskeleton;

import java.util.List;
import java.util.Vector;

public class ManRes {
	private _Id _id;
	private Long id;
	private String lyr;
	private String name;
	private String refProt;
	private String impact;
	private String descr;
	private String regDate;
	private Boolean alertable;
	private Boolean mngable;
	private List<Mcratr> mcratr;
	private Long mother;

	public ManRes() {
		super();
		// TODO Auto-generated constructor stub
	}



	public _Id get_id() {
		return _id;
	}



	public void set_id(_Id _id) {
		this._id = _id;
	}



	public void setMcratr(List<Mcratr> mcratr) {
		this.mcratr = mcratr;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLyr() {
		return lyr;
	}

	public void setLyr(String lyr) {
		this.lyr = lyr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRefProt() {
		return refProt;
	}

	public void setRefProt(String refProt) {
		this.refProt = refProt;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Boolean getAlertable() {
		return alertable;
	}

	public void setAlertable(Boolean alertable) {
		this.alertable = alertable;
	}

	public Boolean getMngable() {
		return mngable;
	}

	public void setMngable(Boolean mngable) {
		this.mngable = mngable;
	}

	public List<Mcratr> getMcratr() {
		return mcratr;
	}

	public void setMcratr(Vector<Mcratr> mcratr) {
		this.mcratr = mcratr;
	}

	public Long getMother() {
		return mother;
	}

	public void setMother(Long mother) {
		this.mother = mother;
	}

}

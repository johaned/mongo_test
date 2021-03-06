package edu.unicauca.artis.mongo_test.model.mbeanskeleton;

import org.simpleframework.xml.Attribute;

public class MyMBeanAttributeInfo {

	@Attribute
	private long id;
	@Attribute
	private String name;
	@Attribute
	private String type;
	@Attribute
	private String refProt;
	@Attribute
	private String description;
	@Attribute
	private boolean isReadable;
	@Attribute
	private boolean isWritable;
	@Attribute
	private boolean isIs;

	public MyMBeanAttributeInfo() {
	}

	public MyMBeanAttributeInfo(String name, String type, String description,
			boolean isReadable, boolean isWritable, boolean isIs) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.isReadable = isReadable;
		this.isWritable = isWritable;
		this.isIs = isIs;
	}

	public MyMBeanAttributeInfo(long id, String name, String type,
			String refProt, String description, boolean isReadable,
			boolean isWritable, boolean isIs) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.refProt = refProt;
		this.description = description;
		this.isReadable = isReadable;
		this.isWritable = isWritable;
		this.isIs = isIs;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRefProt() {
		return refProt;
	}

	public void setRefProt(String refProt) {
		this.refProt = refProt;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isReadable() {
		return isReadable;
	}

	public void setReadable(boolean isReadable) {
		this.isReadable = isReadable;
	}

	public boolean isWritable() {
		return isWritable;
	}

	public void setWritable(boolean isWritable) {
		this.isWritable = isWritable;
	}

	public boolean isIs() {
		return isIs;
	}

	public void setIs(boolean isIs) {
		this.isIs = isIs;
	}

}

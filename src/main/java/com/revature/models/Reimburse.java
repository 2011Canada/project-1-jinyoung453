package com.revature.models;

public class Reimburse {
	
	private int id;
	private double amount;
	private String submit; //date
	private String approve; //date
	private String desc;
	private String receipt;
	private int author;
	private int resolver;
	private int status;
	private int type;
	private String authorName;
	private String resolverName;
	private String statusName;
	
	public Reimburse(int id, int resolver) {
		this.id = id;
		this.resolver = resolver;
	}
	public Reimburse(int id, double amount, String submit, String approve, String desc, String receipt, int author,
			int resolver, int status, int type, String authorName, String resolverName, String statusName,
			String typeName) {
		super();
		this.id = id;
		this.amount = amount;
		this.submit = submit;
		this.approve = approve;
		this.desc = desc;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
		this.authorName = authorName;
		this.resolverName = resolverName;
		this.statusName = statusName;
		this.typeName = typeName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getResolverName() {
		return resolverName;
	}

	public void setResolverName(String resolverName) {
		this.resolverName = resolverName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	private String typeName;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}


	public Reimburse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Reimburse(int id, double amount, String submit, String approve, String desc, String receipt, int author,
			int resolver, int status, int type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submit = submit;
		this.approve = approve;
		this.desc = desc;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}


	@Override
	public String toString() {
		return "Reimburse [id=" + id + ", amount=" + amount + ", submit=" + submit + ", approve=" + approve + ", desc="
				+ desc + ", receipt=" + receipt + ", author=" + author + ", resolver=" + resolver + ", status=" + status
				+ ", type=" + type + ", authorName=" + authorName + ", resolverName=" + resolverName + ", statusName="
				+ statusName + ", typeName=" + typeName + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((approve == null) ? 0 : approve.hashCode());
		result = prime * result + author;
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + id;
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + resolver;
		result = prime * result + ((resolverName == null) ? 0 : resolverName.hashCode());
		result = prime * result + status;
		result = prime * result + ((statusName == null) ? 0 : statusName.hashCode());
		result = prime * result + ((submit == null) ? 0 : submit.hashCode());
		result = prime * result + type;
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimburse other = (Reimburse) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (approve == null) {
			if (other.approve != null)
				return false;
		} else if (!approve.equals(other.approve))
			return false;
		if (author != other.author)
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (id != other.id)
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (resolver != other.resolver)
			return false;
		if (resolverName == null) {
			if (other.resolverName != null)
				return false;
		} else if (!resolverName.equals(other.resolverName))
			return false;
		if (status != other.status)
			return false;
		if (statusName == null) {
			if (other.statusName != null)
				return false;
		} else if (!statusName.equals(other.statusName))
			return false;
		if (submit == null) {
			if (other.submit != null)
				return false;
		} else if (!submit.equals(other.submit))
			return false;
		if (type != other.type)
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}
	
	
	
	
}

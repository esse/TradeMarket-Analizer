package index;

import java.util.Date;

public class Nikkei extends Index {
	private Float value;

	private Date date;
	
	private Long id;

	public void setValue(Float value) {
		this.value = value;
	}

	public Float getValue() {
		return value;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}

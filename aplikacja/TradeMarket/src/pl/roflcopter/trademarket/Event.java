package pl.roflcopter.trademarket;

import java.util.Date;

public class Event {
private String description;

private Date date;

private Long id;

public void setDescription(String description) {
	this.description = description;
}

public String getDescription() {
	return description;
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

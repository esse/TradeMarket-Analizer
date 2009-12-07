package pl.roflcopter.trademarket.index;

import java.util.Date;

public abstract class Index {
	
	public abstract void setValue(Float value);

	public abstract Float getValue();

	public abstract void setDate(Date date);

	public abstract Date getDate();

	public abstract void setId(Long id);

	public abstract Long getId();
}

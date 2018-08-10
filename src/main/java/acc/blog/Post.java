package acc.blog;

import java.sql.Date;
import java.time.LocalDate;

public class Post {
	private int id;
	private String title;
	private String body;
	private Date date;	
	
	public Post(int id, String title, String body, Date date) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}

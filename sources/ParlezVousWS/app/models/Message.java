package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Message extends Model {

	public String content;
	@ManyToOne
	public User author;
	
	public Message(String content, User author) {
		super();
		this.content = content;
		this.author = author;
	}
	
	@Override
	public String toString() {
		return author + ":" + content;
	}
	
}

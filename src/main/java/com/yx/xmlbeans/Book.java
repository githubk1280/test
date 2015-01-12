package com.yx.xmlbeans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Book")
public class Book {

	private String bookId;
	private String bookName;

	public Book() {
	}

	@XmlAttribute(name = "bookId")
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@XmlAttribute(name = "bookName")
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Book(String bookId, String bookName) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
	}
}

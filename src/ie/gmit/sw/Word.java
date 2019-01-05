package ie.gmit.sw;

public class Word {
	private String book;

	private String shingle;

	public Word(String book, String shingle) {
		this.book = book;
		this.shingle = shingle;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getShingle() {
		return shingle;
	}

	public void setShingle(String shingle) {
		this.shingle = shingle;
	}

}

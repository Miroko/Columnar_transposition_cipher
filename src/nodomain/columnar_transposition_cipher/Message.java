package nodomain.columnar_transposition_cipher;

public class Message {
	
	private String keyword;
	private String text;
	
	public Message(String text, String keyword) {
		this.keyword = keyword;
		this.text = text;
	}
	public String getKeyword(){
		return keyword;
	}
	public String getText(){
		return text;
	}
}

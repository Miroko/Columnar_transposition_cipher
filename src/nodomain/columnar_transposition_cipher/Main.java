package nodomain.columnar_transposition_cipher;

public class Main {

	public static void main(String[] args) {
		
		ColumnarTranspositionCipher transpositionCipher = new ColumnarTranspositionCipher();		

		String keyword = "debug";
		String text = "this is a test";			
		Message encryptableMessage = new Message(text, keyword);
		System.out.println(encryptableMessage.getKeyword());
		System.out.println(encryptableMessage.getText());
		
		Message encryptedMessage = transpositionCipher.encryptMessage(encryptableMessage);	
		System.out.println(encryptedMessage.getText());
		
		Message decryptableMessage = encryptedMessage;
		System.out.println(decryptableMessage.getText());
		
		Message decryptedMessage = transpositionCipher.decryptMessage(decryptableMessage);
		System.out.println(decryptedMessage.getText());		
	
	
	}

}

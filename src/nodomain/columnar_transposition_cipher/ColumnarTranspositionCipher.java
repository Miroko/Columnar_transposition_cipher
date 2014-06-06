package nodomain.columnar_transposition_cipher;

public class ColumnarTranspositionCipher {
		
	/**
	 * Builds encrypted message
	 * @param Message
	 * @return Encrypted message with correct keyword to decrypt
	 */
	public Message encryptMessage(Message message) {
		StringBuilder encryptedText = new StringBuilder();
						
		Message messageNoWhitespaces = removeWhitespaces(message);
		
		int[] keywordPermutation = getKeywordPermutationByAlphabeticalOrder(message.getKeyword());	
		Debug.printIntArray(keywordPermutation);
		
		char[][] grid = getTranspositionGridForMessageEncryption(messageNoWhitespaces);
		fillTranspositionGrid(grid, messageNoWhitespaces);
		Debug.print2DCharArray(grid);
		
		for(int permutation = 0; permutation < keywordPermutation.length; permutation++){			
			for(int x = 0; x < keywordPermutation.length; x++){
				if(keywordPermutation[x] == permutation){
					for(int y = 0; y < grid[x].length; y++){
						if(grid[x][y] != 0){
							encryptedText.append(grid[x][y]);
						}
					}
				}
			}
			encryptedText.append(" ");
			
			//  0  2  1  | permutation = x
			// [a][b][c] |
			// [a][b][c] v
			// [a][0][0]
			
			// 0 = aa
			// 1 = cc
			// 2 = bb
			
			// x	     0      1      2
			// string = "aaa+" "+cc+" "+bb"		
		}
		
		return new Message(encryptedText.toString(), message.getKeyword());
	}
	public Message removeWhitespaces(Message message){	
		return new Message(message.getText().replaceAll("\\s", ""), message.getKeyword());		
	}
	/**
	 * Builds decrypted message
	 * @param message
	 * @return Decrypted message with keyword that was used to decrypt
	 */
	public Message decryptMessage(Message message) {
		StringBuilder decryptedText = new StringBuilder();
		
		Message messageNoWhitespaces = removeWhitespaces(message);
		
		int[] keywordPermutation = getKeywordPermutationByAlphabeticalOrder(message.getKeyword());	
		Debug.printIntArray(keywordPermutation);
		
		char[][] grid = getTranspositionGridForMessageEncryption(messageNoWhitespaces);	
		
		String[] textBlocks = message.getText().split(" ");

		for(int permutation = 0; permutation < keywordPermutation.length; permutation++){		
			for(int x = 0; x < keywordPermutation.length; x++){
				if(keywordPermutation[x] == permutation){
					for(int y = 0; y < textBlocks[permutation].length(); y++){
						grid[x][y] = textBlocks[permutation].charAt(y);						
					}	
				}
			}		
		}
		
		Debug.print2DCharArray(grid);
		for(int y = 0; y < grid[0].length; y++){
			for(int x = 0; x < grid.length; x++){
				decryptedText.append(grid[x][y]);
			}
		}
		
		return new Message(decryptedText.toString(), message.getKeyword());
	}
	/**
	 * Builds array containing keyword permutation by alphabetical order
	 * @param Keyword
	 * @return int[] permutation from keyword
	 */
	private int[] getKeywordPermutationByAlphabeticalOrder(String keyword){
		int[] keywordPermutation = new int[keyword.length()];
		
		int permutationNumber = 0;			
		for(char alphabet = 'a'; alphabet < 'z'; alphabet++){				
			if(permutationNumber < keyword.length()){
				int permutationIndexInKeyword = alphabetIndex(alphabet, keyword);
				if(permutationIndexInKeyword > -1){
					keywordPermutation[permutationIndexInKeyword] = permutationNumber;
					permutationNumber++;
				}
			}
			else{
				break;
			}
		}	
		
		return keywordPermutation;		
	}
	/**
	 * Get alphabet index in text
	 * @param Alphabet
	 * @param Text
	 * @return Index or -1 if no alphabet found
	 */
	private int alphabetIndex(char alphabet, String text){
		int index;
		for(index = 0; index < text.length(); index++){
			if(text.charAt(index) == alphabet){
				return index;
			}
		}
		return -1;
	}
	/**
	 * Fill message text to transposition grid
	 * @param Grid
	 * @param Message
	 */
	private void fillTranspositionGrid(char[][] grid, Message message){				
		int index = 0;		
		for(int y = 0; y < grid[0].length; y++){
			for(int x = 0; x < grid.length; x++){
				if(index < message.getText().length()){										
					grid[x][y] = message.getText().charAt(index);
				}
				else{
					grid[x][y] = 0;
				}
				index++;
			}
		}	
	}
	/**
	 * Transposition grid for message encryption
	 * @param Message
	 * @return Correct size of grid for message text
	 */	
	private char[][] getTranspositionGridForMessageEncryption(Message message) {		
		int width = message.getKeyword().length();					
		double height = Math.ceil((double)message.getText().length() / (double)width);	
		
		char[][] textGrid = new char[width][(int)height];	

		return textGrid;
	}
}

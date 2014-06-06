package nodomain.columnar_transposition_cipher;

public class TranspositionCipher {
		
	/**
	 * Encrypted message
	 * @param message
	 * @return Encrypted message with correct keyword to decrypt
	 */
	public Message encryptMessage(Message message) {
		StringBuilder encryptedText = new StringBuilder();
		
		int[] keywordPermutation = getKeywordPermutationByAlphabeticalOrder(message.getKeyword());	
		Debug.printIntArray(keywordPermutation);
		
		char[][] grid = getTranspositionGrid(message.getText(), message.getKeyword());
		Debug.print2DCharArray(grid);
		
		for(int permutationIndex = 0; permutationIndex < keywordPermutation.length; permutationIndex++){
			int x = keywordPermutation[permutationIndex];			
			for(int y = 0; y < grid[x].length; y++){
				if(grid[x][y] != 0){
					encryptedText.append(grid[x][y]);
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
	public Message decryptMessage(Message decryptableMessage) {
		StringBuilder decryptedText = new StringBuilder();
		
		return new Message(decryptedText.toString(), decryptableMessage.getKeyword());
	}
	/**
	 * Keyword permutation by alphabetical order
	 * @param keyword "acb"
	 * @return int[]{1,3,2}
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
	 * Alphabet index in text
	 * @param alphabet
	 * @param text
	 * @return index or -1 if no alphabet found
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
	 * Transposition grid
	 * @param text abcabca
	 * @param keywordLenght 4
	 * @return {
	 * 			abc,
	 * 			abc, 
	 * 			a00
	 * 			}
	 */	
	private char[][] getTranspositionGrid(String text, String keyword) {
		int width = keyword.length();					
		double height = Math.ceil((double)text.length() / (double)width);	
		char[][] textGrid = new char[width][(int)height];		
		
		int index = 0;		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(index < text.length()){
					textGrid[x][y] = text.charAt(index);
				}
				else{
					textGrid[x][y] = 0;
				}
				index++;
			}
		}		
		
		return textGrid;
	}
}

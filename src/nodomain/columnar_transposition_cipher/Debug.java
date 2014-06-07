package nodomain.columnar_transposition_cipher;

public class Debug {
	
	public static void printIntArray(int[] array){
		for(int index = 0; index < array.length; index++){
			System.out.print("["+array[index]+"]");
		}
		System.out.println();
	}
	
	public static void print2DCharArray(char[][] array){		
		for(int y = 0; y < array[0].length; y++){			
			for(int x = 0; x < array.length; x++){				
				System.out.print("["+array[x][y]+"]");				
			}
			System.out.println();
		}
	}

}

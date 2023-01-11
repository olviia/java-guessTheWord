public class WordGeneration{
	//At first, I adjusted that the letters were small, I only work with large ones here
	private String[] words = {"SUPERSTAR", "TRAILER", "PROGRAMMER", "CHIEVER", "DREAMER"};
	
	private int amount = 0;
	
	public String getWord(){
		return words[amount++];
	}

}
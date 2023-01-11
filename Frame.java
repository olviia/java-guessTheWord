import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;


public class Frame extends JFrame implements ActionListener{
	WordGeneration generator = new WordGeneration();
	TextChecker ps = new TextChecker();
	JButton BtnRestart = new JButton("Restart");
	JButton BtnAnswer = new JButton("Answer");
	JButton[] BtnLetters = new JButton[26];
	JTextField textField = new JTextField(30);
	JTextField textGuesses = new JTextField(2);
	boolean isFirst = false;
	JLabel labelGuesses = new JLabel("GUESSES REMAINING");
	JLabel labelWord = new JLabel("WORD TO GUESS");
	String word = "";
	String hiddenWord = "";
	int guesses = 0;
	
	
	public Frame(){
		
		wordStart();
		
		JPanel panelGuess = new JPanel();
		panelGuess.add(labelGuesses);
		panelGuess.add(textGuesses);
		JPanel panelWord = new JPanel(new GridLayout(2,1,5,5));
		panelWord.add(labelWord);
		panelWord.add(textField);
		JPanel panelUp = new JPanel(new GridLayout(2,1,5,5));
		panelUp.add(panelGuess);
		panelUp.add(panelWord);
		
		JPanel panelLetters = new JPanel(new GridLayout(4,7,5,5));
		for (int i = 0; i<=25; i++){
			BtnLetters[i] = new JButton("" +(char)(i+65));
			panelLetters.add(BtnLetters[i]);
			BtnLetters[i].addActionListener(this);
			BtnLetters[i].setActionCommand(""+(char)(i+65));;
		}
		panelLetters.add(BtnRestart);
		panelLetters.add(BtnAnswer);
		BtnRestart.addActionListener(this);
		BtnAnswer.addActionListener(this);
		BtnRestart.setActionCommand("Restart");
		BtnAnswer.setActionCommand("Answer");
		
		
		JPanel parentPanel = new JPanel(new GridLayout (2,1,5,5));
		parentPanel.add(panelUp);
		parentPanel.add(panelLetters);
		
		
		add(parentPanel);
	}
	public void txtUpdate(String input){
		String temp = textField.getText() + input;
		textField.setText(temp);
	}
	
	public  void actionPerformed(ActionEvent e){
		String temp = e.getActionCommand();
		if("Answer".equals(temp)){
			if(guesses > 0){
				textGuesses.setText(""+ --guesses);
				textField.setText(txtGuessUpdate());
				isFirst = true;
				if(isCorrect()){
					JOptionPane.showMessageDialog(null,"You Won!\nNew Game!");
					wordStart();
				}
				
			} else{
				JOptionPane.showMessageDialog(null,"You Lose!\nNew Game!");
				wordStart();
			}
			
		}else if("Restart".equals(temp)){
			wordStart();
			
		}else{
			if(isFirst){
				textField.setText("");
				isFirst = false;
			}
			txtUpdate(temp);
		}
	}
	public void Initialize(){
		this.setSize(600,350);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	//decided to write a bunch of initialization functions of the word. Is it necessary?
	public void wordStart(){
			isFirst = true;
			guesses = 9;
			generateWord();
			hideWord();
			textField.setText(hiddenWord);
			textGuesses.setText(""+guesses);
	}
	public void generateWord(){
		word = generator.getWord();
	}
	public void hideWord(){
		hiddenWord = "";
		for(int i = 0; i<word.length(); i++){
			hiddenWord += "*";
		}
	}
	public boolean isCorrect(){
		String temp = textField.getText();
		return temp.equals(word);
	}
	
	//Maybe there is a better option? I turn the terms into arrays of charms, if there are any in the puzzled word
	//letter from the list that we generated in Text Checker, then we substitute it in the array
	// with asterisks. Then we convert the array back into a string and assign a word with asterisks
	public String txtGuessUpdate(){
		ArrayList<Character> characters = new ArrayList<>();
		char[] wordArr = word.toCharArray();
		char[] hiddenArr = hiddenWord.toCharArray();
		String input = textField.getText();
		characters = TextChecker.compare(word, input);
		for(int i = 0; i<wordArr.length; i++){
			for(int j= 0; j < characters.size(); j++){
				if(wordArr[i] == characters.get(j)){
					hiddenArr[i] = wordArr[i];
				}
			}
		}
		//I don't know how to translate an array of charms in time
		String temp = new String(hiddenArr);
		hiddenWord = temp;
		return hiddenWord;
		
	}
}
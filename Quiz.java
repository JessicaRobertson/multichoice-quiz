import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Arrays;
import java.util.Random;

public class Quiz extends JFrame {
	private QuestionBank questionsList;
	private JLabel questionLabel;
	private JLabel correctLabel;
	private JLabel wrongLabel;
	private JButton correctButton;
	private JButton nextButton;
	private JPanel buttonPanel;
	private JPanel nextPanel;
	private Random num;
	private Random random;
	private int randomNum;
	private int randomIndex;
	private JButton[] buttonArray;
	
	public Quiz(){
		questionsList = new QuestionBank();
		questionsList.addQuestion("Is there a dog?", "Yes", new String[] {"No", "Maybe?", "Who knows..."});
		questionsList.addQuestion("Is there a cat?", "Sure", new String[] {"Never!", "Perhaps", "Always"});
		questionsList.addQuestion("What is the size of a float variable?", "32-bit", new String[] {"16-bit", "8-bit", "64-bit"});
		questionsList.addQuestion("Which is not a primitive type?", "string", new String[] {"char", "int", "double"});
		
		questionLabel = new JLabel();
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionLabel.setOpaque(true);
		questionLabel.setBackground(new Color(204, 229, 255));
		questionLabel.setPreferredSize(new Dimension(280, 180));
		
		correctLabel = new JLabel();
		correctLabel.setText("CORRECT!");
		correctLabel.setHorizontalAlignment(SwingConstants.CENTER);
		correctLabel.setOpaque(true);
		correctLabel.setBackground(new Color(153, 255, 153));
		correctLabel.setPreferredSize(new Dimension(280, 180));
		
		wrongLabel = new JLabel();
		wrongLabel.setText("You're an idiot.");
		wrongLabel.setHorizontalAlignment(SwingConstants.CENTER);
		wrongLabel.setOpaque(true);
		wrongLabel.setBackground(new Color(255, 255, 153));
		wrongLabel.setPreferredSize(new Dimension(280, 180));
		
		correctButton = new JButton();
		correctButton.setHorizontalAlignment(SwingConstants.CENTER);
		correctButton.setPreferredSize(new Dimension(120, 60));
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(255, 204, 255));
		buttonPanel.setPreferredSize(new Dimension(200, 180));
		
		nextButton = new JButton();
		nextButton.setText("Next Question");
		nextButton.setHorizontalAlignment(SwingConstants.CENTER);
		nextButton.setPreferredSize(new Dimension(120, 60));
		
		nextPanel = new JPanel();
		nextPanel.setBackground(new Color(229, 204, 255));
		nextPanel.setPreferredSize(new Dimension(200, 70));
		
		num = new Random();
		randomNum = num.nextInt(4);
		//System.out.println("Random number: " + randomNum);
		
		random = new Random();
		randomIndex = random.nextInt(questionsList.getQueList().size());
		//System.out.println("Random index:  " + randomIndex);
		
		buttonArray = new JButton[5];
	}
	
	public void makeFrame(){
		JFrame frame = new JFrame("Java Quiz");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(255, 204, 255));
		
		String firstQuestion = questionsList.getQueList().get(randomIndex);
		String firstAnswer = questionsList.getAnswer(firstQuestion);
		questionLabel.setText(firstQuestion);
		frame.getContentPane().add(questionLabel, BorderLayout.NORTH);
		
		for(int counter = 0; counter < randomNum; counter++){
			JButton wrongButton = new JButton();
			wrongButton.setText(questionsList.getOptions(randomIndex)[counter]);
			wrongButton.setHorizontalAlignment(SwingConstants.CENTER);
			wrongButton.setPreferredSize(new Dimension(120, 60));
			
			wrongButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent w){
					frame.getContentPane().remove(nextPanel);
					frame.getContentPane().remove(buttonPanel);
					frame.getContentPane().add(wrongLabel, BorderLayout.CENTER);
					frame.getContentPane().add(nextPanel, BorderLayout.SOUTH);
					frame.revalidate();
					frame.repaint();
				}
			});
			
			//buttonPanel.add(wrongButton, BorderLayout.WEST);
			buttonArray[counter] = wrongButton;
		}
		
		/*
		JButton wrongButton1 = new JButton();
		wrongButton1.setText("No");
		wrongButton1.setHorizontalAlignment(SwingConstants.CENTER);
		wrongButton1.setPreferredSize(new Dimension(120, 60));
		
		JButton wrongButton2 = new JButton();
		wrongButton2.setText("Maybe?");
		wrongButton2.setHorizontalAlignment(SwingConstants.CENTER);
		wrongButton2.setPreferredSize(new Dimension(120, 60));
		
		JButton wrongButton3 = new JButton();
		wrongButton3.setText("Who knows...");
		wrongButton3.setHorizontalAlignment(SwingConstants.CENTER);
		wrongButton3.setPreferredSize(new Dimension(120, 60));
		*/
		
		correctButton.setText(firstAnswer);
		correctButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				frame.getContentPane().remove(nextPanel);
				frame.getContentPane().remove(buttonPanel);
				frame.getContentPane().add(correctLabel, BorderLayout.CENTER);
				frame.getContentPane().add(nextPanel, BorderLayout.SOUTH);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		//buttonPanel.add(correctButton, BorderLayout.WEST);
		buttonArray[randomNum] = correctButton;
		
		for(int counter = randomNum; counter < 3; counter++){
			JButton wrongButton = new JButton();
			wrongButton.setText(questionsList.getOptions(randomIndex)[counter]);
			wrongButton.setHorizontalAlignment(SwingConstants.CENTER);
			wrongButton.setPreferredSize(new Dimension(120, 60));
			
			wrongButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent r){
					frame.getContentPane().remove(nextPanel);
					frame.getContentPane().remove(buttonPanel);
					frame.getContentPane().add(wrongLabel, BorderLayout.CENTER);
					frame.getContentPane().add(nextPanel, BorderLayout.SOUTH);
					frame.revalidate();
					frame.repaint();
				}
			});
			
			//buttonPanel.add(wrongButton, BorderLayout.WEST);
			buttonArray[counter + 1] = wrongButton;
		}
		
		/*
		buttonPanel.add(wrongButton1, BorderLayout.EAST);
		buttonPanel.add(wrongButton2, BorderLayout.WEST);
		buttonPanel.add(wrongButton3, BorderLayout.EAST);
		*/
		
		for(int count = 0; count < 4; count++){
			buttonPanel.add(buttonArray[count], BorderLayout.WEST);
		}
		frame.getContentPane().add(buttonPanel);
		
		nextPanel.add(nextButton);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent n){
				//System.out.println("Beep");
				
				int correctIndex = randomNum;
				int prevIndex = randomIndex;
				
				randomNum = num.nextInt(4);
				
				while(randomIndex == prevIndex){
					randomIndex = random.nextInt(questionsList.getQueList().size());
				}
				//System.out.println("Random number: " + randomNum);
				//System.out.println("Random index:  " + randomIndex);
				
				for(int count = 0; count < 4; count++){
					buttonArray[count].setText("Reset");
				}
				
				//System.out.println(correctIndex + " " + randomNum);
				buttonArray[correctIndex] = buttonArray[randomNum];
				buttonArray[randomNum] = correctButton;
				
				for(int counter = 0; counter < randomNum; counter++){
					buttonArray[counter].setText(questionsList.getOptions(randomIndex)[counter]);
				}
				
				for(int counter = randomNum + 1; counter < 4; counter++){
					buttonArray[counter].setText(questionsList.getOptions(randomIndex)[counter - 1]);
				}
				
				questionLabel.setText(questionsList.getQueList().get(randomIndex));
				correctButton.setText(questionsList.getAnsList().get(randomIndex));
				
				buttonPanel.removeAll();
				for(int count = 0; count < 4; count++){
					buttonPanel.add(buttonArray[count], BorderLayout.WEST);
				}
				
				frame.getContentPane().remove(nextPanel);
				frame.getContentPane().remove(correctLabel);
				frame.getContentPane().remove(buttonPanel);
				frame.getContentPane().remove(wrongLabel);
				frame.getContentPane().add(buttonPanel);
				frame.getContentPane().add(nextPanel, BorderLayout.SOUTH);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		frame.getContentPane().add(nextPanel, BorderLayout.SOUTH);
		
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Quiz quiz = new Quiz();
		quiz.makeFrame();
	}

}

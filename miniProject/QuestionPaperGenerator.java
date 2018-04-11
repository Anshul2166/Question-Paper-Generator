package miniProject;

import javax.swing.JFrame;

public class QuestionPaperGenerator {
	JFrame accessFrame;
	
	public static void main(String args[])
	{
		new QuestionPaperGenerator();
	}
	QuestionPaperGenerator(){
		accessFrame=new JFrame();
		accessFrame.setSize(400,400);
		accessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accessFrame.setTitle("OOP Mini Project");
		accessFrame.setVisible(true);
	}
}

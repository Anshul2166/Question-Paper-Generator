package miniProject;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//ID : 2015  PASSWORD: A8459

public class QuestionPaperGenerator extends JFrame {
	JButton submit, viewB, delete, edit, insert, Generate,Format,Question,Reject;
	JTextField tid, numberT;
	JRadioButton sub1, sub2;
	JPasswordField pid;
	JRadioButton op1, op2, op3;
	int buttonClicked;
	int subject = 1, choice = 1, number = 5;
	String format=".txt";
	File fileq = new File("Question.txt");
	File filea = new File("Answer.txt");

	public QuestionPaperGenerator() {
		this.setSize(300, 150);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Question Paper Generator");
		JPanel Panel1 = new JPanel(new GridLayout(3, 3));
		Panel1.setBackground(Color.lightGray);
		JLabel id = new JLabel("USER ID : ");
		Panel1.add(id);

		tid = new JTextField(10);
		tid.setText("2015");
		Panel1.add(tid);
		JLabel label2 = new JLabel("PASSWORD :");
		Panel1.add(label2);
		pid = new JPasswordField(10);
		pid.setText("A8459");
		Panel1.add(pid);
		submit = new JButton("SUBMIT");
		submit.setBounds(500, 500, 480, 430);
		SubmitClicked lforbutton = new SubmitClicked();
		submit.addActionListener(lforbutton);

		Panel1.add(new JLabel(""));
		Panel1.add(submit);
		this.add(Panel1);
		this.setVisible(true);

	}

	private class SubmitClicked implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submit) {
				String sid = tid.getText();
				String spass = pid.getText();
				if (sid.equals("2015") && spass.equals("A8459")) {
					new QuestionPaperGeneratorWindow();
					QuestionPaperGenerator.this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(QuestionPaperGenerator.this, "User ID/ Password combination error",
							"Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		}

	}

	public class QuestionPaperGeneratorWindow extends JFrame implements ItemListener, ActionListener {
		QuestionPaperGeneratorWindow() {
			this.setSize(400, 400);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("GENERATE QUIZ");

			JTextField display = new JTextField();
			JTextField dispNUM = new JTextField();
			numberT = new JTextField();
			display.setText("SELECT A SUBJECT");
			display.setBounds(50, 50, 300, 35);
			display.setEditable(false);

			sub1 = new JRadioButton("MATH", null, true);
			sub2 = new JRadioButton("ENGLISH");
			ButtonGroup radioGroup = new ButtonGroup();
			radioGroup.add(sub1);
			radioGroup.add(sub2);

			JPanel panelR = new JPanel();
			// panelR.setBackground(Color.lightGray);
			panelR.add(sub1);
			panelR.add(sub2);
			panelR.setBounds(50, 110, 300, 30);

			dispNUM.setText("Number of Questions");
			dispNUM.setBounds(20, 150, 120, 30);
			dispNUM.setEditable(false);

			numberT.setText("5");
			numberT.setEditable(true);
			numberT.setBounds(150, 150, 120, 35);

			viewB = new JButton("View Paper");
			viewB.setBounds(50, 255, 130, 30);
			delete = new JButton("Delete");
			delete.setBounds(220, 255, 130, 30);
			edit = new JButton("Edit Type");
			edit.setBounds(220, 200, 130, 30);
			insert = new JButton("Question Type");
			insert.setBounds(50, 200, 130, 30);
			viewB.addActionListener(this);
			delete.addActionListener(this);
			edit.addActionListener(this);
			insert.addActionListener(this);

			sub1.addItemListener(this);
			sub2.addItemListener(this);

			JPanel Panel2 = new JPanel();// main panel
			this.setBackground(Color.lightGray);
			this.add(display);
			this.add(panelR);
			this.add(dispNUM);
			this.add(numberT);
			this.add(viewB);
			this.add(delete);
			this.add(edit);
			this.add(insert);
			this.add(Panel2);
			this.setVisible(true);
		}

		public void itemStateChanged(ItemEvent ie) {
			if (ie.getSource() == sub1)
				subject = 1;
			else if (ie.getSource() == sub2)
				subject = 2;
		}

		public void actionPerformed(ActionEvent e) {
			Desktop desktop = Desktop.getDesktop();
			if (e.getSource() == insert) {
				new Option();
			} else if (e.getSource() == viewB) {
				if (fileq.exists() && filea.exists()) {
					try {
						desktop.open(fileq);
						desktop.open(filea);
					} catch (Exception e1) {
					}
				}

				else {
					JOptionPane.showMessageDialog(this, "Question Paper Does Not Exist.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else if (e.getSource() == delete) {
				JOptionPane.showMessageDialog(this, "Question/Answer Paper Deleted", "Error",
						JOptionPane.ERROR_MESSAGE);
				Delete();
			} else if (e.getSource() == edit) {
				if (fileq.exists() && filea.exists()) {
					try {
						new SelectType();
					} catch (Exception e1) {
					}
				}

				else {
					JOptionPane.showMessageDialog(this, "File Does Not Exist. Generate a file to edit.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public class SelectType extends JFrame implements ActionListener, ItemListener {
		SelectType(){
			this.setSize(400, 400);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setTitle("Output File Format");
			this.setResizable(false);
			JTextField display = new JTextField();
			display.setText("Select Output File Type  :");
			display.setBounds(50, 50, 300, 35);
			display.setEditable(false);
			
			op1 = new JRadioButton("txt", null, true);
			op1.setBounds(50, 100, 150, 40);
			op2 = new JRadioButton("docx");
			op2.setBounds(50, 150, 150, 40);
			op3 = new JRadioButton("pdf");
			op3.setBounds(50, 200, 150, 40);
			ButtonGroup questionType = new ButtonGroup();
			questionType.add(op1);
			questionType.add(op2);
			questionType.add(op3);
			
			JPanel panelForInsertQuestionFrame = new JPanel();

			Format = new JButton("Format");
			Format.setBounds(150, 300, 100, 30);
			
			op1.addItemListener(this);
			op2.addItemListener(this);
			op3.addItemListener(this);
			Format.addActionListener(this);
			this.add(op1);
			this.add(op2);
			this.add(op3);
			this.add(display);
			this.add(Format);
			this.add(panelForInsertQuestionFrame);
			this.setVisible(true);
		}

		@Override
		public void itemStateChanged(ItemEvent ie) {
			// TODO Auto-generated method stub
			if (ie.getSource() == op1)
				format=".txt";
			else if (ie.getSource() == op2)
				format=".docx";
			else if(ie.getSource()==op3)
				format=".pdf";
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			// TODO Auto-generated method stub
			if(ae.getSource()==Format){
				setVisible(false);
				System.out.println("Hello");
			}
		}
	}
	public class Option extends JFrame implements ActionListener, ItemListener {
		Option() {

			this.setSize(400, 400);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setTitle("Inserting Options");
			this.setResizable(false);
			JTextField display = new JTextField();
			display.setText("QUESTION PAPER TYPE  :");

			display.setBounds(50, 50, 300, 35);
			display.setEditable(false);

			op1 = new JRadioButton("Multiple Choice Questions", null, true);
			op1.setBounds(50, 100, 150, 40);
			op2 = new JRadioButton("Fill in the Blanks");
			op2.setBounds(50, 150, 150, 40);
			op3 = new JRadioButton("True/False");
			op3.setBounds(50, 200, 150, 40);

			ButtonGroup questionType = new ButtonGroup();
			questionType.add(op1);
			questionType.add(op2);
			questionType.add(op3);

			JPanel panelForInsertQuestionFrame = new JPanel();

			Generate = new JButton("Generate");
			Generate.setBounds(150, 300, 100, 30);
			this.add(op1);
			this.add(op2);
			this.add(op3);

			this.add(display);
			this.add(Generate);
			this.add(panelForInsertQuestionFrame);
			this.setVisible(true);
			Generate.addActionListener(this);
			op1.addItemListener(this);
			op2.addItemListener(this);
			op3.addItemListener(this);
		}

		public void itemStateChanged(ItemEvent ie) {
			if (ie.getSource() == op1)
				choice = 1;
			else if (ie.getSource() == op2)
				choice = 2;
			else if (ie.getSource() == op3)
				choice = 3;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Generate) {
				String n1 = numberT.getText();
				number = Integer.parseInt(n1);
				try {
					generate(subject, choice, number);
				} catch (Exception excep) {
				}
			}

			setVisible(false);
		}

	}

	void generate(int sub, int choice, int n) throws IOException {
		System.out.println(format);
		BufferedReader q, a;
		PrintWriter writerq = new PrintWriter("Question"+format, "UTF-8");
		PrintWriter writera = new PrintWriter("Answer"+format, "UTF-8");

		if (sub == 1) {
			if (choice == 1) {
				q = new BufferedReader(new FileReader("mathq1.txt"));
				a = new BufferedReader(new FileReader("matha1.txt"));
			}

			else {
				q = new BufferedReader(new FileReader("mathq2.txt"));
				a = new BufferedReader(new FileReader("matha2.txt"));
			}

		}

		else {
			if (choice == 1) {
				q = new BufferedReader(new FileReader("engq1.txt"));
				a = new BufferedReader(new FileReader("enga1.txt"));
			}

			else {
				q = new BufferedReader(new FileReader("engq2.txt"));
				a = new BufferedReader(new FileReader("enga2.txt"));
			}
		}

		Random random = new Random();
		ArrayList<Integer> lineNo = new ArrayList<Integer>();

		for (int i = 0; i < 10; i++)
			lineNo.add(i);

		Collections.shuffle(lineNo);

		for (int i = 9; i > n - 1; i--)
			lineNo.remove(i);

		Collections.sort(lineNo);

		String info = "";

		writerq.println("QUESTION :");
		String formatStr = "%-70s %-55s";
		writerq.println(String.format(formatStr,"","BITS Pilani Hyderabad Campus"));
		formatStr = "%-10s %-10s %140s %-85s";
		writerq.println(String.format(formatStr," ","Subject:Maths"," ","Date:27/04/2016"));
		writerq.println(String.format(formatStr," ","Course-Code:MATHF211"," ","MM:50"));
		writerq.println("          --------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0, c = 0; i < 10; i++) {
			if (lineNo.contains(i)) {
				info = q.readLine();
				writerq.println((++c) + ". " + info);
			}
			else
				info = q.readLine();

		}

		writera.println("ANSWER :");

		for (int i = 0, c = 0; i < 10; i++) {
			if (lineNo.contains(i)) {
				info = a.readLine();
				writera.println((++c) + ". " + info);
			}

			else
				info = a.readLine();

		}

		q.close();
		a.close();
		writerq.close();
		writera.close();
	}

	void Delete() {
		if (fileq.exists() && filea.exists()) {
			fileq.delete();
			filea.delete();
		}
	}

	public static void main(String[] args) {
		new QuestionPaperGenerator();
	}

}

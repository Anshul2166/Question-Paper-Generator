
package miniProject;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//ID : 2015  PASSWORD: A3309
public class QuestionPaperGenerator extends JFrame {
	JButton submit, viewB, delete, edit, insert, Generate, Format, Question, Reject, test;
	JTextField tid, numberT, instituteField, marksField, dateField, codeField, subjectField;
	JRadioButton sub1, sub2;
	JPasswordField pid;
	JRadioButton op1, op2, op3;
	int buttonClicked;
	int subject = 1, choice = 1, number = 5, totalQuestions = 10;
	String format = ".txt";
	File fileq = new File("Question.txt");
	File filea = new File("Answer.txt");
	String instituteName, maxMarks, date, courseCode, topic, qBankName, aBankName;

	public QuestionPaperGenerator() {
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Question Paper Generator");
		JPanel Panel1 = new JPanel(new GridLayout(8, 8));
		Panel1.setBackground(Color.lightGray);
		JLabel id = new JLabel("USER ID : ");
		Panel1.add(id);
		tid = new JTextField(10);
		tid.setText("2015");
		Panel1.add(tid);
		JLabel label2 = new JLabel("PASSWORD :");
		Panel1.add(label2);
		pid = new JPasswordField(10);
		pid.setText("A3309");
		Panel1.add(pid);
		JLabel instituteFieldLabel = new JLabel("Name of Institute : ");
		JLabel marksFieldLabel = new JLabel("Maximum Marks : ");
		JLabel dateFieldLabel = new JLabel("Date of Exam : ");
		JLabel subjectFieldLabel = new JLabel("Subject : ");
		JLabel codeFieldLabel = new JLabel("Subject Code : ");
		instituteField = new JTextField(20);
		codeField = new JTextField(10);
		subjectField = new JTextField(10);
		dateField = new JTextField(10);
		marksField = new JTextField(10);

		instituteField.setText("BITS Pilani Hyderabad Campus");
		codeField.setText("MATHF211");
		subjectField.setText("MATHS");
		dateField.setText("27-04-2018");
		marksField.setText("50");

		Panel1.add(instituteFieldLabel);
		Panel1.add(instituteField);
		Panel1.add(codeFieldLabel);
		Panel1.add(codeField);
		Panel1.add(subjectFieldLabel);
		Panel1.add(subjectField);
		Panel1.add(dateFieldLabel);
		Panel1.add(dateField);
		Panel1.add(marksFieldLabel);
		Panel1.add(marksField);
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
				String instituteNameLocal = instituteField.getText();
				String courseCodeLocal = codeField.getText();
				String subjectLocal = subjectField.getText();
				String marksLocal = marksField.getText();
				String dateLocal = dateField.getText();
				if (sid.equals("2015") && spass.equals("A3309") && !instituteNameLocal.isEmpty()
						&& !courseCodeLocal.isEmpty() && !subjectLocal.isEmpty() && !marksLocal.isEmpty()
						&& !dateLocal.isEmpty()) {
					new QuestionPaperGeneratorWindow();
					instituteName = instituteNameLocal;
					courseCode = courseCodeLocal;
					topic = subjectLocal;
					date = dateLocal;
					maxMarks = marksLocal;
					QuestionPaperGenerator.this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(QuestionPaperGenerator.this, "Filled Entries are invalid", "Error",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		}

	}

	public class QuestionPaperGeneratorWindow extends JFrame implements ItemListener, ActionListener {
		QuestionPaperGeneratorWindow() {
			this.setSize(400, 450);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("GENERATE QUIZ");

			JTextField display = new JTextField();
			JTextField dispNUM = new JTextField();
			numberT = new JTextField();
			display.setText("SELECT A SUBJECT");
			display.setHorizontalAlignment(SwingConstants.CENTER);
			display.setBounds(50, 50, 300, 35);
			display.setEditable(false);

			sub1 = new JRadioButton("MATH", null, true);
			sub2 = new JRadioButton("ENGLISH");
			ButtonGroup radioGroup = new ButtonGroup();
			radioGroup.add(sub1);
			radioGroup.add(sub2);

			JPanel panelR = new JPanel();
			panelR.add(sub1);
			panelR.add(sub2);
			panelR.setBounds(50, 110, 300, 30);

			dispNUM.setText("Number of Questions");
			dispNUM.setBounds(20, 150, 120, 30);
			display.setHorizontalAlignment(SwingConstants.CENTER);
			dispNUM.setEditable(false);

			numberT.setText("5");
			numberT.setEditable(true);
			numberT.setBounds(150, 150, 120, 35);

			viewB = new JButton("View Paper");
			viewB.setBounds(50, 255, 130, 30);
			delete = new JButton("Delete");
			delete.setBounds(220, 255, 130, 30);
			edit = new JButton("Edit Q. Bank");
			edit.setBounds(220, 200, 130, 30);
			insert = new JButton("Question Type");
			insert.setBounds(50, 200, 130, 30);
			test = new JButton("Take a test");
			test.setBounds(135, 310, 130, 30);
			viewB.addActionListener(this);
			delete.addActionListener(this);
			edit.addActionListener(this);
			insert.addActionListener(this);
			test.addActionListener(this);
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
			this.add(test);
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
						new SelectBank();
					} catch (Exception e1) {
					}
				}

				else {
					JOptionPane.showMessageDialog(this, "File Does Not Exist. Generate a file to edit.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else if (e.getSource() == test) {
				if (fileq.exists() && filea.exists()) {
					try {
						new TakeATest();
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

	public class TakeATest extends JFrame implements ActionListener, ItemListener {

		int currentQuestion = 0;
		List<String> questions = new ArrayList<String>();
		List<String> choices = new ArrayList<String>();
		List<String> answers = new ArrayList<String>();
		int correct = 0;
		int wrong = 0;
		JTextField user_answer;
		JTextArea area1;

		TakeATest() {
			this.setSize(1000, 500);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("Take a test");
			this.setVisible(true);
			BufferedReader q = null, a = null;
			try {
				q = new BufferedReader(new FileReader("Question.txt"));
				a = new BufferedReader(new FileReader("Answer.txt"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			String info = null;
			for (int p = 0; p < 8; p++) {
				try {
					String s = q.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			for (int j = 0; j < 10; j++) {
				String ques = null;
				try {
					ques = q.readLine();
					if (ques == null) {
						break;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (choice == 1) {
					try {
						questions.add(ques);
						choices.add(q.readLine());
						String s = q.readLine();
						answers.add(a.readLine());
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					try {
						questions.add(ques);
						String s = q.readLine();
						answers.add(a.readLine());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println(questions);
			JPanel panel = new JPanel();
			area1 = new JTextArea();
			area1.setEditable(false);
			area1.setSize(1000, 200);
			area1.setBounds(0, 0, 1000, 200);
			user_answer = new JTextField();
			JButton next = new JButton("Next=>");
			user_answer.setBounds(460, 225, 80, 80);
			user_answer.addActionListener(this);
			user_answer.setHorizontalAlignment(SwingConstants.CENTER);
			user_answer.setFont(new Font("Arial", Font.BOLD, 16));
			next.addActionListener(this);
			next.setBounds(860, 300, 80, 80);
			this.add(area1);
			this.add(user_answer);
			this.add(next);
			this.add(panel);
			if (choice == 1) {
				area1.setText("\n\n\n" + questions.get(0) + "\n" + "\n" + choices.get(0));
			} else {
				area1.setText("\n\n\n" + questions.get(0));
			}
			area1.setFont(new Font("Arial", Font.BOLD, 16));
		}

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (user_answer.getText().equalsIgnoreCase(answers.get(currentQuestion))) {
				correct++;
			} else
				wrong++;

			currentQuestion++;
			if (currentQuestion == number) {
				JOptionPane.showMessageDialog(this,
						"Number of correct answers is" + correct + "\n" + "Number of wrong answers is" + wrong);
				System.out.println(currentQuestion);
				return;
			}
			if (choice == 1) {
				area1.setText("\n\n\n" + questions.get(currentQuestion) + "\n" + "\n" + choices.get(currentQuestion));
			} else {
				area1.setText("\n\n\n" + questions.get(currentQuestion));
			}
			this.setTitle("Question " + (currentQuestion + 1) + " of " + number);
		}
	}

	public class SelectBank extends JFrame implements ActionListener, ItemListener {
		JButton selectQBank, selectABank, generateFromBank;
		String qBank = null;
		String aBank = null;
		JRadioButton mcq, mcqN;
		JTextField qBankFile, aBankFile;

		SelectBank() {
			this.setSize(500, 450);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setTitle("Select Question Banks");
			this.setResizable(false);
			JTextField display = new JTextField();
			display.setText("Select Question Banks  :");
			display.setBounds(50, 50, 300, 35);
			display.setEditable(false);

			JLabel labelMcq = new JLabel("Is paper mcq?");
			mcq = new JRadioButton("Yes", null, true);
			mcqN = new JRadioButton("No");
			ButtonGroup group = new ButtonGroup();
			group.add(mcq);
			group.add(mcqN);

			JPanel panelT = new JPanel(new GridLayout(2, 2));
			JLabel qBankFileLabel = new JLabel("Question Bank Selected:");
			JLabel aBankFileLabel = new JLabel("Answer Bank Selected:");
			qBankFile = new JTextField();
			qBankFile.setEditable(false);
			qBankFile.setText("None Selected");
			qBankFile.setHorizontalAlignment(SwingConstants.CENTER);
			aBankFile = new JTextField();
			aBankFile.setEditable(false);
			aBankFile.setText("None Selected");
			aBankFile.setHorizontalAlignment(SwingConstants.CENTER);
			panelT.setBounds(50, 50, 350, 100);

			panelT.add(qBankFileLabel);
			panelT.add(qBankFile);
			panelT.add(aBankFileLabel);
			panelT.add(aBankFile);

			JPanel panelR = new JPanel();
			panelR.add(labelMcq);
			panelR.add(mcq);
			panelR.add(mcqN);
			panelR.setBounds(90, 110, 300, 30);

			selectQBank = new JButton("Select Question Bank");
			selectQBank.setBounds(50, 200, 200, 30);
			selectABank = new JButton("Select Answer Bank");
			selectABank.setBounds(280, 200, 200, 30);
			generateFromBank = new JButton("Generate From Bank");
			generateFromBank.setBounds(125, 275, 255, 30);
			selectQBank.addActionListener(this);
			selectABank.addActionListener(this);
			generateFromBank.addActionListener(this);

			JPanel panel = new JPanel();
			this.add(panelT);
			this.add(panelR);
			this.add(selectQBank);
			this.add(selectABank);
			this.add(generateFromBank);
			this.add(panel);
			this.setVisible(true);
		}

		@Override
		public void itemStateChanged(ItemEvent ie) {
			if (ie.getSource() == mcq) {
				choice = 1;
			} else if (ie.getSource() == mcqN) {
				choice = 2;
			}
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == selectQBank) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					qBank = chooser.getSelectedFile().getAbsolutePath();
					qBankFile.setText(chooser.getSelectedFile().getName());
				}
			} else if (ae.getSource() == selectABank) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					aBank = chooser.getSelectedFile().getAbsolutePath();
					aBankFile.setText(chooser.getSelectedFile().getName());
				}
			} else if (ae.getSource() == generateFromBank) {
				if (!(qBank == null) || !(aBank == null)) {
					qBankName = qBank;
					aBankName = aBank;
					try {
						generate(1, choice, 5, true);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select both banks");
				}
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
					generate(subject, choice, number, false);
				} catch (Exception excep) {
				}
			}

			setVisible(false);
		}

	}

	void generate(int sub, int choice, int n, boolean bank) throws IOException {
		System.out.println(format);
		BufferedReader q = null, a = null;
		PrintWriter writerq = new PrintWriter(new FileOutputStream("Question.txt"), true);
		PrintWriter writera = new PrintWriter(new FileOutputStream("Answer.txt"), true);
		try {
			if (bank) {
				q = new BufferedReader(new FileReader(qBankName));
				a = new BufferedReader(new FileReader(aBankName));
			} else {
				if (sub == 1) {
					if (choice == 1) {
						q = new BufferedReader(new FileReader("mathq1.txt"));
						a = new BufferedReader(new FileReader("matha1.txt"));
					} else if (choice == 2) {
						q = new BufferedReader(new FileReader("mathq2.txt"));
						a = new BufferedReader(new FileReader("matha2.txt"));
					} else {
						q = new BufferedReader(new FileReader("mathq3.txt"));
						a = new BufferedReader(new FileReader("matha3.txt"));
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
			String formatStr = "%-70s %-55s";
			writerq.println();
			writerq.println();
			writerq.println();
			writerq.println(String.format(formatStr, "", instituteName));
			formatStr = "%-10s %-10s %140s %-85s";
			writerq.println(String.format(formatStr, " ", "Subject:" + topic, " ", "Date:" + date));
			writerq.println(String.format(formatStr, " ", "Course-Code:" + courseCode, " ", "MM:" + maxMarks));
			writerq.println(
					"          --------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			writerq.println();
			for (int i = 0, c = 0;; i++) {
				info = q.readLine();
				if (info == null) {
					// reached end of file
					break;
				}
				if (lineNo.contains(i)) {
					String s = (++c) + ". " + info;
					if (choice == 1) {
						int indexA = s.indexOf("A.");
						int indexB = s.indexOf("B.");
						int indexC = s.indexOf("C.");
						int indexD = s.indexOf("D.");
						System.out.println(indexA + " " + indexB + " " + indexC + " " + indexD);
						String ques = s.substring(0, indexA - 1);
						System.out.println(ques);
						String optionA = s.substring(indexA, indexB);
						String optionB = s.substring(indexB, indexC);
						String optionC = s.substring(indexC, indexD);
						String optionD = s.substring(indexD);
						writerq.println(String.format("%1$" + (10 + ques.length()) + "s", ques));
						writerq.print(String.format("%1$" + (10 + optionA.length()) + "s", optionA));
						writerq.print(String.format("%1$" + (10 + optionB.length()) + "s", optionB));
						writerq.print(String.format("%1$" + (10 + optionC.length()) + "s", optionC));
						writerq.println(String.format("%1$" + (10 + optionD.length()) + "s", optionD));
					} else {
						writerq.println(String.format("%1$" + (10 + s.length()) + "s", s));
					}
					writerq.println();
				}

			}
			for (int i = 0, c = 0; i < 10; i++) {
				if (lineNo.contains(i)) {
					info = a.readLine();
					writera.println(info);
				} else
					info = a.readLine();
				// Desktop desktop = Desktop.getDesktop();
				// desktop.open(fileq);
				// desktop.open(filea);
			}
		} catch (Exception e) {
			e.printStackTrace(writerq);
			e.printStackTrace(writera);
			JOptionPane.showMessageDialog(this, "File Does not Follow proper format");
		} finally {
			q.close();
			a.close();
			writerq.close();
			writera.close();
		}
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

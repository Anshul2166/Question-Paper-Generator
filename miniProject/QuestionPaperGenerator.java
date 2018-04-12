package miniProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class QuestionPaperGenerator {
	JFrame accessFrame,optionsMenuFrame;
	JButton loginButton;
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
		
		//this is the button for registered users (on entering username and password)
		loginButton = new JButton();
		loginButton.setText("SUBMIT");
		loginButton.setBounds(50, 275, 100, 30);
		loginButton.setToolTipText("Registered User (access to full functionality)");
		
		accessFrame.add(loginButton);
		
		ListenForAccess loginListen=new ListenForAccess();
        loginButton.addActionListener(loginListen);
        
	}
	private class OptionsMenuFrame extends JFrame
    {
		public OptionsMenuFrame()
        {
        	optionsMenuFrame = new JFrame();
            optionsMenuFrame.setSize(400,400);
            optionsMenuFrame.setLocationRelativeTo(null);
            optionsMenuFrame.setTitle("Registered User");
            optionsMenuFrame.setResizable(false);
            optionsMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            optionsMenuFrame.setVisible(true);
        }
    }
	private class ListenForAccess implements ActionListener, WindowListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource() == loginButton)
            {
                    new OptionsMenuFrame();
//                    accessFrame.setEnabled(false);
            }
        }

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}
}
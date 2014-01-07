package at.SecChat.UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class UI extends JFrame{
	private final JPanel mainP = new JPanel();
	private final JPanel publicInPanle = new JPanel();
	private final JPanel publicSendenButtonPane = new JPanel();
	
	private final JTextArea publicOut = new JTextArea();
	private final JScrollPane publicOutScrollPane = new JScrollPane(publicOut);
	private final JTextArea publicIn = new JTextArea(3, 50);
	private final JButton publicSendenButton = new JButton("Senden");
	
	private String id = "";
	
	
	public UI () {
		
		//ID Wird später vom Benutzername abgeleitet
		id = "Michael";
		
		publicIn.setText("");
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("SecChat!");
		
		mainP.setLayout(new BorderLayout(5, 5));
		publicInPanle.setLayout(new BorderLayout(5, 5));
		publicSendenButtonPane.setLayout(new GridLayout(1, 1));
		
		publicOut.setEnabled(false);
		publicIn.setEnabled(false);
		publicSendenButton.setEnabled(false);
		
		mainP.add(publicOutScrollPane, BorderLayout.CENTER);
		mainP.add(publicInPanle, BorderLayout.SOUTH);
		
		publicSendenButtonPane.add(publicSendenButton);
		
		publicInPanle.add(publicIn, BorderLayout.CENTER);
		publicInPanle.add(publicSendenButtonPane, BorderLayout.EAST);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu cmdVerbindung = new JMenu("Verbingung");
		menuBar.add(cmdVerbindung);
		
		JMenuItem cmdVerbinden = new JMenuItem("Verbinden...");
		cmdVerbindung.add(cmdVerbinden);
		
		JMenuItem cmdTrennen = new JMenuItem("Trennen");
		cmdVerbindung.add(cmdTrennen);
		
		
		
		JMenu cmdSecurity = new JMenu("Security");
		menuBar.add(cmdSecurity);
		
		JMenuItem cmdStandard = new JMenuItem("Standard");
		cmdSecurity.add(cmdStandard);
		
		JMenuItem cmdPrivate = new JMenuItem("Private");
		cmdSecurity.add(cmdPrivate);
		
		
		
		ActionListener verbinden = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verbindungAufbauen();
			}
		};
		cmdVerbinden.addActionListener(verbinden);
		
		ActionListener trennen = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verbindungTrennen();
			}
		};
		cmdTrennen.addActionListener(trennen);
		
		ActionListener sendenPublic = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (publicIn.getText().equals("")) {
				}else{
					sayPublic(publicIn.getText());
					publicIn.setText("");
				}
			}
		};
		publicSendenButton.addActionListener(sendenPublic);
		
		add(mainP);
		pack();
	}
	
	private void verbindungTrennen() {
		publicIn.setText("");
		publicIn.setEditable(false);
		publicSendenButton.setEnabled(false);
	}
	
	private final JPanel verifyPanel = new JPanel();
	private final JPanel verifyTextPanel = new JPanel();
	private final JPanel verifyButtonPanel = new JPanel();
	private final JPanel verifyLablePanel = new JPanel();
	private final JButton login = new JButton("Login");
	private final JTextField username = new JTextField();
	private final JPasswordField password = new JPasswordField();
	private final JLabel usn = new JLabel("Benutzername:");
	private final JLabel pw = new JLabel("Passwort:");
	private final JFrame verify = new JFrame();
	
	private void verbindungAufbauen() {
		setEnabled(false);
		
		verifyPanel.setLayout(new BorderLayout(5, 5));
		verifyTextPanel.setLayout(new GridLayout(2, 1));
		verifyButtonPanel.setLayout(new GridLayout(1, 1));
		verifyLablePanel.setLayout(new GridLayout(2,1));
		
		verify.add(verifyPanel, BorderLayout.NORTH);
		verifyPanel.add(verifyLablePanel, BorderLayout.WEST);
		verifyPanel.add(verifyTextPanel, BorderLayout.CENTER);
		verifyPanel.add(verifyButtonPanel, BorderLayout.EAST);
		
		verifyLablePanel.add(usn);
		verifyLablePanel.add(pw);
		
		verifyTextPanel.add(username);
		verifyTextPanel.add(password);
		
		verifyButtonPanel.add(login);
		
		verify.setVisible(true);
		verify.pack();
		
		ActionListener veri = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verify.setEnabled(false);
				verify.setVisible(false);
				setEnabled(true);
				publicIn.setEnabled(true);
				publicSendenButton.setEnabled(true);
			}
		};
		login.addActionListener(veri);
	}
	
	
	
	private void sayPublic(String input) {
		//Public-Chathistory wird später anders mit dem Server Gehändelt
		publicOut.setText(publicOut.getText() + "\n" + id + ": " + input);
	}
	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UI frame = new UI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

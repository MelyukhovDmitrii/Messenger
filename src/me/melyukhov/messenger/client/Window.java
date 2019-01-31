package me.melyukhov.messenger.client;

import java.awt.BorderLayout;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Window extends JFrame {
	private static final long serialVersionUID = -8839548662369332061L;
	
	private JTextArea messages;
	private JTextField message;
	private JTextField user;
	private Client client;
	private JButton send;

	private boolean state = false;

	public Window() throws NoSuchAlgorithmException, NoSuchPaddingException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		JPanel output = new JPanel();
		this.message = new JTextField(20);
		this.user = new JTextField(10);
		send = new JButton("Send");
		message.requestFocusInWindow();
		output.add(user);
		output.add(message);
		output.add(send);
		messages = new JTextArea();
		messages.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(messages);
		scrollPane.setBounds(10,60,780,500);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(BorderLayout.CENTER, scrollPane);
		getContentPane().add(BorderLayout.SOUTH, output);	
		setVisible(true);
		send.addActionListener((e)->{
			if(user.getText() != "") {
				if(user.getText() != client.getUserName()) {
					client.registration(user.getText());	
				}
				if(message.getText() != "") {
					client.sendStringMessage(message.getText());
					message.setText("");
				}
			}
		});
		setStateWindow(false);
		client = new Client();
		setStateWindow(true);
	}

	public void setStateWindow(boolean state){
		message.setEditable(state);
		user.setEditable(state);
		send.setEnabled(state);
	}

	public void sendMessageToWindow(String message) {
		messages.append(message);
	}
	
}

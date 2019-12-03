package br.edu.univas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtSenha;

	public Login() {
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setBounds(10, 75, 46, 14);
		contentPane.add(lblUsurio);
		
		txtUser = new JTextField();
		txtUser.setBounds(66, 72, 337, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 119, 46, 14);
		contentPane.add(lblSenha);
		
		JButton EnterButton = new JButton("Entrar");
		EnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtSenha.getText().equals("Admin")&&txtUser.getText().equals("Admin")) {
					PrincipalFrame frame = new PrincipalFrame();
					frame.setVisible(true);dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Usuário ou Senha INCORRETOS!");
					txtUser.requestFocus();
					txtUser.setText(null);
					txtSenha.setText(null);
				}
			}
		});
		EnterButton.setBounds(66, 179, 337, 23);
		contentPane.add(EnterButton);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(66, 116, 337, 20);
		contentPane.add(txtSenha);
	}
}

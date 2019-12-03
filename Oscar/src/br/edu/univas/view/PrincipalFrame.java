package br.edu.univas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PrincipalFrame extends JFrame {

	private JPanel contentPane;
	private JPanel centerPanel;
	private VehicleList listPanel;
	private AddVehicle addPanel;
	
	public PrincipalFrame() {
		setSize(800, 640);
		setTitle("Cadastro de Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initialize();
	}
	
	private void initialize() {
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		try {
			addPanel = new AddVehicle();
			listPanel = new VehicleList();
			listPanel.updateTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		createNorthPanel();
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(listPanel);
		contentPane.add(centerPanel, BorderLayout.CENTER);
	}
	
	private void createNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(800, 140));
		northPanel.setBackground(Color.GRAY);
		
		JButton addButton = new JButton();
		addButton.setPreferredSize(new Dimension(100, 100));
		ImageIcon image1 = new ImageIcon(getClass().getResource("/car.png"));
		addButton.setIcon(image1);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.removeAll();
				centerPanel.add(addPanel);
				centerPanel.revalidate();
				PrincipalFrame.this.repaint();
			}
		});
		northPanel.add(addButton);
		
		JButton listButton = new JButton();
		listButton.setPreferredSize(new Dimension(100, 100));
		ImageIcon image2 = new ImageIcon(getClass().getResource("/list.png"));
		listButton.setIcon(image2);
		listButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listPanel.updateTable();
				centerPanel.removeAll();
				centerPanel.add(listPanel);
				centerPanel.revalidate();
				PrincipalFrame.this.repaint();
			}
		});
		northPanel.add(listButton);
		
		contentPane.add(northPanel, BorderLayout.NORTH);
	}
	
}

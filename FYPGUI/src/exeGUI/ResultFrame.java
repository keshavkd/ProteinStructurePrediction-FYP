package exeGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.ldap.ControlFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.util.*;
import java.io.*;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ResultFrame extends JFrame {

	private JPanel contentPane;
	public static String choice, outputString;
	public static float[] featureVector;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultFrame frame = new ResultFrame(choice, featureVector, outputString);
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ResultFrame(String choice, float[] featureVector, String outputString) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 450, 510);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel predLabel = new JLabel("Prediction");
		predLabel.setForeground(Color.WHITE);
		predLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		predLabel.setBounds(175, 11, 103, 30);
		contentPane.add(predLabel);
		
		JButton button = new JButton("Back");
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLUE);
		button.setBounds(175, 411, 93, 30);
		contentPane.add(button);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlPane cp = new ControlPane();
				cp.setVisible(true);
				cp.setResizable(false);
				dispose();
			}
		});
		
		JLabel labelFV = new JLabel("Feature Vector");
		labelFV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelFV.setForeground(Color.WHITE);
		labelFV.setBounds(175, 59, 93, 14);
		contentPane.add(labelFV);
		
		JLabel labelRes = new JLabel("Result(s)");
		labelRes.setForeground(Color.WHITE);
		labelRes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelRes.setBounds(187, 225, 67, 14);
		contentPane.add(labelRes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(65, 84, 310, 130);
		contentPane.add(scrollPane);
		
		JTextArea fvText = new JTextArea();
		scrollPane.setViewportView(fvText);
		fvText.setForeground(Color.WHITE);
		fvText.setBackground(Color.GRAY);
		fvText.setLineWrap(true);
		fvText.setWrapStyleWord(true);
		
		fvText.setText(Arrays.toString(featureVector));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(65, 250, 310, 150);
		contentPane.add(scrollPane_1);
		
		JTextArea resText = new JTextArea();
		scrollPane_1.setViewportView(resText);
		resText.setForeground(Color.WHITE);
		resText.setBackground(Color.GRAY);
		resText.setText(outputString);
		
	}
}

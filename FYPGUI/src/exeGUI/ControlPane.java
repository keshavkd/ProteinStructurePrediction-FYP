package exeGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import java.util.*;
import java.io.*;
import java.util.regex.*;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class ControlPane extends JFrame {

	private JPanel contentPane;
	public String choice, seqProtein, outputLine, outputString = "", stringFV;
	public boolean flag = true, invalid = true;
	public float[] actFV;
	String theDirectory = "C:\\Users\\Crytek\\Python Classifiers";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlPane frame = new ControlPane();
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
	public ControlPane() {
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 450, 510);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(116, 275, 190, 93);
		panel.setBackground(Color.DARK_GRAY);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel);
		
		JRadioButton btnNB = new JRadioButton("Naive Bayesian");
		btnNB.setBackground(Color.DARK_GRAY);
		btnNB.setForeground(Color.WHITE);
		panel.add(btnNB);
		
		JRadioButton btnSVM = new JRadioButton("Support Vector Machine");
		btnSVM.setBackground(Color.DARK_GRAY);
		btnSVM.setForeground(Color.WHITE);
		panel.add(btnSVM);
		
		JRadioButton btnRF = new JRadioButton("Random Forest");
		btnRF.setBackground(Color.DARK_GRAY);
		btnRF.setForeground(Color.WHITE);
		panel.add(btnRF);
		
		JLabel titlePSP = new JLabel("Protein Structure Prediction");
		titlePSP.setBounds(94, 31, 259, 22);
		titlePSP.setForeground(Color.WHITE);
		titlePSP.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPane.add(titlePSP);
		
		JButton btnPredict = new JButton("Predict");
		btnPredict.setBounds(91, 379, 105, 31);
		btnPredict.setForeground(Color.WHITE);
		btnPredict.setBackground(new Color(0, 0, 255));
		contentPane.add(btnPredict);
		
		JButton btnPredictAll = new JButton("Predict All");
		btnPredictAll.setBounds(226, 379, 105, 31);
		btnPredictAll.setForeground(new Color(255, 255, 255));
		btnPredictAll.setBackground(new Color(139, 0, 0));
		contentPane.add(btnPredictAll);
		
		JLabel seqInfoText = new JLabel("Enter sequence");
		seqInfoText.setForeground(Color.WHITE);
		seqInfoText.setBounds(116, 250, 113, 14);
		contentPane.add(seqInfoText);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(94, 64, 237, 178);
		contentPane.add(scrollPane);
		
		JTextArea inpTF = new JTextArea();
		inpTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(inpTF);
		inpTF.setLineWrap(true);
		inpTF.setWrapStyleWord(true);
		
		JTextArea infoDetail = new JTextArea();
		infoDetail.setBackground(Color.DARK_GRAY);
		infoDetail.setForeground(Color.WHITE);
		infoDetail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		infoDetail.setText("Check one of the buttons or use \"Predict All\" instead.");
		infoDetail.setBounds(94, 419, 237, 40);
		contentPane.add(infoDetail);
		seqInfoText.setVisible(false);
		infoDetail.setVisible(false);
		infoDetail.setLineWrap(true);
		infoDetail.setWrapStyleWord(true);
		
		btnNB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoDetail.setVisible(false);
				if(btnNB.isSelected()) {
					choice = "NaiveBayesian";
					btnRF.setSelected(false);
					btnSVM.setSelected(false);
				}
			}
		});
		
		btnSVM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoDetail.setVisible(false);
				if(btnSVM.isSelected()) {
					choice = "SupportVectorMachine";
					btnRF.setSelected(false);
					btnNB.setSelected(false);
				}
			}
		});
		
		btnRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoDetail.setVisible(false);
				if(btnRF.isSelected()) {
					choice = "RandomForest";
					btnNB.setSelected(false);
					btnSVM.setSelected(false);
				}
			}
		});
		
		btnPredict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Pattern.matches("\\s+", inpTF.getText()) || inpTF.getText().equals("")) {
					seqInfoText.setText("Enter sequence");
					seqInfoText.setVisible(true);
					flag = false;
				}
				else if(!Pattern.matches("[a-zA-Z&&[^bjozBJOZ]]{20,1231}", inpTF.getText())) {
					seqInfoText.setText("Invalid sequence!");
					seqInfoText.setVisible(true);
					flag = false;
				}
				else
					flag = true;
				
				if(!btnNB.isSelected() && !btnSVM.isSelected() && !btnRF.isSelected()) {
					if(flag = true)
						seqInfoText.setVisible(false);
					infoDetail.setVisible(true);
					flag = false;
				}
				
				if(flag){
					seqProtein = inpTF.getText();
					Maps newSequence = new Maps(seqProtein);
					actFV = newSequence.VectorFormFunction();
					stringFV = Arrays.toString(actFV);
					stringFV = stringFV.replaceAll(" ", "");
//					System.out.println(stringFV);
					try {
						Process pythonProcess = Runtime.getRuntime().exec("cmd /c python " + choice + ".py " + stringFV, null, new java.io.File(theDirectory));
						BufferedReader stdInput = new BufferedReader(new InputStreamReader(pythonProcess.getInputStream()));
				        while ((outputLine = stdInput.readLine()) != null) {
				        	outputString += outputLine + "\n";
				        }
					} catch(IOException exceptionE) {
						exceptionE.printStackTrace();
					}
					ResultFrame rf = new ResultFrame(choice, actFV, outputString);
					rf.setVisible(true);
					dispose();
				}
			}
		});
		
		btnPredictAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Pattern.matches("\\s+", inpTF.getText()) || inpTF.getText().equals("")) {
					seqInfoText.setText("Enter sequence");
					seqInfoText.setVisible(true);
					flag = false;
				}
				
				else if(!Pattern.matches("[a-zA-Z&&[^bjozBJOZ]]{20,1231}", inpTF.getText())) {
					seqInfoText.setText("Invalid sequence!");
					seqInfoText.setVisible(true);
					flag = false;
				}
				else
					flag = true;
				if(flag) {
					choice = "ALL";
					seqProtein = inpTF.getText();
					Maps newSequence = new Maps(seqProtein);
					actFV = newSequence.VectorFormFunction();
					stringFV = Arrays.toString(actFV);
					stringFV = stringFV.replaceAll(" ", "");
					try {
						Process pythonProcess = Runtime.getRuntime().exec("cmd /c python " + choice + ".py " + stringFV, null, new java.io.File(theDirectory));
						BufferedReader stdInput = new BufferedReader(new InputStreamReader(pythonProcess.getInputStream()));
				        while ((outputLine = stdInput.readLine()) != null) {
				        	outputString += outputLine + "\n";
				        }
					} catch(IOException exceptionE) {
						exceptionE.printStackTrace();
					}
					ResultFrame rf = new ResultFrame(choice, actFV, outputString);
					rf.setVisible(true);
					rf.setResizable(false);
					dispose();
				}
			}
		});
	}
}

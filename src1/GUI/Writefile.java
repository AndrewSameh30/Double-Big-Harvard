package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.controller;
import ProjectWithGui.*;

public class Writefile   extends JFrame implements ActionListener  {
	private JButton x1;
	private controller controller;
	private JPanel panel ;
	public controller getController() {
	return controller;
}
	private JTextArea labelText = new JTextArea();

public void setController(controller controller) {
	this.controller = controller;
}

 

		//JLabel label1;
		 public Writefile(controller s){
			 controller=s;
	        x1 = new JButton ();
	        x1.addActionListener(this);
	        x1.setText(" run ");
	        x1.setFocusable(false);
	        x1.setFont(new Font ("Comic Sans",Font.BOLD,20));
	        x1.setBorder(BorderFactory.createEtchedBorder());
		    x1.setIconTextGap(-15);
			x1.setForeground(Color.black);
			x1.setBackground(Color.LIGHT_GRAY);
			x1.setBounds(300, 620, 200, 50);
	        x1.setVisible(true);
			
			
			
			labelText.setOpaque(true);
			labelText.setBackground(Color.LIGHT_GRAY);
		//	labelText.setFont(new Font("ALGERIAN",Font.BOLD,8));
     	//	labelText.setBounds(0,0,800,600);
			labelText.setText("enter your code");;
			labelText.setFont(new Font("Times New Roman", Font.BOLD, 17));
			labelText.setLineWrap(true);       // wrap line
			labelText.setWrapStyleWord(true);  // wrap line at word boundary
		//	labelText.setBackground(new Color(204, 238, 241)); // light blue
		      // Wrap the JTextArea inside a JScrollPane
		      JScrollPane tAreaScrollPane = new JScrollPane(labelText);
		      tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		      tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		      tAreaScrollPane.setBounds(0,0,800,550);		      
		    //  panel.setBackground(Color.black);
		    
			
			this.setTitle("Double Big Harvard");
			this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
			this.setResizable(true);
			this.setSize(800,730);
			this.setVisible(true);
			this.setLocation(new Point(350,50));
			this.setLayout(null);
			ImageIcon image1 = new ImageIcon("2.png");
			this.setIconImage(image1.getImage());
		
			this.add(tAreaScrollPane);
		
			this.add(x1);



		
			
		}
		 
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==x1) {
			
			File f = new File("output.txt");
			try {
			    FileWriter fw = new FileWriter(f);
			    fw.write(labelText.getText());
			    fw.flush();
			    fw.close();
			} catch (IOException e1) {
			    e1.printStackTrace();
			}
			
			processor x =new processor();
			x.setFilename("output.txt");
			
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			// IMPORTANT: Save the old System.out!
			PrintStream old = System.out;
			// Tell Java to use your special stream
			System.setOut(ps);
			// Print some output: goes to your special stream
			x.processor();
			// Put things back
			System.out.flush();
			System.setOut(old);
			// Show what happened
			this.dispose();
			getController().tooutputview(baos.toString());

		}
			
			
		}
	} 
	
	



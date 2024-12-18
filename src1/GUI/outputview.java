package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.controller;

public class outputview extends JFrame implements ActionListener  {
	public  void setLabelText(String x) {
		this.labelText.setText(x);
	}

	private JTextArea labelText = new JTextArea();

	private controller controller;
		public controller getController() {
		return controller;
	}
	public void setController(controller controller) {
		this.controller = controller;
	}



	
	//JLabel label1;
	public outputview(controller s){
		controller=s;
		 
	        
			
			
			
		
			
			
			
		//	labelText.setForeground(Color.RED);
			labelText.setOpaque(true);
			labelText.setBackground(Color.LIGHT_GRAY);
		//	labelText.setFont(new Font("ALGERIAN",Font.BOLD,8));
			labelText.setBounds(0,0,800,730);
			
			labelText.setFont(new Font("Serif", Font.ITALIC, 13));
			labelText.setLineWrap(true);       // wrap line
			labelText.setWrapStyleWord(true);  // wrap line at word boundary
		//	labelText.setBackground(new Color(204, 238, 241)); // light blue
		      // Wrap the JTextArea inside a JScrollPane
		      JScrollPane tAreaScrollPane = new JScrollPane(labelText);
		      tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		      tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			labelText.setEditable(false);
			 
			this.add(tAreaScrollPane, BorderLayout.CENTER);
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
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

	
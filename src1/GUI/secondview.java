package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import controller.controller;

public class secondview   extends JFrame implements ActionListener  {
	private JButton x1;
	private JButton x2;

	private controller controller;
		public controller getController() {
		return controller;
	}
	public void setController(controller controller) {
		this.controller = controller;
	}



	//JLabel label1;
	public secondview(controller s){
		controller=s;
		  x1 = new JButton ();
	        x1.addActionListener(this);
	        x1.setText(" Write Code ");
	        x1.setFocusable(false);
	        x1.setFont(new Font ("Comic Sans",Font.BOLD,15));
	        x1.setBorder(BorderFactory.createEtchedBorder());
		    x1.setIconTextGap(-15);
			x1.setForeground(Color.black);
			x1.setBackground(Color.LIGHT_GRAY);
			x1.setBounds(150, 300 , 150, 50);
	        
			
			x2 = new JButton ();
	        x2.addActionListener(this);
	        x2.setText(" Enter A File Name ");
	        x2.setFocusable(false);
	        x2.setFont(new Font ("Comic Sans",Font.BOLD,15));
	        x2.setBorder(BorderFactory.createEtchedBorder());
		    x2.setIconTextGap(-15);
			x2.setForeground(Color.black);
			x2.setBackground(Color.LIGHT_GRAY);
			x2.setBounds(500, 300, 150, 50);
			
			
			JLabel backgroundImage= new JLabel();
			backgroundImage.setOpaque(true);
			ImageIcon backGround = new ImageIcon("2.png");
			backgroundImage.setIcon(backGround);
			//backgroundImage.setBackground(Color.RED);
			backgroundImage.setBounds(0,0,800,730);
			
			
			JLabel labelText1= new JLabel();
			labelText1.setText(" HELLO TO OUR PROGRAM ");
			labelText1.setForeground(Color.RED);
			labelText1.setOpaque(true);
			labelText1.setBackground(Color.white);
			labelText1.setFont(new Font("ALGERIAN",Font.BOLD,15));
			labelText1.setBounds(300,100,200,40);
			
			
			JLabel labelText= new JLabel();
			labelText.setText("WOULD YOU LIKE TO ENTER A FILE NAME EXISTS OR YOU WANT TO WRITE CODE ?");
			labelText.setForeground(Color.RED);
			labelText.setOpaque(true);
			labelText.setBackground(Color.white);
			labelText.setFont(new Font("ALGERIAN",Font.BOLD,15));
			labelText.setBounds(100,150,600,40);
			
			
			
			
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBounds(0,0,800,750);
			layeredPane.add(backgroundImage, Integer.valueOf(0));
			layeredPane.add(labelText, Integer.valueOf(1));
			layeredPane.add(x1, Integer.valueOf(1));
			layeredPane.add(x2, Integer.valueOf(2));
			layeredPane.add(labelText1, Integer.valueOf(2));

			
			this.setTitle("Double Big Harvard");
			this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
			this.setResizable(true);
			this.setSize(800,730);
			this.setVisible(true);
			this.setLocation(new Point(350,50));
			this.setLayout(null);
			ImageIcon image1 = new ImageIcon("2.png");
			this.setIconImage(image1.getImage());
		
			this.add(layeredPane);
		}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==x2) {
			this.dispose();
			getController().tofileview();
		}
		if(e.getSource()==x1) {
			this.dispose();
			getController().towritefile();
		}
		
	} 
	

}

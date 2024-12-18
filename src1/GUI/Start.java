package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import controller.controller;

public class Start   extends JFrame implements ActionListener  {
	private JButton x1;
	private controller controller;
 

		//JLabel label1;
		 public Start(controller s){
			 controller=s;
	        x1 = new JButton ();
	        x1.addActionListener(this);
	        x1.setText(" Start ");
	        x1.setFocusable(false);
	        x1.setFont(new Font ("Comic Sans",Font.BOLD,20));
	        x1.setBorder(BorderFactory.createEtchedBorder());
		    x1.setIconTextGap(-15);
			x1.setForeground(Color.black);
			x1.setBackground(Color.LIGHT_GRAY);
			x1.setBounds(300, 615, 200, 50);
	        
			JLabel backgroundImage= new JLabel();
			backgroundImage.setOpaque(true);
			ImageIcon backGround = new ImageIcon("2.png");
			backgroundImage.setIcon(backGround);
			//backgroundImage.setBackground(Color.RED);
			backgroundImage.setBounds(0,0,800,730);
			
			
			JLabel labelText= new JLabel();
			labelText.setText(" D  B  H ");
			labelText.setForeground(Color.RED);
			labelText.setFont(new Font("ALGERIAN",Font.BOLD,80));
			labelText.setBounds(260,320,400,400);
			
			JLabel labelText2= new JLabel();
			//labelText.setOpaque(true);
			labelText2.setText("Double Big Harvard Architercture");
			labelText2.setForeground(Color.RED);
			labelText2.setFont(new Font("ALGERIAN",Font.BOLD,25));
			labelText2.setBounds(170,370,500,400);
			
			
			
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBounds(0,0,800,750);
			layeredPane.add(backgroundImage, Integer.valueOf(0));
			layeredPane.add(labelText, Integer.valueOf(1));
			layeredPane.add(labelText2, Integer.valueOf(2));
			layeredPane.add(x1, Integer.valueOf(1));
			
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
		 
	
	public controller getController() {
			return controller;
		}
		public void setController(controller controller) {
			this.controller = controller;
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==x1) {
			
			this.dispose();
			getController().tosecondview();
		}
	} 
	
	

}

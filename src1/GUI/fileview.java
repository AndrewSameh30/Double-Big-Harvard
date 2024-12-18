package GUI;
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
import java.io.PrintStream;
import java.util.concurrent.Flow.Processor;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import controller.controller;
import ProjectWithGui.*;
public class fileview  extends JFrame implements ActionListener{

	

		private JButton x1;
		private controller controller;
		private JTextField filename;
		private processor processor;
		private JLabel labelText2= new JLabel();


			//JLabel label1;
			 public fileview(controller s){
				 controller=s;
		        x1 = new JButton ();
		        x1.addActionListener(this);
		        x1.setText(" Run The File ");
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
				
				
				filename = new JTextField();
				filename.setPreferredSize(new Dimension(100,200));
				filename.setText("file name");
				filename.setCaretColor(Color.white);
				filename.setFont(new Font("Consolas" , Font.BOLD, 15));
				filename.setForeground(Color.DARK_GRAY);
				filename.setBounds(100,180,250,50);
				
				
				//labelText.setOpaque(true);
				labelText2.setText(" * the file doesnot exist write the file as (Anything.txt) and must be exist in the project *");
				labelText2.setForeground(Color.RED);
				labelText2.setOpaque(true);
				labelText2.setBackground(Color.white);
				labelText2.setFont(new Font("ALGERIAN",Font.BOLD,12));
				labelText2.setBounds(100,240,600,20);
				labelText2.setVisible(false);
				
				JLabel labelText= new JLabel();
				//labelText.setOpaque(true);
				labelText.setText("Enter The File Name :");
				labelText.setForeground(Color.RED);
				labelText.setOpaque(true);
				labelText.setBackground(Color.white);
				labelText.setFont(new Font("ALGERIAN",Font.BOLD,20));
				labelText.setBounds(100,100,230,40);
				
				
				
				JLayeredPane layeredPane = new JLayeredPane();
				layeredPane.setBounds(0,0,800,750);
				layeredPane.add(backgroundImage, Integer.valueOf(0));
				layeredPane.add(labelText, Integer.valueOf(2));
				layeredPane.add(labelText2, Integer.valueOf(2));
				layeredPane.add(x1, Integer.valueOf(1));
				layeredPane.add(filename, Integer.valueOf(2));

				
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
				codeparser.readFile(filename.getText());
				if (codeparser.getCheckerror()==1) {
					labelText2.setVisible(true);

				}
				else {
				processor x =new processor();
				x.setFilename(filename.getText());
				
				
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
		
		

	
}

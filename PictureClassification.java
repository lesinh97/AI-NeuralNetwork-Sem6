package NN;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;


public class PictureClassification {
	public JFrame frame;
	public Paint paint;
	Perceptron pct;
	public PictureClassification()
	{	
		pct=new Perceptron(Paint.DEFAULT_SIZE.width, Paint.DEFAULT_SIZE.height);
		
		this.frame = new JFrame("No ron nhan tao!");
		this.frame.setSize(600 ,300);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(null);
		
		paint= new Paint();
		paint.setBounds(50, 0, Paint.DEFAULT_SIZE.width, Paint.DEFAULT_SIZE.height);
		this.frame.getContentPane().add(paint);
		
		final JLabel l1=new JLabel("0");
		l1.setBounds(540, 30, 100, 25);
		this.frame.add(l1);
		
		JButton Learn=new JButton("Class 1");
		Learn.setBounds(410, 30, 100, 25);
		Learn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pct.learning(paint.image, 0);
				//paint.clear();
				l1.setText(""+pct.class1);
			}
		});
		this.frame.add(Learn);
		
		final JLabel l2=new JLabel("0");
		l2.setBounds(540, 70, 100, 25);
		this.frame.add(l2);
		
		JButton Learn2=new JButton("Class 2");
		Learn2.setBounds(410, 70, 100, 25);
		Learn2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pct.learning(paint.image, 1);
				//paint.clear();
				l2.setText(""+pct.class2);
			}
		});
		this.frame.add(Learn2);
		
		final JLabel l3=new JLabel("0");
		l3.setBounds(540, 120, 200, 25);
		this.frame.add(l3);
		
		JButton Test=new JButton("Classification");
		Test.setBounds(410, 120, 125, 25);
		Test.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				double o=pct.output(paint.image);
				//l3.setText(""+o);
				if (o<0.5) l3.setText("Class 1!");
				else l3.setText("Class 2!");
			}
		});
		this.frame.add(Test);
		
		JButton Cl=new JButton("Clear");
		Cl.setBounds(410, 200, 75, 25);
		Cl.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				paint.clear();
				l3.setText("");
			}
		});
		this.frame.add(Cl);
		
		frame.setVisible(true);
	}
	
	
	public static void main(String args[]){
		try { UIManager.setLookAndFeel(
				UIManager.getSystemLookAndFeelClassName()); }
			catch (Exception e) { e.printStackTrace(); }
		new PictureClassification();
	}
}

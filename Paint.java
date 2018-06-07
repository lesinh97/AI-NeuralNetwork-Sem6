

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;

public class Paint extends JPanel implements MouseListener,
                                             MouseMotionListener
{
	public static final Dimension DEFAULT_SIZE = new Dimension(300, 300);
	public BufferedImage image;
	private Graphics graphics;
	private Point startPoint;
	
	public Paint()
	{
		setPreferredSize(DEFAULT_SIZE);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		image = new BufferedImage(
				DEFAULT_SIZE.width, DEFAULT_SIZE.height, BufferedImage.TYPE_3BYTE_BGR);
		graphics = image.getGraphics();
		clear();
	}
	
	public void clear(){
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		graphics.setColor(Color.BLACK);
		repaint();
	
	}
	@Override public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(image, 0, 0, this);
	}
	
	@Override public void mousePressed(MouseEvent e)
	{
		Point p = e.getPoint();
		graphics.fillOval(p.x-10, p.y-10, 20, 20);
		repaint();
		startPoint = p;
	}
	
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	
	@Override public void mouseDragged(MouseEvent e)
	{
		Point p = e.getPoint();
		graphics.fillOval(p.x-10, p.y-10, 20, 20);
		//graphics.drawLine(startPoint.x, startPoint.y, p.x, p.y);
		repaint();
		startPoint = p;
	}
	
	@Override public void mouseMoved(MouseEvent e) {}
	
	public void saveImage()
	{
		JFileChooser fileDialog = new JFileChooser();
		int state = fileDialog.showSaveDialog(this);
		if (state != JFileChooser.APPROVE_OPTION)
			return;
		
		File file = fileDialog.getSelectedFile();
		String fileName = file.getName();
		if (!fileName.endsWith(".png"))
			file = new File(file.getParent(), fileName + ".png");
		
		try { ImageIO.write(image, "png", file); }
		catch (Exception e) { e.printStackTrace(); }
	}

	
}

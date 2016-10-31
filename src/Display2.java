import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Display2 extends JFrame implements ActionListener {
	private JMenuBar menu;
	private JMenu menu1;
	private JMenuItem item1;
	private JMenuItem item2;
	private ArrayList<BoxWord> box;
	int x, y;
	JLabel jLabel;
	PicturePanel panel;

	public Display2(ArrayList<String> array) {
		menu = new JMenuBar();
		menu1 = new JMenu("Select Option");
		item1 = new JMenuItem("Add Word");
		item2 = new JMenuItem("Quit");

		item1.addActionListener(this);
		item2.addActionListener(this);
		
		menu1.add(item1);
		menu1.add(item2);
		
		menu.add(menu1);
		
		setJMenuBar(menu);

		box = new ArrayList<BoxWord>();

		Random random = new Random();
		this.setSize(550, 550);
		this.setTitle("Word Drag 2015");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new PicturePanel();
		panel.setLayout(null);
		
		for (int i = 0; i < array.size(); i++) {
			
			box.add(new BoxWord());
			int x = random.nextInt(475);
			int y = random.nextInt(475);

			box.get(i).setText(array.get(i));
			box.get(i).setBackground(Color.pink);
			Dimension size = box.get(i).getPreferredSize();

			box.get(i).setBounds(x, y, size.width+6, size.height+6);
			System.out.println(x + ", " + y);
			box.get(i).setOpaque(true);
			box.get(i).setBorder(BorderFactory.createRaisedBevelBorder());
			
			panel.add(box.get(i));

		}
		
		this.add(panel);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		final ArrayList<String> list = new ArrayList<>(Arrays.asList("Java","Red","Drad","Sad","Cloudy","Foot","Dopey","J2EE","Great"
				,"Hands","Word","Through","Face","Run","Skippy", "WASD 5.1","Boca Juniors","Xeneizes"));

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Display2(list);
			}
		});

	}

	class BoxWord extends JLabel implements MouseListener, MouseMotionListener {

		private Point initialLoc;
		private Point initialLocOnScreen;

		public BoxWord() {
			addMouseMotionListener(this);
			addMouseListener(this);

		}

		@Override
		public void mouseDragged(MouseEvent e) {
	
			Component comp = (Component) e.getSource();
//			comp.requestFocusInWindow();
			Point locOnScreen = e.getLocationOnScreen();

			int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
			int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
			comp.setLocation(x, y);

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			Component comp = (Component) arg0.getSource();
			if (arg0.getClickCount() == 2) {
				int output = JOptionPane.showConfirmDialog(this
			               , "Remove " + this.getText() + " ?"
			               ,"Word Remove Dialog"
			               ,JOptionPane.OK_CANCEL_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
				if (output == JOptionPane.YES_OPTION) {
					Container parent = this.getParent();
					parent.remove(this);
					parent.validate();
					parent.repaint();
				}
					
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			//
    
			panel.setComponentZOrder(this, 0);
			Component comp = (Component) arg0.getSource();
			this.setBackground(Color.yellow);
			comp.requestFocusInWindow();
			initialLoc = comp.getLocation();
			initialLocOnScreen = arg0.getLocationOnScreen();

		}

		@Override
		public void mouseReleased(MouseEvent e) {

			Component comp = (Component) e.getSource();
			Point locOnScreen = e.getLocationOnScreen();
			this.setBackground(Color.pink);
			int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
			int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
			comp.setLocation(x, y);

		}

	}

	
	class PicturePanel extends JPanel
	{
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == item1) {
			String output = JOptionPane.showInputDialog("New Word Entry", "Enter your word");
			BoxWord temp = new BoxWord();
			temp.setText(output);
			box.add(temp);
			
			
			temp.setBackground(Color.pink);

			Dimension size = temp.getPreferredSize();
			temp.setBounds(x, y, size.width+6, size.height+6);
			
			temp.setOpaque(true);
			temp.setBorder(BorderFactory.createRaisedBevelBorder());
			
			
			panel.add(temp);
			Container parent = temp.getParent();
			
			parent.validate();
			parent.repaint();
		}
			if (arg0.getSource() == item2) {
				System.exit(0);
		}
		
	}

}

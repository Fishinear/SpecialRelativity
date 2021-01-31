package specialRelativity;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame
{
	private DrawingPanel panel;

	public MainFrame()
	{
		super();

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.lightGray);

		panel = new DrawingPanel();
		panel.setBackground(Color.white);

		contentPane.add(panel);

		setTitle("Einstein spherical light wave");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_VERT);
		setSize(800, 800);
		setVisible(true);

	}

	public static void main( String[] args )
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				new MainFrame();
			}
		});
	}

}

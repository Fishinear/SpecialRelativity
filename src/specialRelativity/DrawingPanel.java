package specialRelativity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel
{
	private static final double c = 300; // light speed
	private static final double v = 0.8 * c; // speed of moving frame
	private static final double gamma = 1 / Math.sqrt(1 - v * v / (c * c)); // Lorentz factor
	private static final double timeStep = 0.003;
	private static final double angleStep = 0.05;

	private static final double dotSize = 4;

	private static int doubleCompare( double a, double b, double acc )
	{
		double diff = a - b;
		if ( diff < -acc ) {
			return -1;
		} else if ( diff > acc ) {
			return 1;
		} else {
			return 0;
		}
	}

	private static Color colorForTime( double t )
	{
		if ( doubleCompare(t, 1, 0.01) == 0 ) {
			return Color.black;
		} else {
			return new Color(Color.HSBtoRGB((float) t, 1, 1));
		}
	}

	@Override
	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		g2.translate(getWidth() / 2, getHeight() / 2);

		// for a number of different t in the stationary frame
		for ( double t = 0; t <= 4; t += timeStep ) {

			// for a number of radiation angles in the stationary frame
			for ( double angle = 0; angle < 360; angle += angleStep ) {

				// calculate x, y of the photon in the stationary frame
				double x = Math.sin(angle * Math.PI / 180) * c * t;
				double y = Math.cos(angle * Math.PI / 180) * c * t;

				// Lorentz transform to xm, ym, and tm in the moving frame
				double tm = gamma * (t - v * x / (c * c));
				double xm = gamma * (x - v * t);
				double ym = y;

				// plot the result
				g2.setColor(colorForTime(tm));
				drawDot(g2, xm, ym);
			}
		}
	}

	private static void drawDot( Graphics2D g2, double xm, double ym )
	{
		g2.fill(new Ellipse2D.Double(xm - dotSize / 2, ym - dotSize / 2, dotSize, dotSize));
	}

}

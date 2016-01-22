package com.sudestwebdesign.steeve.life.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.sudestwebdesign.steeve.life.JuliaFrame;


public class JuliaPanel extends JPanel implements MouseListener
{
	public JuliaPanel(double xValue, double yValue, JuliaFrame parent)
	{
		this.xValue = xValue;
		this.yValue = yValue;
		this.parent = parent;
		createComponents();
		setProperties();
	}

	public void update()
	{
		repaint();
		parent.updateTitle();
	}

	public void setZoom(double zoom)
	{
		this.zoom = zoom;
		update();
	}

	public void setXCenter(double xCenter)
	{
		this.xCenter = xCenter;
		update();
	}

	public void setYCenter(double yCenter)
	{
		this.yCenter = yCenter;
		update();
	}

	public void setXValue(double xValue)
	{
		this.xValue = xValue;
		update();
	}

	public void setYValue(double yValue)
	{
		this.yValue = yValue;
		update();
	}

	public double getZoom()
	{
		return zoom;
	}

	public double getXCenter()
	{
		return xCenter;
	}

	public double getYCenter()
	{
		return yCenter;
	}

	public double getXValue()
	{
		return xValue;
	}

	public double getYValue()
	{
		return yValue;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		xCenter = (1.0 * e.getX() / getWidth() - 0.5) * zoomWidth + xCenter;
		yCenter = (1.0 * e.getY() / getHeight() - 0.5) * zoomHeight + yCenter;

		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		int min = Math.min(getWidth(), getHeight());
		zoomWidth = zoom * getWidth() / min;
		zoomHeight = zoom * getHeight() / min;

		for (double cx = xCenter - zoomWidth / 2; cx < xCenter + zoomWidth / 2; cx += zoomWidth
				/ getWidth())
		{
			for (double cy = yCenter - zoomHeight / 2; cy < yCenter
					+ zoomHeight / 2; cy += zoomHeight / getHeight())
			{
				double zx = cx, zy = cy;

				for (int i = 0; i < iterations; i++)
				{
					double zxt = zx * zx - zy * zy + xValue;
					double zyt = 2 * zx * zy + yValue;

					zx = zxt;
					zy = zyt;

					if (zx * zx + zy * zy > 4.0)
					{
						g.setColor(Color.getHSBColor((float) (0.005 * i),
								(float) 1.0, (float) 0.5));
						g.drawLine((int) ((cx - xCenter + zoomWidth / 2)
								* getWidth() / zoomWidth),
								(int) ((cy - yCenter + zoomHeight / 2)
										* getHeight() / zoomHeight), (int) ((cx
										- xCenter + zoomWidth / 2)
										* getWidth() / zoomWidth), (int) ((cy
										- yCenter + zoomHeight / 2)
										* getHeight() / zoomHeight));
						break;
					}
				}
			}
		}
	}

	private void createComponents()
	{
		xCenter = 0;
		yCenter = 0;
		zoom = ZOOM;

		iterations = ITERATIONS;
	}

	private void setProperties()
	{
		addMouseListener(this);
	}

	private double xCenter, yCenter, xValue, yValue, zoom, zoomWidth,
			zoomHeight;
	private int iterations;

	private JuliaFrame parent;

	public static final double ZOOM = 4.0;
	public static final int ITERATIONS = 200;

	private static final long serialVersionUID = 3L;
}

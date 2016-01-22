package com.sudestwebdesign.steeve.life;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sudestwebdesign.steeve.life.panel.JuliaPanel;
import com.sudestwebdesign.steeve.life.panel.MandelbrotPanel;


public class JuliaFrame extends JFrame
{
	public JuliaFrame()
	{
		panel = new JuliaPanel(0.0, 0.0, this);
		createComponents();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public JuliaFrame(double xValue, double yValue)
	{
		panel = new JuliaPanel(xValue, yValue, this);
		createComponents();
		setProperties();
	}

	public void updateTitle()
	{
		StringBuilder str = new StringBuilder("z²");

		if (panel.getXValue() > 0)
		{
			str.append("+").append(Math.round(100 * panel.getXValue()) / 100.0);
		}
		else if (panel.getXValue() < 0)
		{
			str.append(Math.round(100 * panel.getXValue()) / 100.0);
		}

		if (panel.getYValue() > 0)
		{
			str.append("+").append(Math.round(100 * panel.getYValue()) / 100.0)
					.append("i");
		}
		else if (panel.getYValue() < 0)
		{
			str.append(Math.round(100 * panel.getYValue()) / 100.0).append("i");
		}

		String equation = str.toString();
		setTitle("Ensemble de Julia: " + equation);
	}

	public void setPanel(JuliaPanel panel)
	{
		this.panel = panel;
	}

	public JuliaPanel getPanel()
	{
		return panel;
	}

	private void createComponents()
	{
		zoomIn = new JButton("Zoom In");
		zoomOut = new JButton("Zoom out");
		zoomInit = new JButton("Vue par défaut");

		JPanel southMenu = new JPanel();
		southMenu.setLayout(new FlowLayout());
		southMenu.add(zoomIn);
		southMenu.add(zoomOut);
		southMenu.add(zoomInit);

		zoomIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event)
			{
				if (panel.getZoom() > 1E-12)
				{
					panel.setZoom(panel.getZoom() * 0.8);
				}
			}
		});

		zoomOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event)
			{
				if (panel.getZoom() < Double.MAX_VALUE / 100)
				{
					panel.setZoom(panel.getZoom() * 1.25);
				}
			}
		});

		zoomInit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event)
			{
				panel.setZoom(MandelbrotPanel.ZOOM);
				panel.setXCenter(0.0);
				panel.setYCenter(0.0);
			}
		});

		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		add(southMenu, BorderLayout.SOUTH);

	}

	private void setProperties()
	{
		setSize(400, 300);
		setLocation(800, 0);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private JButton zoomIn, zoomOut, zoomInit;
	private JuliaPanel panel;

	private static final long serialVersionUID = 4L;
}

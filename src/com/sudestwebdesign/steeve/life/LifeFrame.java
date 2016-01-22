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


public class LifeFrame extends JFrame
{
	public LifeFrame()
	{
		createComponents();
		setProperties();
	}

	public JuliaPanel julia()
	{
		return julia.getPanel();
	}

	private void createComponents()
	{
		mandelbrot = new MandelbrotPanel(this);
		julia = new JuliaFrame();

		zoomIn = new JButton("Zoom In");
		zoomOut = new JButton("Zoom out");
		zoomInit = new JButton("Vue par défaut");
		showJulia = new JButton("Afficher l'ensemble de Julia");

		JPanel southMenu = new JPanel();
		southMenu.setLayout(new FlowLayout());
		southMenu.add(zoomIn);
		southMenu.add(zoomOut);
		southMenu.add(zoomInit);
		southMenu.add(showJulia);

		zoomIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event)
			{
				if (mandelbrot.getZoom() > 1E-12)
				{
					mandelbrot.setZoom(mandelbrot.getZoom() * 0.8);
				}
			}
		});

		zoomOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event)
			{
				if (mandelbrot.getZoom() < Double.MAX_VALUE / 100)
				{
					mandelbrot.setZoom(mandelbrot.getZoom() * 1.25);
				}
			}
		});

		zoomInit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event)
			{
				mandelbrot.setZoom(MandelbrotPanel.ZOOM);
				mandelbrot.setXCenter(0.0);
				mandelbrot.setYCenter(0.0);

				julia().setXValue(0.0);
				julia().setYValue(0.0);
				julia().setXCenter(0.0);
				julia().setYCenter(0.0);
				julia().setZoom(JuliaPanel.ZOOM);
			}
		});

		showJulia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (!julia.isShowing())
				{
					julia = new JuliaFrame(mandelbrot.getXCenter(), mandelbrot
							.getYCenter());
				}
				julia().setXCenter(mandelbrot.getXCenter());
				julia().setYCenter(mandelbrot.getYCenter());
				julia().setZoom(mandelbrot.getZoom());
			}
		});

		setLayout(new BorderLayout());
		add(mandelbrot, BorderLayout.CENTER);
		add(southMenu, BorderLayout.SOUTH);
	}

	private void setProperties()
	{
		setLocation(0, 0);
		setSize(800, 600);
		setTitle("LIFE Is a Fractal Explorer - Ensemble de Mandelbrot: z²+c");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private MandelbrotPanel mandelbrot;
	private JuliaFrame julia;

	private JButton zoomIn, zoomOut, zoomInit, showJulia;
	private static final long serialVersionUID = 1L;
}

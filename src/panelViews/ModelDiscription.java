package panelViews;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JPanel;

import technicalModels.CrossModel;
import technical_investing_practice.DayData;

public class ModelDiscription extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private HashMap<String, ArrayList<DayData>> moloDayData = new HashMap<String, ArrayList<DayData>>();

	final int PAD = 20;


	public ModelDiscription() {
		super();
		setBackground(Color.WHITE);
		//add stocks
		/*
		moloDayData.put("aapl", PriceParserCSV.parse("aapl"));
		moloDayData.put("midu", PriceParserCSV.parse("midu"));
		moloDayData.put("vix", PriceParserCSV.parse("vix"));
		moloDayData.put("fb", PriceParserCSV.parse("fb"));
		moloDayData.put("msft", PriceParserCSV.parse("msft"));
		moloDayData.put("uvxy", PriceParserCSV.parse("uvxy"));
		moloDayData.put("tvix", PriceParserCSV.parse("tvix"));
		moloDayData.put("tna", PriceParserCSV.parse("tna"));
		moloDayData.put("spy", PriceParserCSV.parse("spy"));
		moloDayData.put("gspc", PriceParserCSV.parse("gspc"));
		moloDayData.put("midz", PriceParserCSV.parse("midz"));
		moloDayData.put("vxx", PriceParserCSV.parse("vxx"));
		*/


	}

	protected void paintComponent(Graphics g) {
		// To determine the space on the top and bottom of the chart

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();

		// Draw outline.
		g2.setPaint(Color.lightGray);
		g2.draw(new Rectangle2D.Double(PAD, PAD, w-2*PAD, h-2*PAD));


		// Draw String.
		g2.setPaint(Color.black);
		renderString(g, gerDiscription(), 2*PAD, 2*PAD);
	}

	private String gerDiscription() {
		return CrossModel.optimizeAll(moloDayData);
	}
	
	private void renderString(Graphics g, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

}

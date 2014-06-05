package panelViews;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JPanel;

import parser.PriceParserCSV;
import technicalModels.CrossModel;
import technical_investing_practice.DayData;

public class StockChart extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<DayData> loDayData;
	private boolean useLogScale;
	private String lineType;
	private String timeSpan; 
	final int PAD = 20;
	private HashMap<String, ArrayList<DayData>> moloDayData = new HashMap<String, ArrayList<DayData>>();


	public StockChart(String stockSymbol) {
		super();
		setBackground(Color.WHITE);
		loDayData = PriceParserCSV.parse(stockSymbol);
		useLogScale = false;
		lineType = "line";
		timeSpan = "1Y";
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



	}

	protected void paintComponent(Graphics g) {
		// To determine the space on the top and bottom of the chart
		double min = getMin();
		double max = getMax();
		double spread = min - max;
		double minOnChart = getMin()+spread*0.07;
		double maxOnChart = getMax()-spread*0.07;

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();

		// Draw chart outline.
		g2.setPaint(Color.lightGray);
		g2.draw(new Rectangle2D.Double(PAD, PAD, w-2*PAD, h-2*PAD));


		// Draw labels.
		g2.setPaint(Color.BLACK);
		Font font = g2.getFont();
		FontRenderContext frc = g2.getFontRenderContext();
		LineMetrics lm = font.getLineMetrics("0", frc);
		float sh = lm.getAscent() + lm.getDescent();



		// Ordinate label.
		String s = ""; //TODO
		float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
		for(int i = 0; i < s.length(); i++) {
			String letter = String.valueOf(s.charAt(i));
			float sw = (float)font.getStringBounds(letter, frc).getWidth();
			float sx = (PAD - sw)/2;
			g2.drawString(letter, sx, sy);
			sy += sh;
		}

		// Abscissa label.
		s = ""; //TODO
		sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
		float sw = (float)font.getStringBounds(s, frc).getWidth();
		float sx = (w - sw)/2;
		g2.drawString(s, sx, sy);

		Calendar start= loDayData.get(0).getDate();
		// Draw lines.
		double xInc = (double)(w - 2*PAD)/(loDayData.get(0).getDate().getTimeInMillis()-loDayData.get(loDayData.size()-1).getDate().getTimeInMillis());
		
		

		double scale = (double)(h - 2*PAD)/(maxOnChart-minOnChart);
		double x1;
		double y1;
		double x2 = PAD;
		double y2;
		Path2D.Double mountain = new Path2D.Double();
		mountain.moveTo(PAD, h-PAD);
		//this part deals with the chart scale
		if(useLogScale)
			y2 = h - PAD - scale*(Math.log(loDayData.get(1).getHigh()) - minOnChart);
		else
			y2 = h - PAD - scale*(loDayData.get(1).getHigh() - minOnChart);
		
		for(DayData next: loDayData) {
			x1 = x2;
			y1 = y2;
			
			//this part deals with the chart scale
			x2 = PAD + (start.getTimeInMillis()-next.getDate().getTimeInMillis())*xInc;

			if(useLogScale)
				y2 = h - PAD - scale*(Math.log(next.getHigh())-minOnChart);
			else
				y2 = h - PAD - scale*(next.getHigh()-minOnChart);
			
			
			//this part deals with the line type
			if (lineType.toLowerCase().equals("mountain"))
				g2.setPaint(Color.getHSBColor((float)0.5555, (float)0.9, (float)0.89));
			else if(y1 > y2)
				g2.setPaint(Color.green.darker());
			else
				g2.setPaint(Color.red.darker());
			g2.draw(new Line2D.Double(x1, y1, x2, y2));

			if (lineType.toLowerCase().equals("mountain"))
				mountain.lineTo(x2, y2);
			
			
		}
		if (lineType.toLowerCase().equals("mountain")){
			mountain.lineTo(w-PAD, h-PAD);
			g2.setPaint(Color.getHSBColor((float)0.5555, (float)-9.898572, (float)0.89));
			g2.fill(mountain);
		}
		
		
		
		// Mark data points.
		g2.setPaint(Color.black);
		for(DayData next: loDayData) {
			start.compareTo(next.getDate());
			double x = PAD + (start.getTimeInMillis()-next.getDate().getTimeInMillis())*xInc;
			double y;
			//this part deals with the chart scale
			if(useLogScale)
				y = h - PAD - scale*(Math.log(next.getHigh()) - minOnChart);
			else
				y = h - PAD - scale*(next.getHigh() - minOnChart);
			//TODO have this line or not?
			//g2.fill(new Ellipse2D.Double(x-1, y-1, 1, 1));

		}
	}
	private String gerDiscription() {
		return CrossModel.optimizeAll(moloDayData);
	}
	private void renderString(Graphics g, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
	private double getMax() {
		double max = loDayData.get(0).getHigh();
		for(DayData next: loDayData) {
			if(next.getHigh() > max)
				max = next.getHigh();
		}

		//this part deals with the chart scale
		if(useLogScale)
			return Math.log(max);
		else 
			return max;
	}

	private double getMin() {
		double min = loDayData.get(0).getLow();
		for(DayData next: loDayData) {
			if(next.getLow() < min)
				min = next.getLow();
		}

		//this part deals with the chart scale
		if(useLogScale)
			return Math.log(min);
		else 
			return min;
	}


	public boolean isUseLogScale() {
		return useLogScale;
	}

	public void setUseLogScale(boolean useLnScale) {
		this.useLogScale = useLnScale;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;

	}

	public String getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(String timeSpan) {
		this.timeSpan = timeSpan;
	}

}

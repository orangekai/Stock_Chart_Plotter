package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import panelViews.MetricDiscription;
import panelViews.StockChart;


public class MainView extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel panelView;
	private static String stockSymbol = "aapl";


	public MainView() {
		super(stockSymbol);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panelView = new StockChart(stockSymbol);
		this.add(panelView);
		createMenus();
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}


	private void changePanel(JPanel p) {
		this.remove(panelView);
		panelView = p;
		this.add(panelView);
	}


	private void createMenus() {
		JMenuBar menuBar = new JMenuBar();

		//Menus
		JMenu viewMenu = new JMenu("View");
		menuBar.add(viewMenu);

		//sub Menus
		JMenuItem ScreenSizeSubMenu = new JMenu("Screen Size");
		viewMenu.add(ScreenSizeSubMenu);
		
		
		//sub sub Menus
		JMenuItem fitScreenItem = new JMenuItem("Fit Screen");
		fitScreenItem.addActionListener(this);
		ScreenSizeSubMenu.add(fitScreenItem);

		JMenuItem fullScreenItem = new JMenuItem("Full Screen");
		fullScreenItem.addActionListener(this);
		ScreenSizeSubMenu.add(fullScreenItem);
		//sub sub Menus end
		//sub Menus end

		JMenu chartSettingMenu = new JMenu("Chart Setting");
		menuBar.add(chartSettingMenu);

		//sub Menus
		JMenuItem LineTypeSubMenu = new JMenu("Line Type");
		chartSettingMenu.add(LineTypeSubMenu);

		//sub sub Menus
		JMenuItem lineItem = new JMenuItem("Line");
		lineItem.addActionListener(this);
		LineTypeSubMenu.add(lineItem);

		JMenuItem MountainItem = new JMenuItem("Mountain");
		MountainItem.addActionListener(this);
		LineTypeSubMenu.add(MountainItem);

		JMenuItem CandleStickItem = new JMenuItem("Candle Stick");
		CandleStickItem.addActionListener(this);
		LineTypeSubMenu.add(CandleStickItem);

		JMenuItem OHLCItem = new JMenuItem("OHLC");
		OHLCItem.addActionListener(this);
		LineTypeSubMenu.add(OHLCItem);
		//sub sub Menus end

		JMenuItem ChartScaleSubMenu = new JMenu("Chart Scale");
		chartSettingMenu.add(ChartScaleSubMenu);

		//sub sub Menus
		JMenuItem logItem = new JMenuItem("Log");
		logItem.addActionListener(this);
		ChartScaleSubMenu.add(logItem);

		JMenuItem linearItem = new JMenuItem("Linear");
		linearItem.addActionListener(this);
		ChartScaleSubMenu.add(linearItem);
		//sub sub Menus end
		//sub Menus end
		//Menus end

		

		this.setJMenuBar(menuBar);
	}

	public void actionPerformed(ActionEvent e) {
		
		//Actions triggered when in the StockChart panel view.
		if(((JMenuItem) e.getSource()).getText() == "Line")
			((StockChart)panelView).setLineType("line");
		else if (((JMenuItem) e.getSource()).getText() == "Mountain")
			((StockChart)panelView).setLineType("Mountain");
		else if (((JMenuItem) e.getSource()).getText() == "OHLC")
			((StockChart)panelView).setLineType("OHLC");
		else if (((JMenuItem) e.getSource()).getText() == "Candle Stick")
			((StockChart)panelView).setLineType("Candle Stick");
		else if (((JMenuItem) e.getSource()).getText() == "Log")
			((StockChart)panelView).setUseLogScale(true);
		else if (((JMenuItem) e.getSource()).getText() == "Linear")
			((StockChart)panelView).setUseLogScale(false);
		else if (((JMenuItem) e.getSource()).getText() == "Full Screen")
			System.out.print("under construction");
		//TODO actually make it full screen
		else if (((JMenuItem) e.getSource()).getText() == "Fit Screen")
			setExtendedState(JFrame.MAXIMIZED_BOTH); 
		panelView.repaint();
	}

}

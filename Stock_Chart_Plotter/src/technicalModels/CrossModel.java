package technicalModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import technical_investing_practice.DayData;

public class CrossModel {



	public static String optimizeAll(HashMap< String, ArrayList<DayData>> moloDayData){
		StringBuffer buffer = new StringBuffer();
		double max = 0;
		int parameterUsed=0;
		for(String symbol: moloDayData.keySet()){
			ArrayList<DayData> lod = moloDayData.get(symbol);
			max = 0;
			parameterUsed=0;
			for(int i=1; i<300; i++){
				double m = parameterTry(i, lod);
				if (m > max){
					max = m;
					parameterUsed = i;
				}
			}
			
			buffer.append(symbol.toUpperCase() + ": \n" + parameterUsed +" SMA\nnet gain: " + (max - 100)+"% \n\n");
		}
		System.out.println(parameterTry2(59, moloDayData.get("aapl"))-100);
		return buffer.toString();
	}

	private static double parameterTry2(int n, ArrayList<DayData> loDayData){
		// n for n days moving average
		//TODO parameter is set for now, later it'd be a variable
		double m = 1; //ratio of original
		Double price = null; //price of the previous action.
		double sum = 0;

		for (int i=0; i < n; i++){
			sum = sum + loDayData.get(i).getAdjClose();
		}
		double SMACurrentDay = sum/n; // n day SMA on the nth day
		int i;
		boolean wasLowerThanSMA = loDayData.get(n-1).getAdjClose() < SMACurrentDay;
		for (i = n; i < loDayData.size()-1; i++){
			SMACurrentDay = SMACurrentDay + (loDayData.get(i).getAdjClose() - loDayData.get(i-n).getAdjClose())/n;
			if(wasLowerThanSMA != (loDayData.get(i).getAdjClose() < SMACurrentDay)){
				if(wasLowerThanSMA){					if(price != null)

					System.out.println("sell" +" " +price+" "+loDayData.get(i).getAdjClose()+
							" " +(1+(price - loDayData.get(i).getAdjClose())/price)+" "+loDayData.get(i).getDate().getTime()+" "+m);
					if(price != null)
						m = m*(1+(price - loDayData.get(i).getAdjClose())/price); //buy back
					price = loDayData.get(i).getAdjClose();                       //buy
				}//buy back and buy
				else{					if(price != null)

					System.out.println("buy" +" " +price+" "+loDayData.get(i).getAdjClose()+" " +
					(1+(loDayData.get(i).getAdjClose() - price)/price)+" "+loDayData.get(i).getDate().getTime()+" "+m);
					if(price != null)
						m = m*(1+(loDayData.get(i).getAdjClose() - price)/price); //sell
					price = loDayData.get(i).getAdjClose();                       //sell short
				}//sell and sell short
				wasLowerThanSMA = loDayData.get(i).getAdjClose() < SMACurrentDay;
			}
		}

		//to determine the result by ending the last trade
		if(wasLowerThanSMA){
			if(price != null)
				m = m*(1+(price - loDayData.get(i).getAdjClose())/price); //buy back
		}
		else{
			if(price != null)
				m = m*(1+(loDayData.get(i).getAdjClose() - price)/price); //sell
		}

		return (m)*100;

	}



	private static double parameterTry(int n, ArrayList<DayData> loDayData){
		// n for n days moving average
		//TODO parameter is set for now, later it'd be a variable
		double m = 1; //ratio of original
		Double price = null; //price of the previous action.
		double sum = 0;

		for (int i=0; i < n; i++){
			sum = sum + loDayData.get(i).getAdjClose();
		}
		double SMACurrentDay = sum/n; // n day SMA on the nth day
		int i;
		boolean wasLowerThanSMA = loDayData.get(n-1).getAdjClose() < SMACurrentDay;
		for (i = n; i < loDayData.size()-1; i++){
			SMACurrentDay = SMACurrentDay + (loDayData.get(i).getAdjClose() - loDayData.get(i-n).getAdjClose())/n;
			if(wasLowerThanSMA != (loDayData.get(i).getAdjClose() < SMACurrentDay)){
				if(wasLowerThanSMA){
					if(price != null)
						m = m*(1+(price - loDayData.get(i).getAdjClose())/price); //buy back
					price = loDayData.get(i).getAdjClose();                       //buy
				}//buy back and buy
				else{
					if(price != null)
						m = m*(1+(loDayData.get(i).getAdjClose() - price)/price); //sell
					price = loDayData.get(i).getAdjClose();                       //sell short
				}//sell and sell short
				wasLowerThanSMA = loDayData.get(i).getAdjClose() < SMACurrentDay;
			}
		}

		//to determine the result by ending the last trade
		if(wasLowerThanSMA){
			if(price != null)
				m = m*(1+(price - loDayData.get(i).getAdjClose())/price); //buy back
		}
		else{
			if(price != null)
				m = m*(1+(loDayData.get(i).getAdjClose() - price)/price); //sell
		}

		return (m)*100;

	}

}

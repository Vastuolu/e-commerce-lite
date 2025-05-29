package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Locale;

public class MissionUtil {
	public static String getUserInput() {
        
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
        String inputString = null;
        
        try {
            inputString = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return inputString;
    }
    
    public static String moneyFormat( int price ) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return nf.format( price );
    }
    
    public static void clearConsole() throws Exception{
    	try {    		
    		new ProcessBuilder("cmd","/c", "cls").inheritIO().start().waitFor();
    	}catch(Exception e) {
    		throw e;
    	}
    }
}

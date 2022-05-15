package com.mycompany.ingresse.logs;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws SecurityException, IOException {

        Logs logs = new Logs();

        logs.info("Deu certo amém - Log normal");

        logs.warning("Deu certo amém 2 - Log de erro");

        logs.severe("Deu certo amém 3 - Log de fudeu");

//		LOGGER.info("Logger Name: "+ LOGGER.getName());
//		
//		LOGGER.warning("Can cause ArrayIndexOutOfBoundsException");
//		
//		//An array of size 3
//		int []a = {1,2,3};
//		int index = 4;
//		LOGGER.config("index is set to "+index);
//		
//		try{
//			System.out.println(a[index]);
//		}catch(ArrayIndexOutOfBoundsException ex){
//			LOGGER.log(Level.SEVERE, "Exception occur", ex);
//		};
    }
}

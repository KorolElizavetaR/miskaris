package pack.meingutt;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class MainClass {
	static Logger logger = Logger.getLogger(MainClass.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		logger.info("Hello World");
	}
}

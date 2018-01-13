package View;

import java.util.Iterator;
import java.util.Properties;


public class Test {

	public static void main(String[] args) {
		Properties p = System.getProperties();
		for (Object s : p.keySet()) {
			System.out.println(s + " : " + p.getProperty((String) s));
		}
		System.out.println(p);
	}
}

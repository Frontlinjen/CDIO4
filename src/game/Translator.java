package game;

import java.util.Locale;
import java.util.ResourceBundle;

public class Translator {
	private static ResourceBundle strings;
	public static String getString(String keyword, Object... args)
	{
		
		//If not previous set, use default locale(da, DK)
		if(strings==null)
		{
			setLocale(new Locale("en", "GB"));
		}
		if(args!=null)
		{
			return String.format(strings.getString(keyword), args);
		}
		else
		{
			return strings.getString(keyword);
		}
	}
	@Override
	public String toString() {
		return "Current locale: " + strings.getLocale() + " on the following file: " + strings.getBaseBundleName() + " where " + strings.keySet().size() + "keys are contained";
	}
	public static void setLocale(Locale l)
	{	
		strings = ResourceBundle.getBundle("MessageBundle", l);
	}
	//Avoids creating objects of this class
	private Translator()
	{
		
	}
}

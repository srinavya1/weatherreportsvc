package egenproj.WeatherApp.Facade.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public final class Constant {
public final static String ALPHABET_REGEX="[a-z A-Z]";
public final static DateFormat TIME_STAMP_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS", Locale.ENGLISH);
public final static SimpleDateFormat DATE_WITHOUT_TIME_SIME_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
public final static long  MILLISECONDS_IN_AN_HOUR =3600000;
public final static String WIND="wind";
public final static String PREASSURE="pressure";
public final static String HUMIDITY="humidity";
public final static String DEGREE="degree";
public final static String SPEED="speed";
public final static String TEMPTATURE="temperature";
public final static String DESCIPTION="description";
public final static String TIMESTAMP="timestamp";
public final static String DAY="day";
public final static String HOUR="hour";
}

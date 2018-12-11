/*
 * Copyright (c) 2018 Homelet Wei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package homelet.Utile;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateHandler{
	
	private static DateHandler dateHandler = new DateHandler();
	
	private DateHandler(){
	}
	
	public static DateHandler getDateHandler(){
		return dateHandler;
	}
	
	/**
	 * Get day or month range string [ ].
	 *
	 * @param daysInMonthOrMonthInYears the days in month or month in years
	 * @return the string [ ]
	 */
	public String[] getDayOrMonthRange(int daysInMonthOrMonthInYears){
		String[] value   = new String[daysInMonthOrMonthInYears];
		int      counter = 1;
		for(int index = 0; index < value.length; index++){
			value[index] = String.valueOf(counter++);
		}
		return value;
	}
	
	/**
	 * Get year range string [ ].
	 *
	 * @param thisYear the this year
	 * @param range    the range
	 * @return the string [ ]
	 */
	public String[] getYearRange(int thisYear, int range){
		String[] value   = new String[(range * 2) + 1];
		int      counter = 0;
		for(int adder = range; adder > 0; adder--){
			value[counter++] = String.valueOf(thisYear + adder);
		}
		value[counter++] = String.valueOf(thisYear);
		for(int adder = 1; adder <= range; adder++){
			value[counter++] = String.valueOf(thisYear - adder);
		}
		return value;
	}
	
	/**
	 * Get year before today string [ ].
	 *
	 * @param thisYear the this year
	 * @param range    the range
	 * @return the string [ ]
	 */
	public String[] getYearBeforeToday(int thisYear, int range){
		String[] value   = new String[(range * 2) + 1];
		int      counter = 0;
		value[counter++] = String.valueOf(thisYear);
		for(int adder = 1; adder <= range * 2; adder++){
			value[counter++] = String.valueOf(thisYear - adder);
		}
		return value;
	}
	
	public int[] getCurrentDateInfo(){
		Calendar now    = Calendar.getInstance();
		int[]    result = new int[3];
		result[0] = now.get(Calendar.YEAR);
		result[1] = now.get(Calendar.MONTH);
		result[2] = now.get(Calendar.DAY_OF_MONTH) - 1;
		return result;
	}
	
	public int getMonthInYear(){
		return 12;
	}
	
	public int getDaysInMonth(int year, int month){
		Calendar time = Calendar.getInstance();
		time.clear();
		time.set(Calendar.YEAR, year);
		time.set(Calendar.MONTH, month - 1);
		return time.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public String getYear(){
		return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	}
	
	/**
	 * TODO
	 *
	 * @return string
	 * @author HomeletWei
	 */
	public String getDateInfo(){
		return getDateInfo("zXXX;") + getDateInfo("yyyy-MM-dd") + "T" + getDateInfo("kk:mm:ss.SSS") + "Z";
	}
	
	/**
	 * <pre>
	 * G 	Era 			designator Text 		AD
	 * y 	Year 			Year 				1996; 96
	 * Y 	Week 			year 				Year 2009; 09
	 * M 	Month in year 		(context sensitive) Month 	July; Jul; 07
	 * L 	Month in year 		(standalone form) Month 	July; Jul; 07
	 * w 	Week in year 		Number 				27
	 * W 	Week in month 		Number 				2
	 * D 	Day in year 		Number 				189
	 * d 	Day in month 		Number 				10
	 * F 	Day of week in month 	Number 				2
	 * E 	Day name in week 	Text 				Tuesday; Tue
	 * u 	Day number of week 	(1 = Monday, ..., 7 = Sunday) 	1
	 * a 	Am/pm marker 		Text 				PM
	 * H 	Hour in day (0-23) 	Number				0
	 * k 	Hour in day (1-24) 	Number 				24
	 * K 	Hour in am/pm (0-11) 	Number 				0
	 * h 	Hour in am/pm (1-12) 	Number 				12
	 * m 	Minute in hour 		Number 				30
	 * s 	Second in minute 	Number 				55
	 * S 	Millisecond 		Number 				978
	 * z 	Time zone General time zone Pacific Standard Time; PST; GMT-08:00
	 * Z 	Time zone RFC 822 	time zone 			-0800
	 * X 	Time zone ISO 8601 	time zone 			-08; -0800; -08:00
	 * </pre>
	 *
	 * @param formate the formate
	 * @return string
	 * @author HomeletWei
	 */
	public String getDateInfo(String formate){
		Calendar         currentDate = Calendar.getInstance();
		SimpleDateFormat formatter   = new SimpleDateFormat(formate);
		return formatter.format(currentDate.getTime());
	}
}

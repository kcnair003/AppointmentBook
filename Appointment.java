import java.util.Calendar;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.text.SimpleDateFormat;

public class Appointment implements Comparable<Appointment>, Serializable
{

	private static final long serialVersionUID = 1L;
	private String fName;
	private String lName;
	private String description;
	private Calendar start;
	private Calendar end;
	private String day;
	/**
	 * Constructs an appointment object containing the name, description, and start and end times for an event
	 * @param fName is the first name of the person who booked the event
	 * @param lName is the last name of the person who booked the event
	 * @param description describes the event that will occur
	 * @param year is the year the event occurs according to the Gregorian Calendar
	 * @param month is the month the event occurs according to the Gregorian Calendar
	 * @param date is the number of the day the event occurs according to the Gregorian Calendar
	 * @param day is the day of the week the event occurs according to the Gregorian Calendar
	 * @param hour is the hour of the day the event occurs according to the Gregorian Calendar
	 * @param minute is the minute of the day the event occurs according to the Gregorian Calendar
	 * @param hour2 is the hour of the day the event ends according to the Gregorian Calendar
	 * @param minute2 is the minute of the day the event ends according to the Gregorian Calendar
	 * @throws IllegalArgumentException when the appointment ends before it begins or lasts more than a day or is less than a minute long
	 */
	public Appointment(String fName, String lName, String description, int year, int month, int date, String day, int hour, int minute, int hour2, int minute2) 
	{
		if (fName.equals("") || lName.equals("") || description.equals(""))
			throw new IllegalArgumentException("You must include a first name, last name, and description.");
		this.fName = fName;
		this.lName = lName;
		this.description = description;
		this.day = day;
		int xday = 0;
		if(day.equalsIgnoreCase("Sunday"))
			xday=1;
		if(day.equalsIgnoreCase("Monday"))
			xday=2;
		if(day.equalsIgnoreCase("Tuesday"))
			xday=3;
		if(day.equalsIgnoreCase("Wednesday"))
			xday=4;
		if(day.equalsIgnoreCase("Thursday"))
			xday=5;
		if(day.equalsIgnoreCase("Friday"))
			xday=6;
		if(day.equalsIgnoreCase("Saturday"))
			xday=7;
		start = Calendar.getInstance();
		start.set(year, month, date, hour, minute);
		start.setLenient(false);
		end = Calendar.getInstance();
		end.set(year, month, date, hour2, minute2);
		end.setLenient(false);
		if(start.compareTo(end)>0)
			throw new IllegalArgumentException("The appointment must end after it is started.");
		if(start.compareTo(end) == 0)
			throw new IllegalArgumentException("The appointment must be at least one minute long.");
		if(start.get(Calendar.DAY_OF_WEEK) != xday)
			throw new IllegalArgumentException("The day of the week is incorrect for the date.");
	}
	/**
	 * @return Calendar for the start of the event
	 */
	public Calendar getStart() {
		return start;
	}
	/**
	 * @return Calendar for the end of the event
	 */
	public Calendar getEnd() {
		return end;
	}
	/**
	 * Compares two Appointments to return the earlier event in terms of a positive integer for the parameter Appointment being earlier
	 * @param other Appointment for comparison
	 * @return an integer that shows which appointment is earlier
	 */
	public int compareTo(Appointment other) {
		return start.compareTo(other.start);
	}
	/**
	 * Compares two Appointments to return the earlier event in terms of a positive integer for the parameter Appointment being earlier
	 * @param other Appointment for comparison
	 * @return an integer that shows which appointment is earlier
	 */
	public int compareTo(Calendar other) {
		return start.compareTo(other);
	}
	/**
	 * @return String that represents the object's state of name, description, and start and end times
	 */
	public String toString() {
		return "The event: " + description + System.getProperty("line.separator") + "Was booked by: " + fName + " " + lName + System.getProperty("line.separator") + "For the duration of: " + new SimpleDateFormat("h:mm a").format(start.getTime()) + " to " + new SimpleDateFormat("h:mm a").format(end.getTime()) + " on " + day + ", " + new SimpleDateFormat("MMMM d, yyyy").format(start.getTime());
	}

}

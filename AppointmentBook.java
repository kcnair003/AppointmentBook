import java.io.Serializable;
import java.util.Calendar;

public class AppointmentBook implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AppointmentBook()
	{
		this.array = new Appointment[0];
	}
	/**
	 * Constructs an Appointment Book object that contains an array of appointments
	 * @param array of appointments
	 */
	public AppointmentBook(Appointment[] array)
	{
		this.array = array;
	}
	/**
	 * Sorts appointments of Appointment Book's array by date and time
	 */
	public void sortArray()
	{
		new MergeSorter(array).sort();
	}
	/**
	 * Adds an appointment to appointment book's array
	 * @param a is an appointment
	 */
	public void addAppointment(Appointment a)
	{
		if (conflictSearch(a))
			throw new IllegalArgumentException("This appointment conflicts with other appointments! Please Reschedule! ");
		Appointment[] array2 = new Appointment[array.length+1];
		int index = ((new BinarySearcher(array).search(a)+1)*-1);
		System.arraycopy(array, 0, array2, 0, index);
		System.arraycopy(array, index, array2, index, array.length-index);
		array2[array.length] = a;
		array = array2;
	}
	public Appointment[] getAppointmentsOnDay(int year, int month, int date) {
		Calendar s = Calendar.getInstance();
		s.set(year, month, date, 0, 0);
		s.setLenient(false);
		Calendar e = Calendar.getInstance();
		e.set(year, month, date, 23, 59);
		e.setLenient(false);
		int xday = s.get(Calendar.DAY_OF_WEEK);
		String day = "";
		if(xday==1)
			day = "Sunday";
		if(xday==2)
			day = "Monday";
		if(xday==3)
			day = "Tuesday";
		if(xday==4)
			day = "Wednesday";
		if(xday==5)
			day = "Thursday";
		if(xday==6)
			day = "Friday";
		if(xday==7)
			day = "Saturday";
		Appointment[] array2 = new Appointment[array.length];
		Appointment app = new Appointment("start", "start", "start", s.get(Calendar.YEAR), s.get(Calendar.MONTH), s.get(Calendar.DAY_OF_MONTH), day, 0, 0, 0, 1);
		Appointment app2 = new Appointment("start", "start", "start", s.get(Calendar.YEAR), s.get(Calendar.MONTH), s.get(Calendar.DAY_OF_MONTH), day, 23, 58, 23, 59);
		int start = new BinarySearcher(array).search(app);
		int end = new BinarySearcher(array).search(app2);
		Appointment[] array3 = new Appointment[0];
		if(start<0 && end<0)
		{
			System.arraycopy(array, -start-1, array2, 0, -end-1);
			array3 = new Appointment[-end+start];
			System.arraycopy(array2, 0, array3, 0, array3.length);
		}
		if(start>=0 && end>0)
		{
			System.arraycopy(array, start, array2, 0, end);
			array3 = new Appointment[end-start];
			System.arraycopy(array2, 0, array3, 0, array3.length);
		}
		if(start>=0 && end<0)
		{
			System.arraycopy(array, start, array2, 0, -end-1);
			array3 = new Appointment[-end-1-start];
			System.arraycopy(array2, 0, array3, 0, array3.length);
		}
		if(start<0 && end>0)
		{
			System.arraycopy(array, -start-1, array2, 0, end);
			array3 = new Appointment[end+start+1];
			System.arraycopy(array2, 0, array3, 0, array3.length);
		}
		return array3;
	}
	public boolean conflictSearch(Appointment a)
	{
		for (Appointment b:array) { 
			Calendar startA = a.getStart();
			Calendar endA = a.getEnd();
			Calendar startB = b.getStart();
			Calendar endB = b.getEnd();
			int x = startA.compareTo(startB);
			int y = startA.compareTo(endB);
			int z = startB.compareTo(endA);
			if ((x >= 0 && y < 0) || (x <= 0 && z < 0))
				return true;
		}
		return false;
	}
	public Appointment[] getBook()
	{
		return array;
	}
	public String toString() {
		String s = "";
		if (array.length == 0)
			s = "No appointments.";
		for (Appointment a:array)
			s += a.toString() + System.getProperty("line.separator") + System.getProperty("line.separator");
		return s;
	}
	
	private Appointment[] array;
}

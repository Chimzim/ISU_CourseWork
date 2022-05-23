
public class Alarm {
	//the time
	private int mins;
	private int hours;
	//the alarm time
	private int alarmMin, alarmHour;
	//when the alarm should go off
	private boolean alarmRinging;
	//snooze 
	//if the alarm is set or not
	private boolean isSet;
	
	public Alarm() {
		mins = 0;
		hours = 0;
		alarmMin = 10;
		alarmHour = 10;
		alarmRinging = false;
		isSet = false;
	}
	public Alarm(int setMins, int setHours, int setAlarmMins, int setAlarmHours) {
		mins = setMins;
		hours = setHours;
		alarmMin = setAlarmMins;
		alarmHour = setAlarmHours;
		alarmRinging = false;
		isSet = false;
	}
	/**
	 * Turns the alarm off
	 */
	public void turnAlarmOff() {
		isSet = false;
		alarmRinging = false;
	}
	/**
	 * Turns on the alarm
	 */
	public void turnAlarmOn() {
		isSet = true;
	}
	/**
	 * Sets the time of the clock.
	 * @param setMins - sets the minutes on the clock 
	 * @param setHours - sets the hours on the clock
	 */
	public void setTime(int setMins, int setHours) {
		mins = setMins;
		hours = setHours;
	}
	/**
	 * Sets the time for the alarm to ring
	 * @param setAlarmMins - sets the alarm mins
	 * @param setAlarmHours - sets the alarm hours
	 */
	public void setAlarm(int setAlarmMins, int setAlarmHours) {
		alarmMin = setAlarmMins;
		alarmHour = setAlarmHours;
	}
	/**
	 * Silences the alarm
	 */
	public void silenceAlarm() {
		alarmRinging = false;
	}
	/**
	 * Checks to see if the alarm is ringing
	 * @return - If the alarm is ringing or not
	 */
	public boolean isRinging() {
		return alarmRinging;
	}
	/**
	 * Shows the time on the alarm 
	 * @return The current time on the clock
	 */
	public String getTime() {
		return hours + ":" + mins;
	}
	/**
	 * Shows the alarm time 
	 * @return The currently set alarm time
	 */
	public String getAlarmTime() {
		return alarmHour + ":" + alarmMin;
	}
	public void advanceTime() {
		mins++;
		hours = hours + (mins / 60); 
		mins = mins % 60;
		hours %= 24;
	}

}

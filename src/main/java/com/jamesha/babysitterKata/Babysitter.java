package com.jamesha.babysitterKata;

public class Babysitter {

	//According to the original specifications, these 4 fields (startTimes and bedtimes) can range from from the earliest start time to 11PM.
	//An enhancement will be required if the product owner wants to adjust these fields to go past midnight. 
	public static final int START_TIME_EARLIEST = 17; //Hours are represented from 1-24.
	public static final int START_TIME_LATEST = 23;
	public static final int BEDTIME_EARLIEST = START_TIME_EARLIEST;
	public static final int BEDTIME_LATEST = 23;
	
	public static final int END_TIME_EARLIEST = START_TIME_EARLIEST;
	public static final int END_TIME_LATEST = 4;
	
	public static final int WAGE_START_TO_BEDTIME = 12;
	public static final int WAGE_BEDTIME_TO_MIDNIGHT = 8;
	public static final int WAGE_MIDNIGHT_TO_END = 18;
	
	public static final String INVALID_TIME_RANGES_MESSAGE = "Valid time inputs must be from 1 to 24.";
	public static final String INVALID_EARLY_START_TIME_MESSAGE = "Start time cannot be before " + START_TIME_EARLIEST + ".";
	public static final String INVALID_LATE_START_TIME_MESSAGE = "Start time cannot be after " + START_TIME_LATEST + ".";
	public static final String INVALID_EARLY_BEDTIME_MESSAGE = "Bedtime cannot be before " + BEDTIME_EARLIEST + ".";
	public static final String INVALID_LATE_BEDTIME_MESSAGE = "Bedtime cannot be after " + BEDTIME_LATEST + ".";
	public static final String INVALID_END_TIME_MESSAGE = "End time must be between " + END_TIME_EARLIEST + " and " + END_TIME_LATEST + ".";
	public static final String INVALID_BEDTIME_BEFORE_START_TIME_MESSAGE = "Bedtime cannot be before start time.";
	public static final String INVALID_END_TIME_BEFORE_START_TIME_MESSAGE = "End time cannot be before start time.";
	public static final String INVALID_BEDTIME_AFTER_END_TIME_MESSAGE = "Bedtime cannot be after end time.";

	private int startTime;
	private int bedtime;
	private int endTime;
	
	public Babysitter(int startTime, int bedtime, int endTime) {
		validateTimes(startTime, bedtime, endTime);
		this.startTime = startTime;
		this.bedtime = bedtime;
		this.endTime = endTime;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getBedtime() {
		return bedtime;
	}

	public int getEndTime() {
		return endTime;
	}
	
	private void validateTimes(int startTime, int bedtime, int endTime) {
		validateTimesAreFromOneToTwentyFour(startTime, bedtime, endTime);
		validateTimesAgainstEarliestAndLatestAllowedTimes(startTime, bedtime, endTime);
		validateBedtimeAndEndTimeNotBeforeStartTime(startTime, bedtime, endTime);
		validateBedtimeNotAfterEndTime(bedtime, endTime);
	}

	private void validateTimesAreFromOneToTwentyFour(int startTime, int bedtime, int endTime) {
		if (startTime < 1 || bedtime < 1 || endTime < 1 || startTime > 24 || bedtime > 24 || endTime > 24) {
			throw new IllegalArgumentException(INVALID_TIME_RANGES_MESSAGE);
		}
	}
	
	private void validateTimesAgainstEarliestAndLatestAllowedTimes(int startTime, int bedtime, int endTime) {
		if (startTime < START_TIME_EARLIEST) {
			throw new IllegalArgumentException(INVALID_EARLY_START_TIME_MESSAGE);
		} 
		if (startTime > START_TIME_LATEST) {
			throw new IllegalArgumentException(INVALID_LATE_START_TIME_MESSAGE);
		}
		if (bedtime < BEDTIME_EARLIEST) {
			throw new IllegalArgumentException(INVALID_EARLY_BEDTIME_MESSAGE);
		}
		if (bedtime > BEDTIME_LATEST) {
			throw new IllegalArgumentException(INVALID_LATE_BEDTIME_MESSAGE);
		}
		if (endTime < END_TIME_EARLIEST && endTime > END_TIME_LATEST) {
			throw new IllegalArgumentException(INVALID_END_TIME_MESSAGE);
		}
	}
	
	private void validateBedtimeAndEndTimeNotBeforeStartTime(int startTime, int bedtime, int endTime) {
		if (bedtime < startTime) {
			throw new IllegalArgumentException(INVALID_BEDTIME_BEFORE_START_TIME_MESSAGE);
		}
		if (endTime < startTime && !isEndTimeAfterMidnight(endTime)) {
			throw new IllegalArgumentException(INVALID_END_TIME_BEFORE_START_TIME_MESSAGE);
		}
	}
	
	private void validateBedtimeNotAfterEndTime(int bedtime, int endTime) {
		if (endTime < bedtime && !isEndTimeAfterMidnight(endTime)) {
			throw new IllegalArgumentException(INVALID_BEDTIME_AFTER_END_TIME_MESSAGE);
		}
	}
	
	public static boolean isEndTimeAfterMidnight(int endTime) {
		//The scope of this application is calculating wage for one night of babysitting (not exceeding 24 hours in total).
		//Therefore, if end time is less than the earliest possible start time, that time will be have to be in the next day after midnight.
		return endTime < START_TIME_EARLIEST;
	}
}

/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.portlet.ClassifiedsPortlet.service;


import org.jasig.portlet.ClassifiedsPortlet.service.AdService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class AdGroomer extends Thread {
	private int hourToCheck = 0;  
	private int minuteToCheck = 0;
	private int checkInterval = 0; 	// seconds
	private long maxCheckIntervalMillis = 0L;	// 12 hours
	/*
	private int hourToCheck = 11;  
	private int minuteToCheck = 0;
	private int checkInterval = 60; 	// seconds
	private long maxCheckIntervalMillis = 43200000L;	// 12 hours
*/

	private boolean bRun;

	
	public void setHourToCheck(int hourToCheck)
	{
		this.hourToCheck = hourToCheck;
	}
	
	public void setMinuteToCheck(int minuteToCheck)
	{
		this.minuteToCheck = minuteToCheck;
	}
	
	public void setCheckInterval(int checkInterval)
	{
		this.checkInterval = checkInterval;
	}
	
	public void setmaxCheckIntervalMillis(long maxCheckIntervalMillis)
	{
		this.maxCheckIntervalMillis = maxCheckIntervalMillis;
	}
	
	@Autowired
	private AdService adService;
	
	private static Log log = LogFactory.getLog(AdGroomer.class);
	
	AdGroomer() {
		this.setDaemon(true);
		log.info("Ad groomer starting...");
		bRun = true;
	}
	public void endThread(){
		bRun = false;
		log.info("Ad groomer stopping...");
		this.interrupt();
	}
	
	
	@Override
	public void run() {
		Date now;
		Calendar nowCal = new GregorianCalendar();
		long lastCheckTime = System.currentTimeMillis();
		boolean firstCheck = true;
		
		while (bRun) {
			now = new Date();
			nowCal.setTime(now);
		
			if (nowCal.get(Calendar.HOUR_OF_DAY) == hourToCheck && 
				nowCal.get(Calendar.MINUTE) <= (minuteToCheck + 1) &&
				(firstCheck || System.currentTimeMillis() > (lastCheckTime + maxCheckIntervalMillis))) {
				
				log.info("Ad groomer to delete expired ads at "+now.toString());
				adService.deleteExpiredAds();
				lastCheckTime = System.currentTimeMillis();
				firstCheck = false;
			}
			try {
				log.debug("Ad groomer Waiting to see if we should check the time...");
				sleep(checkInterval * 1000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}	

package com.coffee.weibo4j;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;

/**
 * Scanner.
 */
public abstract class Scanner {
	
	private Timer timer;
	private TimerTask task;
	private File rootDir;
	private int interval;
	private boolean running = false;
	protected Server server = null;
	
	private final Map<String,TimeSize> preScan = new HashMap<String,TimeSize> ();
	private final Map<String,TimeSize> curScan = new HashMap<String,TimeSize> ();
	
	public Scanner(String rootDir, int interval,Server server) {
		if (StringUtils.isBlank(rootDir))
			throw new IllegalArgumentException("The parameter rootDir can not be blank.");
		this.rootDir = new File(rootDir);
		if (!this.rootDir.isDirectory())
			throw new IllegalArgumentException("The directory " + rootDir + " is not exists.");
		if (interval <= 0)
			throw new IllegalArgumentException("The parameter interval must more than zero.");
		this.interval = interval;
		this.server=server;
	}
	
	public abstract void onChange();
	
	private void working() {
		scan(rootDir);
		compare();
		
		preScan.clear();
		preScan.putAll(curScan);
		curScan.clear();
	}
	
	private void scan(File file) {
		if (file == null || !file.exists())
			return ;
		
		if (file.isFile()) {
			try {
				curScan.put(file.getCanonicalPath(), new TimeSize(file.lastModified(),file.length()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (file.isDirectory()) {
			File[] fs = file.listFiles();
			if (fs != null)
				for (File f : fs)
					scan(f);
		}
	}
	
	private void compare() {
		if (preScan.size() == 0)
			return;
		
		if (!preScan.equals(curScan))
			onChange();
	}
	
	public void start() {
		if (!running) {
			timer = new Timer("Scanner", true);
			task = new TimerTask() {public void run() {working();}};
			timer.schedule(task, 1010L * interval, 1010L * interval);
			running = true;
		}
	}
	
	public void stop() {
		if (running) {
			timer.cancel();
			task.cancel();
			running = false;
		}
	}
}

class TimeSize {
	
	final long time;
	final long size;
	
	public TimeSize(long time, long size) {
		this.time = time;
		this.size = size;
	}
	
	public int hashCode() {
		return (int)(time ^ size);
	}
	
	public boolean equals(Object o) {
		if (o instanceof TimeSize) {
			TimeSize ts = (TimeSize)o;
			return ts.time == this.time && ts.size == this.size;
		}
		return false;
	}
	
	public String toString() {
		return "[t=" + time + ", s=" + size + "]";
	}
}


package com.coffee.weibo4j.utils;

/**
 * 重做一个毫秒级的简单StopWatch轮子。
 * 
 * @author calvin
 */
public class StopWatch {
	private long startTime;

	public StopWatch() {
		startTime = System.currentTimeMillis();
	}

	public long getMillis() {
		return System.currentTimeMillis() - startTime;
	}

	public void reset() {
		startTime = System.currentTimeMillis();
	}
}

package com.e16din.lightutils;

import android.util.Log;

@Deprecated
public class LogWrapper {

	private Object cls = null;

	public LogWrapper(Object cls) {
		this.cls = cls;
	}

	public void logDebug(String message) {
		Log.d("debug_bp", updateMessage(message));
	}

	public void logInfo(String message) {
		Log.i("info_bp", cls.getClass().getSimpleName() + ": " + message);
	}

	public void logError(String message) {
		Log.i("error_bp", cls.getClass().getSimpleName() + ": " + message);
	}

	private String updateMessage(String message) {
		return cls.getClass().getSimpleName() + ": " + message;
	}
}

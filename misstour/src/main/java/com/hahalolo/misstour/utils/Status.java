package com.hahalolo.misstour.utils;

public class Status {

	private int code;
	private boolean success;
	private String errors;
	
	public Status(int code, boolean success, String errors) {
		this.code = code;
		this.success = success;
		this.errors = errors;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	
}

package com.hahalolo.misstour.utils;

import java.util.List;

public class MissResponses<E> {
	
	private Status status;
	private List<E> elements;
	public MissResponses (Status status, List<E> elements){
		this.status = status;
		this.elements = elements;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public List<E> getElements() {
		return elements;
	}
	public void setElements(List<E> elements) {
		this.elements = elements;
	}
	
	

}

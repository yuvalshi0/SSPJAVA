package com.hit.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CLI implements Runnable {
	private Scanner input;
	private PrintWriter output;
	private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
	private String value = null;
	
	public CLI(InputStream in, OutputStream out) {
		input = new Scanner(in);
		output = new PrintWriter(out);
	} 
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		this.changes.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		this.changes.removePropertyChangeListener(pcl);
	}

	@Override
	public void run() {
		String clientInput = null;
		write("SPP Server Manager enter \"help\" for more info");
		
		while (true) {
			clientInput = input.nextLine().toLowerCase();
			switch(clientInput) {
			case "start":
				write("Starting server...");
				setValue(clientInput);
				break;
			case "stop":
				write("Stopping server...");
			    setValue(clientInput);
			    break;
			case "help":
				write("start: start the server\r\n" + 
						"stop: stops the server\r\n" + 
						"deletedb: clears data base\r\n" + 
						"startmonitor: start server monitor\r\n" + 
						"stopmonitor: stop server monitor");
			    break;
			case "deletedb":
				write("Database deleted");
			    setValue(clientInput);
			    break;
			case "startmonitor":
				write("Monitor ON");
			    setValue(clientInput);
			    break;
			case "stopmonitor":
				write("Monitor OFF");
			    setValue(clientInput);
			    break;
			default:
				write("Unknown command" + clientInput);
				break;
			}
		}
	}
	
	public void write(String string) {
		output.println(string);
		output.flush();
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String newValue) {
		this.value = newValue;
		this.changes.firePropertyChange("value",null, newValue);
	}

}

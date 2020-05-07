package com.ilegra.swe.brunovieira.powservice;

public class ShutdownModule extends netflix.karyon.ShutdownModule{
	
	public ShutdownModule() {
	    super(9092);
	}
	
}
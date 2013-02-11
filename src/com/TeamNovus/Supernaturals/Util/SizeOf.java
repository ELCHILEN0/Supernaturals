package com.TeamNovus.Supernaturals.Util;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class SizeOf {
	private static Runtime runtime = Runtime.getRuntime();

	/**
	 * Estimate the size of an object.
	 * 
	 * @param o - The object to estimate the size of.
	 */
	public static long sizeOf(Object o) {
		// The count of the number of objects to allocate.
		final int count = 10000;
		
		// The array of the allocated objects.
		Object[] objects = new Object[count];

		long before = getUsedMemory();

		for (int i = 0; i < objects.length; i++) {
			// Clone the object.
			objects[i] = clone(o);
		}

		long after = getUsedMemory();

		return Math.round((after - before)/count);
	}
	
	public static void printSizeOf(Object o) {
		System.out.println("_____________________.[ SizeOf ]._____________________");
		System.out.println("Object: " + o.getClass().getName());
		System.out.println("Object Size: " + sizeOf(o));
		System.out.println("------------------------------------------------------");
	}

	private static void runGC() {
		int times = 4;

		for (int i = 0; i < times; i++) {
			runtime.runFinalization();
			runtime.gc();

			Thread.currentThread();
			Thread.yield();
		}
	}

	private static long getUsedMemory() {
		// Force the GC to run to ensure more precise memory statistics;
		runGC();

		return runtime.totalMemory() - runtime.freeMemory();
	}
	
	private static Object clone(Object o) {
		Object clone = null;
		
		try {
			clone = o.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		// Experimental: (Doesnt reflect the actual size for some reason)
//		Field[] fromFields = o.getClass().getDeclaredFields();
//		Field[] toFields = o.getClass().getDeclaredFields();
//		
//		for (int i = 0; i < fromFields.length; i++) {
//			for (int j = 0; j < toFields.length; j++) {
//				fromFields[i].setAccessible(true);
//				toFields[i].setAccessible(true);
//				
//				if(fromFields[i].getName().equals(toFields[i].getName()) && fromFields[i].getType().equals(toFields[i].getType())) {
//					try {
//						toFields[i].set(clone, fromFields[i].get(o));
//					} catch (IllegalArgumentException e) {
//						e.printStackTrace();
//					} catch (IllegalAccessException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
		
		return clone;
	}

	// Experimental: getUnsafe().allocateInstance(o.getClass());
	@SuppressWarnings("unused")
	private static Unsafe getUnsafe() {
	    try {
	        Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
	        Field unsafeField = unsafeClass.getDeclaredField("theUnsafe");
	        unsafeField.setAccessible(true);
	        Object unsafeObj = unsafeField.get(unsafeClass);
	 
	        return (Unsafe) unsafeObj;
	    }
	    catch (Exception e) {
	        return null;
	    }
	}

}

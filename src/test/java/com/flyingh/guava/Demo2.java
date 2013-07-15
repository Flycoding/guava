package com.flyingh.guava;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class Demo2 {
	enum Ordering implements Serializable {
		INSTANCE;
	}

	@Test
	public void test() throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			FileNotFoundException, IOException, ClassNotFoundException {
		Constructor<Ordering> declaredConstructor = Ordering.class
				.getDeclaredConstructor(String.class, int.class);
		System.out.println(declaredConstructor);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"Ordering.ser"));
		oos.writeObject(Ordering.INSTANCE);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"Ordering.ser"));
		Object obj = ois.readObject();
		ois.close();
		System.out.println(obj.getClass());
		System.out.println(obj == Ordering.INSTANCE);
	}
}

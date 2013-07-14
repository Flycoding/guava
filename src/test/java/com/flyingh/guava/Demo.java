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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Equivalence;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

class Order implements Serializable {
	private static final long serialVersionUID = -271725743026637470L;
	private static final Order INSTANCE = new Order();

	private Order() {
	}

	public static Order getInstance() {
		return INSTANCE;
	}

	private Object readResolve() {
		return INSTANCE;
	}
}

public class Demo {
	
	@Test
	public void test10() throws FileNotFoundException, IOException,
			ClassNotFoundException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		System.out.println(Order.getInstance() == Order.getInstance());
		String path = "order.ser";
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				path));
		oos.writeObject(Order.getInstance());
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		Object order2 = ois.readObject();
		ois.close();
		System.out.println(order2 == Order.getInstance());
		Constructor<Order> conc = Order.class.getDeclaredConstructor();
		conc.setAccessible(true);
		Order newInstance = conc.newInstance();
		System.out.println(newInstance == Order.getInstance());
	}

	class LengthEquivalence extends Equivalence<String> {

		@Override
		protected boolean doEquivalent(String a, String b) {
			return a.length() == b.length();
		}

		@Override
		protected int doHash(String t) {
			return t.hashCode();
		}

	}

	@Test
	public void test9() {
		LengthEquivalence le = new LengthEquivalence();
		Assert.assertTrue(le.equivalent("a", "b"));
		Assert.assertFalse(le.equivalent("a", "hello"));
	}

	@Test
	public void test8() {
		System.out.println("Aa".hashCode());
		System.out.println("BB".hashCode());
	}

	@Test
	public void test7() {
		int a = 158;
		System.out.println(~a);
		int b = -158;
		System.out.println(~b);
	}

	@Test
	public void test6() {
		ArrayList<String> list = Lists.newArrayList("a", "b", "c", "d", "e");
		String[] strs = new String[8];
		Arrays.fill(strs, "abc");
		String[] array = list.toArray(strs);
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(strs));
	}

	@Test
	public void test5() {
		Assert.assertFalse(Double.NaN > 0);
		Assert.assertFalse(Double.NaN == 0);
		Assert.assertFalse(Double.NaN < 0);
	}

	@Test
	public void test4() {
		Set<Integer> set = Optional.of(3).asSet();
		System.out.println(set.getClass());
		System.out.println(Objects.toStringHelper(set)
				.add("info", "hello world!!!").toString());
		System.out.println(Objects.toStringHelper(set.getClass()));
		System.out.println(Objects.toStringHelper("java.lang.String").add("a",
				"1"));
	}

	@Test
	public void test3() {
		Optional<Object> absent = Optional.fromNullable(null);
		System.out.println(absent.isPresent());
		System.out.println(absent.or("defaultValue"));
		System.out.println(absent.orNull());
	}

	@Test
	public void test2() {
		Optional<String> optional = Optional.of("abc");
		System.out.println(optional.isPresent());
		System.out.println(optional.get());
	}

	@Test
	public void test() {
		List<Integer> list = Lists.newArrayList();
		list.add(1);
		list.addAll(Arrays.asList(3, 5, 2, 4));
		System.out.println(list);
		System.out.println(Ordering.natural().sortedCopy(list));
	}
}

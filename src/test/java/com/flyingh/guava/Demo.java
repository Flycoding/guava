package com.flyingh.guava;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class Demo {
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

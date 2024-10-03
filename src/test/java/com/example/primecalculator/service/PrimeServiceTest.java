package com.example.primecalculator.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimeServiceTest {
	private final PrimeService primeService = new PrimeService();

	@Test
	void calculatePrimes_WithUpperBoundLessThan2_ShouldReturnEmptyList() {
		List<Integer> primes = primeService.calculatePrimes(1);
		assertTrue(primes.isEmpty(), "List should be empty for upperBound < 2");
	}

	@Test
	void calculatePrimes_WithUpperBound2_ShouldReturnListContainingOnly2() {
		List<Integer> primes = primeService.calculatePrimes(2);
		assertEquals(Arrays.asList(2), primes, "List should only contain number 2");
	}

	@Test
	void calculatePrimes_WithUpperBound10_ShouldReturnCorrectPrimes() {
		List<Integer> expectedPrimes = Arrays.asList(2, 3, 5, 7);
		List<Integer> primes = primeService.calculatePrimes(10);
		assertEquals(expectedPrimes, primes, "List should contain correct prime numbers up to 10");
	}

	@Test
	void calculatePrimes_WithUpperBound30_ShouldReturnCorrectPrimes() {
		List<Integer> expectedPrimes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		List<Integer> primes = primeService.calculatePrimes(30);
		assertEquals(expectedPrimes, primes, "List should contain correct prime numbers up to 30");
	}

	@Test
	void calculatePrimes_WithLargeUpperBound_ShouldReturnPrimes() {
		int upperBound = 100;
		List<Integer> primes = primeService.calculatePrimes(upperBound);
		List<Integer> expectedFirstPrimes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		assertEquals(expectedFirstPrimes, primes.subList(0, 10), "First 10 prime numbers should be correct");
		assertEquals(97, primes.get(primes.size() - 1), "Last prime number below 100 should be 97");
	}
}

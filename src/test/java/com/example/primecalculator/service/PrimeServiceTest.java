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
		// given
		int upperBound = 1;

		// when
		List<Integer> primes = primeService.calculatePrimes(upperBound);

		// then
		assertTrue(primes.isEmpty(), "List should be empty for upperBound < 2");
	}

	@Test
	void calculatePrimes_WithUpperBound2_ShouldReturnListContainingOnly2() {
		// given
		int upperBound = 2;

		// when
		List<Integer> primes = primeService.calculatePrimes(upperBound);

		// then
		assertEquals(Arrays.asList(2), primes, "List should only contain number 2");
	}

	@Test
	void calculatePrimes_WithUpperBound10_ShouldReturnCorrectPrimes() {
		// given
		int upperBound = 10;
		List<Integer> expectedPrimes = Arrays.asList(2, 3, 5, 7);

		// when
		List<Integer> primes = primeService.calculatePrimes(upperBound);

		// then
		assertEquals(expectedPrimes, primes, "List should contain correct prime numbers up to 10");
	}

	@Test
	void calculatePrimes_WithUpperBound30_ShouldReturnCorrectPrimes() {
		// given
		int upperBound = 30;
		List<Integer> expectedPrimes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);

		// when
		List<Integer> primes = primeService.calculatePrimes(upperBound);

		// then
		assertEquals(expectedPrimes, primes, "List should contain correct prime numbers up to 30");
	}

	@Test
	void calculatePrimes_WithLargeUpperBound_ShouldReturnPrimes() {
		// given
		int upperBound = 100;
		List<Integer> expectedFirstPrimes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);

		// when
		List<Integer> primes = primeService.calculatePrimes(upperBound);

		// then
		assertEquals(expectedFirstPrimes, primes.subList(0, 10), "First 10 prime numbers should be correct");
		assertEquals(97, primes.get(primes.size() - 1), "Last prime number below 100 should be 97");
	}

	@Test
	void calculatePrimes_WithUpperBoundZeroOrNegative_ShouldReturnEmptyList() {
		// given
		int upperBoundZero = 0;
		int upperBoundNegative = -10;

		// when
		List<Integer> primesZero = primeService.calculatePrimes(upperBoundZero);
		List<Integer> primesNegative = primeService.calculatePrimes(upperBoundNegative);

		// then
		assertTrue(primesZero.isEmpty(), "List should be empty if upperBound = 0");
		assertTrue(primesNegative.isEmpty(), "List should be empty if upperBound = -10");
	}
}

package com.example.primecalculator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrimeService {
	public List<Integer> calculatePrimes(int upperBound) {
		List<Integer> primes = new ArrayList<>();
		if (upperBound < 2) {
			return primes;
		}

		boolean[] isComposite = new boolean[upperBound + 1];
		int sqrt = (int) Math.sqrt(upperBound);

		for (int i = 2; i <= sqrt; i++) {
			if (!isComposite[i]) {
				for (int multiple = i * i; multiple <= upperBound; multiple += i) {
					isComposite[multiple] = true;
				}
			}
		}

		for (int i = 2; i <= upperBound; i++) {
			if (!isComposite[i]) {
				primes.add(i);
			}
		}

		return primes;
	}
}

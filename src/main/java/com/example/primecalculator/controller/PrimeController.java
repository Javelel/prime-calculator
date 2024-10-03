package com.example.primecalculator.controller;

import com.example.primecalculator.service.PrimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PrimeController {
	private final PrimeService primeService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/calculate")
	public String calculatePrimes(@RequestParam("upperBound") Integer upperBound, Model model) {
			if (upperBound == null || upperBound < 2) {
				model.addAttribute("error", "Please enter an integer greater than or equal to 2.");
				return "index";
			}
			List<Integer> primes = primeService.calculatePrimes(upperBound);
			model.addAttribute("upperBound", upperBound);
			model.addAttribute("primes", primes);
		return "index";
	}
}

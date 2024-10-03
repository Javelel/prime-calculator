package com.example.primecalculator.controller;

import com.example.primecalculator.service.PrimeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrimeController.class)
public class PrimeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PrimeService primeService;

	@Test
	@DisplayName("GET / powinien zwrócić stronę index")
	void testGetIndex() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().size(0));
	}

	@Test
	@DisplayName("POST /calculate z prawidłowym inputem powinien zwrócić listę liczb pierwszych")
	void testCalculatePrimesValidInput() throws Exception {
		int upperBound = 10;
		Mockito.when(primeService.calculatePrimes(upperBound)).thenReturn(Arrays.asList(2, 3, 5, 7));

		mockMvc.perform(post("/calculate")
						.param("upperBound", String.valueOf(upperBound)))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attributeExists("upperBound"))
				.andExpect(model().attributeExists("primes"))
				.andExpect(model().attribute("upperBound", upperBound))
				.andExpect(model().attribute("primes", Arrays.asList(2, 3, 5, 7)));

		verify(primeService, times(1)).calculatePrimes(upperBound);
	}

	@Test
	@DisplayName("POST /calculate z inputem mniejszym niż 2 powinien zwrócić błąd")
	void testCalculatePrimesInvalidInputBelow2() throws Exception {
		int upperBound = 1;

		mockMvc.perform(post("/calculate")
						.param("upperBound", String.valueOf(upperBound)))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attributeExists("error"))
				.andExpect(model().attribute("error", "Please enter an integer greater than or equal to 2."));

		verify(primeService, times(0)).calculatePrimes(anyInt());
	}

	@Test
	@DisplayName("POST /calculate z nieprawidłowym inputem (nie liczba) powinien zwrócić błąd")
	void testCalculatePrimesInvalidInputNonNumber() throws Exception {
		String invalidInput = "abc";

		mockMvc.perform(post("/calculate")
						.param("upperBound", invalidInput))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attributeExists("error"))
				.andExpect(model().attribute("error", "Invalid input. Please enter a valid integer."));

		verify(primeService, times(0)).calculatePrimes(anyInt());
	}
}

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
	@DisplayName("GET / should return index page")
	void testGetIndex() throws Exception {
		// when
		mockMvc.perform(get("/"))
				// then
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().size(0));
	}

	@Test
	@DisplayName("POST /calculate with correct input should return list of the primary numbers")
	void testCalculatePrimesValidInput() throws Exception {
		// given
		int upperBound = 10;
		Mockito.when(primeService.calculatePrimes(upperBound)).thenReturn(Arrays.asList(2, 3, 5, 7));

		// when
		mockMvc.perform(post("/calculate")
						.param("upperBound", String.valueOf(upperBound)))
				// then
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attributeExists("upperBound"))
				.andExpect(model().attributeExists("primes"))
				.andExpect(model().attribute("upperBound", upperBound))
				.andExpect(model().attribute("primes", Arrays.asList(2, 3, 5, 7)));

		// verify
		verify(primeService, times(1)).calculatePrimes(upperBound);
	}

	@Test
	@DisplayName("POST /calculate if input is lower than 2 should return error")
	void testCalculatePrimesInvalidInputBelow2() throws Exception {
		// given
		int upperBound = 1;

		// when
		mockMvc.perform(post("/calculate")
						.param("upperBound", String.valueOf(upperBound)))
				// then
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attributeExists("error"))
				.andExpect(model().attribute("error", "Please enter an integer greater than or equal to 2."));

		// verify
		verify(primeService, times(0)).calculatePrimes(anyInt());
	}

	@Test
	@DisplayName("POST /calculate if input is not a number should return error")
	void testCalculatePrimesInvalidInputNonNumber() throws Exception {
		// given
		String invalidInput = "abc";

		// when
		mockMvc.perform(post("/calculate")
						.param("upperBound", invalidInput))
				// then
				.andExpect(status().isBadRequest());

		// verify
		verify(primeService, times(0)).calculatePrimes(anyInt());
	}

	@Test
	@DisplayName("POST /calculate with missing upperBound should return error")
	void testCalculatePrimesMissingInput() throws Exception {
		// when
		mockMvc.perform(post("/calculate"))
				// then
				.andExpect(status().isBadRequest());

		// verify
		verify(primeService, times(0)).calculatePrimes(anyInt());
	}

	@Test
	@DisplayName("POST /calculate if upperBound = null should return error")
	void testCalculatePrimesNullInput() throws Exception {
		// given
		String upperBound = "";

		// when
		mockMvc.perform(post("/calculate")
						.param("upperBound", upperBound))
				// then
				.andExpect(status().isBadRequest());

		// verify
		verify(primeService, times(0)).calculatePrimes(anyInt());
	}
}

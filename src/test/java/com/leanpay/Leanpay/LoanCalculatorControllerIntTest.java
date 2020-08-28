package com.leanpay.Leanpay;

import static org.junit.Assert.*;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.leanpay.Leanpay.controller.LoanCalculatorController;
import com.leanpay.Leanpay.dto.DetailedResponseDto;
import com.leanpay.Leanpay.dto.LoanCalculatorRequestDto;
import com.leanpay.Leanpay.dto.ResponseDTO;
import com.leanpay.Leanpay.entity.LoanCalculatorRequest;
import com.leanpay.Leanpay.entity.LoanCalculatorResponse;
import com.leanpay.Leanpay.entity.MonthlyAccountDetailed;
import com.leanpay.Leanpay.service.LoanCalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoanCalculatorControllerIntTest {

	@MockBean
	private LoanCalculatorService service;

	@Autowired
	private MockMvc mvc;
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	String exampleRequestJson = "{\"amount\":\"1000.00\",\"numberOfMonths\":\"3\",\"monthlyInterestPercent\":\"5.00\"}";
	
	MonthlyAccountDetailed monthlyAccount1;
	MonthlyAccountDetailed monthlyAccount2;
	MonthlyAccountDetailed monthlyAccount3;

	List<MonthlyAccountDetailed> monthlyList;
	LoanCalculatorRequest request;
	LoanCalculatorResponse response;
	DetailedResponseDto detailedResponseDto;

	@Before
	public void initData() {
		monthlyAccount1 = new MonthlyAccountDetailed();
		monthlyAccount1.setBalanceOwed(668.05);
		monthlyAccount1.setInterestAmount(4.17);
		monthlyAccount1.setMonth(1);
		monthlyAccount1.setPaymentAmount(336.11);
		monthlyAccount1.setPrincipalAmount(331.95);

		monthlyAccount2 = new MonthlyAccountDetailed();
		monthlyAccount2.setBalanceOwed(334.72);
		monthlyAccount2.setInterestAmount(2.78);
		monthlyAccount2.setMonth(2);
		monthlyAccount2.setPaymentAmount(336.11);
		monthlyAccount2.setPrincipalAmount(333.33);

		monthlyAccount3 = new MonthlyAccountDetailed();
		monthlyAccount3.setBalanceOwed(0.0);
		monthlyAccount3.setInterestAmount(1.39);
		monthlyAccount3.setMonth(3);
		monthlyAccount3.setPaymentAmount(336.11);
		monthlyAccount3.setPrincipalAmount(334.72);

		monthlyList = new ArrayList<>();
		monthlyList.add(monthlyAccount1);
		monthlyList.add(monthlyAccount2);
		monthlyList.add(monthlyAccount3);

		response = new LoanCalculatorResponse();
		response.setAmount(1000.0f);
		response.setInterestAmount(8.34);
		response.setTotalAmount(1008.34);

		response.setDetailedItems(monthlyList);

		request = new LoanCalculatorRequest();
		request.setAmount(1000.0f);
		request.setMonthlyInterestPercent(5.0f);
		request.setNumberOfMonths(3);
		request.setLc_response(response);

		detailedResponseDto = new DetailedResponseDto();
		detailedResponseDto.setAmount(1000.00);
		detailedResponseDto.setInterestAmount(8.34);
		detailedResponseDto.setTotalAmount(1008.34);
		// detailedResponseDto.setItems(monthlyList);

	}

	@Test
	public void whenValidInputInstallmentPlan_thenReturns200() throws Exception {

		Mockito.when(service.save(Mockito.any(LoanCalculatorRequestDto.class))).thenReturn(request);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/installment-plan")
				.accept(MediaType.APPLICATION_JSON).content(exampleRequestJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void whenValidInputInstallmentPlan_thenReturnsValidResponse() throws Exception {

		Mockito.when(service.save(Mockito.any(LoanCalculatorRequestDto.class))).thenReturn(request);

		
		mvc.perform(post("/installment-plan")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.amount").value(request.getAmount()))
				.andExpect(jsonPath("$.totalAmount").value(request.getLc_response().getTotalAmount()))
				.andExpect(jsonPath("$.interestAmount").value(request.getLc_response().getInterestAmount()))
				.andExpect(jsonPath("$.items[0].month").value(1))
				.andExpect(jsonPath("$.items[0].paymentAmount").value(336.11))
				.andExpect(jsonPath("$.items[1].month").value(2))
				.andExpect(jsonPath("$.items[1].paymentAmount").value(336.11))
				.andExpect(jsonPath("$.items[2].month").value(3))
				.andExpect(jsonPath("$.items[2].paymentAmount").value(336.11))
				;
	}
	
	@Test
	public void whenValidInputInstallmentPlanDetailed_thenReturnsValidResponse() throws Exception {

		Mockito.when(service.save(Mockito.any(LoanCalculatorRequestDto.class))).thenReturn(request);

		
		mvc.perform(post("/installment-plan-detailed")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.amount").value(request.getAmount()))
				.andExpect(jsonPath("$.totalAmount").value(request.getLc_response().getTotalAmount()))
				.andExpect(jsonPath("$.interestAmount").value(request.getLc_response().getInterestAmount()))
				
				.andExpect(jsonPath("$.items[0].month").value(1))
				.andExpect(jsonPath("$.items[0].paymentAmount").value(336.11))
				.andExpect(jsonPath("$.items[0].principalAmount").value(331.95))
				.andExpect(jsonPath("$.items[0].interestAmount").value(4.17))
				.andExpect(jsonPath("$.items[0].balanceOwed").value(668.05))
				
				.andExpect(jsonPath("$.items[1].month").value(2))
				.andExpect(jsonPath("$.items[1].paymentAmount").value(336.11))
				.andExpect(jsonPath("$.items[1].principalAmount").value(333.33))
				.andExpect(jsonPath("$.items[1].interestAmount").value(2.78))
				.andExpect(jsonPath("$.items[1].balanceOwed").value(334.72))
				
				.andExpect(jsonPath("$.items[2].month").value(3))
				.andExpect(jsonPath("$.items[2].paymentAmount").value(336.11))
				.andExpect(jsonPath("$.items[2].principalAmount").value(334.72))
				.andExpect(jsonPath("$.items[2].interestAmount").value(1.39))
				.andExpect(jsonPath("$.items[2].balanceOwed").value(0.0))
				
				;
	}

	
	@Test
	public void whenPostRequestToINstallmentPlanDetailedAndValidRequest_thenCorrectResponse() throws Exception {

	    mvc.perform(MockMvcRequestBuilders.post("/installment-plan-detailed")
	      .content(exampleRequestJson)
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(MockMvcResultMatchers.status().isOk())
	      .andExpect(MockMvcResultMatchers.content()
	        .contentType(MediaType.APPLICATION_JSON));
	}
}

package com.cg.timecard.controllers;


import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cg.timecard.entities.TimecardDetails;
import com.cg.timecard.repositories.TimecardDetailsRepository;
import com.cg.timecard.services.ITimecardDetailsService;
import com.cg.timecard.services.MapValidationErrorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TimecardDetailsController.class)


class TimecardControllerTest
{
	@Autowired
    private MockMvc mockMvc;
	@MockBean
	TimecardDetailsRepository timecarddetailsRepository;
	@MockBean
	private ITimecardDetailsService timecarddetailsService;
	@MockBean
	private MapValidationErrorService mapValidationErrorService;
	
	
	
	
	@Test
	public void testSaveTimecardDetails() throws Exception{
        String URI = "/timecard";
        TimecardDetails timecard= new TimecardDetails();
        
    
        timecard.setEmpId(100);
        timecard.setDate("10-20-2020");
        timecard.setHours(9);
        timecard.setId(123);
        timecard.setProjectId(12);
        timecard.setProjectName("java");
        timecard.setTimecardId("1");
        timecard.setTimecardStatus("pending");
        
        String jsonInput = this.converttoJson(timecard);   //??????????????????
        
        Mockito.when(timecarddetailsService.saveTimecardDetails(Mockito.any(TimecardDetails.class))).thenReturn(timecard);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
        
		}
	
	@Test
	public void testListByTimecardId() throws Exception {
		String URI= "/timecard/{timecardId}";			//????????????????????????
		TimecardDetails timecard= new TimecardDetails();
        
    	
        timecard.setEmpId(100);
        timecard.setDate("10-20-2020");
        timecard.setHours(9);
        timecard.setId(123);
        timecard.setProjectId(12);
        timecard.setProjectName("java");
        timecard.setTimecardId("1");
        timecard.setTimecardStatus("pending");
        
        String jsonInput = this.converttoJson(timecard); //?????????????????????
        
        Mockito.when(timecarddetailsService.listTimecardDetailsById(Mockito.any())).thenReturn(timecard);                 //?????????????????????????????????
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	
	

	@Test
	public void testUpdate() throws Exception {
		String URI= "/timecard/{timecardId}";   //???????????????????
		
TimecardDetails timecard= new TimecardDetails();
        
    	
        timecard.setEmpId(100);
        timecard.setDate("10-20-2020");
        timecard.setHours(20);
        timecard.setId(123);
        timecard.setProjectId(12);
        timecard.setProjectName("java");
        timecard.setTimecardId("1");
        timecard.setTimecardStatus("pending");

        String jsonInput = this.converttoJson(timecard);  //????????????????

        
        Mockito.when(timecarddetailsService.listTimecardDetailsById(Mockito.any())).thenReturn(timecard);
        Mockito.when(timecarddetailsService.saveTimecardDetails(Mockito.any(TimecardDetails.class))).thenReturn(timecard);
        Mockito.when(timecarddetailsService.update(timecard)).thenReturn(timecard);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,100).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	private String converttoJson(Object timecard) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(timecard);
	}
	
	
	

}

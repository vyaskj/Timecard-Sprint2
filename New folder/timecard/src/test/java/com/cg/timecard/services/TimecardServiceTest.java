package com.cg.timecard.services;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.timecard.entities.TimecardDetails;
import com.cg.timecard.exception.TimecardIdException;
import com.cg.timecard.repositories.TimecardDetailsRepository;
import com.cg.timecard.services.ITimecardDetailsService;

@ExtendWith(SpringExtension.class)
@SpringBootTest

class TimecardServiceTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@MockBean
	private TimecardDetailsRepository timecardRepository;
	@Autowired
	private ITimecardDetailsService timecardService;
	
	@Test
	
	void testSaveTimeCard() 
	{
	TimecardDetails timecard = new TimecardDetails();
	Date objDate = new Date();
    timecard.setEmpId(100);
    timecard.setDate("10-20-2020");
    timecard.setHours(20);
    timecard.setId(123);
    timecard.setProjectId(12);
    timecard.setProjectName("java");
    timecard.setTimecardId("a1");
    timecard.setTimecardStatus("true");
    Mockito.when(timecardRepository.save(timecard)).thenReturn(timecard);
	assertThat(timecardService.saveTimecardDetails(timecard)).isEqualTo(timecard);	
}
	
	@Test
	 void testListTimecardDetailsById() throws TimecardIdException{
		

		TimecardDetails timecard = new TimecardDetails();
		Date objDate = new Date();
	    timecard.setEmpId(100);
	    timecard.setDate("10-20-2020");
	    timecard.setHours(20);
	    timecard.setId(123);
	    timecard.setProjectId(12);
	    timecard.setProjectName("java");
	    timecard.setTimecardId("a1");
	    timecard.setTimecardStatus("true");
       Mockito.when(timecardRepository.findByTimecardId("a1")).thenReturn(timecard);
       assertThat(timecardService.listTimecardDetailsById("a1")).isEqualTo(timecard);
	}
	
	@Test
	 void testUpdate() throws TimecardIdException{
		TimecardDetails timecard = new TimecardDetails();
		Date objDate = new Date();
	    timecard.setEmpId(100);
	    timecard.setDate("10-20-2020");
	    timecard.setHours(20);
	    timecard.setId(123);
	    timecard.setProjectId(12);
	    timecard.setProjectName("java");
	    timecard.setTimecardId("a1");
	    timecard.setTimecardStatus("true");
		Mockito.when(timecardRepository.findByTimecardId("a1")).thenReturn(timecard);
		Mockito.when(timecardRepository.save(timecard)).thenReturn(timecard);
		assertThat(timecardService.update(timecard)).isEqualTo(timecard);		
	}
	@Test
	void test_insertNewTimeCrdDetail() {
		TimecardDetails timecard = new TimecardDetails();
		Date objDate = new Date();
	    timecard.setEmpId(100);
	    timecard.setDate("10-20-2020");
	    timecard.setHours(20);
	    timecard.setId(123);
	    timecard.setProjectId(12);
	    timecard.setProjectName("java");
	    timecard.setTimecardId("a1");
	    timecard.setTimecardStatus("true");
			
	}

}

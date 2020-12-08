package com.cg.timecard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.timecard.entities.TimecardDetails;
import com.cg.timecard.exception.TimecardIdException;
import com.cg.timecard.repositories.TimecardDetailsRepository;

@Service
public class TimecardDetailsService implements ITimecardDetailsService {
	@Autowired
	private TimecardDetailsRepository timecardDetailsRepository;
	
	/**This method adds time card details
	 * @param timecardDetails
	 */	
	public TimecardDetails saveTimecardDetails(TimecardDetails timecardDetails) {
		return timecardDetailsRepository.save(timecardDetails);		
	}
	
	@Override
	public TimecardDetails update(TimecardDetails timecardDetails) {
		return timecardDetailsRepository.save(timecardDetails);
				}

	/**This method finds time card details using time card ID
	 * @param timecardId
	 * @throws TimecardIdException 
	 */

	@Override
	public TimecardDetails listTimecardDetailsById(String timecardId) throws TimecardIdException{  
		TimecardDetails timecardDetails=timecardDetailsRepository.findByTimecardId(timecardId);
		if(timecardDetails==null) {
			throw new TimecardIdException("Time card id is not found"+timecardId);
		}
		return timecardDetails;
	}
	
}
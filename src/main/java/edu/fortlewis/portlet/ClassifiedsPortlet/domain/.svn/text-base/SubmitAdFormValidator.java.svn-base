package edu.fortlewis.portlet.ClassifiedsPortlet.domain;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Ad;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.*;                     
import java.util.Date;

public class SubmitAdFormValidator implements Validator {

    public boolean supports(Class clazz) {
        return Ad.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
    	
    	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category",
         "required.category");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
                "required.title");
        ValidationUtils.rejectIfEmpty(errors, "startDate",
                "required.startDate");
        ValidationUtils.rejectIfEmpty(errors, "endDate",
                "required.endDate");
         ValidationUtils.rejectIfEmpty(errors, "description",
                "required.description");
             
        
        Ad form = (Ad) target;
        
        if (form.getPolicyAccepted() == false) {
        	
        	errors.rejectValue("policyAccepted", "required.policyAccepted");
        }
        
        if (form.getStartDate() != null && form.getEndDate() != null
                && !form.getStartDate().before(form.getEndDate())) {
            errors.rejectValue("startDate", "invalid.startDateAfterEndDate");
        }
        
        Calendar cal = Calendar.getInstance(); 
        Date today = new Date();
        
        
        cal.setTime(today);
        cal.add(Calendar.DATE, 60);
        Date maxStartDate = cal.getTime();
         
        if (form.getStartDate() != null && form.getStartDate().after(maxStartDate)) {
            errors.rejectValue("startDate", "invalid.startDatePastMaximumDays");
        }
        
        cal.setTime(today);
        cal.add(Calendar.DATE, 120);
        Date maxEndDate = cal.getTime();
         
        if ( form.getEndDate() != null && form.getEndDate().after(maxEndDate)) {
            errors.rejectValue("startDate", "invalid.endDatePastMaximumDays");
        }
       
        if (form.getDescription().length() > 2056 ) {
        	errors.rejectValue("description", "tolong.description");
        };
        
        if (form.getCategory()== null) {
        	errors.rejectValue("category", "required.category");
        };
        	
        
    }
    
}

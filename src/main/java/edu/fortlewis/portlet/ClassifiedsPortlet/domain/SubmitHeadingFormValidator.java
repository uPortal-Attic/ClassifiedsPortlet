package edu.fortlewis.portlet.ClassifiedsPortlet.domain;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Heading;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.*;                     
import java.util.Date;

public class SubmitHeadingFormValidator implements Validator {

    public boolean supports(Class clazz) {
        return Ad.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "headingname",
                "required.headingname");
        
         Heading form = (Heading) target;
       
        if (form.getHeadingname().length() > 36 ) {
        	errors.rejectValue("headingname", "tolong.heading");
        };
        
    }
    
}

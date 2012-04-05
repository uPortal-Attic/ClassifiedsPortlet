package edu.fortlewis.portlet.ClassifiedsPortlet.domain;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Category;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SubmitCategoryFormValidator implements Validator {

    public boolean supports(Class clazz) {
        return Ad.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
    	
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "required.category");
            
        Category form = (Category) target;
       
        if (form.getName().length() > 36 ) {
        	errors.rejectValue("name", "tolong.category");
        };
        
    }
    
}

package edu.fortlewis.portlet.ClassifiedsPortlet.service;

import java.util.List;


import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Heading;

public interface HeadingService {

    public List<Heading> getHeadings();
    public List<Heading> getHeading(Long  id);
    public void processHeading(Heading heading);
    public void delete(Long id);
   
}

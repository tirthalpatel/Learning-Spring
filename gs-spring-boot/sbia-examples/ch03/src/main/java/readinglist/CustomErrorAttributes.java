package readinglist;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(
      RequestAttributes requestAttributes, boolean includeStackTrace) {
    Map<String, Object> attributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
    
    attributes.put("customErrorMsg", "There seems to be a problem with the page you requested");
    
    return attributes;
  }
  
}

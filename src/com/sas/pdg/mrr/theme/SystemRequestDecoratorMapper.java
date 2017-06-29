package com.sas.pdg.mrr.theme;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;

public class SystemRequestDecoratorMapper extends AbstractDecoratorMapper {
  private String decoratorParameter = null;

  public void init(Config config, Properties properties, DecoratorMapper parent) throws InstantiationException {
    super.init(config, properties, parent);
    decoratorParameter = properties.getProperty("decorator.parameter", "decorator");
  }

  public Decorator getDecorator(HttpServletRequest request, Page page) {
    Decorator result = null;
    String decorator = (String) request.getAttribute(decoratorParameter);

    if (decorator != null) {
      result = getNamedDecorator(request, decorator);
    }
        
    return result == null ? super.getDecorator(request, page) : result;
  }
}

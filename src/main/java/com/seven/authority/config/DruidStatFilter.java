package com.seven.authority.config;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * druid过滤器.
 * @author Administrator
 *
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
    initParams={
            @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
     }
)
public class DruidStatFilter extends DruidStatProperties.WebStatFilter {
  
}
package com.janeirodigital.xform.webdriver.common;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:dev.properties" // mention the property file name
})
public interface Environment extends Config  {
        String url();
        String urlMiddleOffice();
        String config();
        String redisServerHost();
        String redisServerPort();
}

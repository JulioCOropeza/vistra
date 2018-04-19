package com.janeirodigital.vistra.webdriver.actions;

import com.janeirodigital.xform.webdriver.actions.LogActions;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.common.Environment;
import com.janeirodigital.xform.webdriver.objectRepository.RedisHistoriesTCData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class RedisInjectionActions {
    private Common common;
    private static final Logger logger = LoggerFactory.getLogger(LogActions.class);

    public RedisInjectionActions(){

    }

    public void setRedisHistories (RedisHistoriesTCData tcData, Environment testEnvironment ){
        Jedis jedis = new Jedis(testEnvironment.redisServerHost(),Integer.parseInt(testEnvironment.redisServerPort()));
        //jedis.set(tcData.getServerId(), tcData.getCurrentValue());
        jedis.zadd(" history_" + tcData.getServerId(), Double.parseDouble( tcData.getUnixTimeStamp()), tcData.getCurrentValue());

    }
}

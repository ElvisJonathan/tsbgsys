package com.tsbg.mis.ecoService.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


@Service
public class SessionService {
    @Autowired
    private RedisService redisService;

    @Value("${server.servlet.session.timeout}")
    private long sessionExpire;

    private static final Logger LOG = LoggerFactory.getLogger(SessionService.class);

    public boolean checkSession(String sessionKey) {
        LOG.info("checkSession::sessionKey = [{}]", sessionKey);
        //非空检查
        if (StringUtils.isEmpty(sessionKey)) {
            return false;
        }
        return true;
    }


    /**
     * 重置session生存时间，默认10分钟
     *
     * @param sessionKey
     */
    public void resetTokenExpire(String sessionKey) {
        redisService.updateExpire(sessionKey, sessionExpire);
    }

    public void putFlowValue(String flowId, String key, String value) {
        Map<String, Object> values = redisService.getCacheMap(flowId);
        if (values == null) {
            values = new HashMap<>();
        }
        values.put(key, value);
        redisService.setCacheMap(key, values);
    }

    public void putFlowValues(String flowId, Map<String, Object> values) {
        redisService.setCacheMap(flowId, values);
    }

    public Map<String, Object> getFlowValues(String flowId) {
        Map<String, Object> values = redisService.getCacheMap(flowId);
        if (values == null) {
            values = new HashMap<>();
        }
        return values;
    }


    public Object getFlowValue(String flowId, String key) {
        return redisService.getCacheMap(flowId).get(key);
    }

    public void removeFlowValue(String flowId, String key) {
        redisService.deleteCacheMapValue(flowId, key);
    }

}

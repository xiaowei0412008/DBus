package com.creditease.dbus.commons.log.processor.rule.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.creditease.dbus.commons.log.processor.parse.ParseResult;
import com.creditease.dbus.commons.log.processor.parse.ParseRuleGrammar;
import com.creditease.dbus.commons.log.processor.parse.RuleGrammar;
import com.creditease.dbus.commons.log.processor.rule.IRule;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ExcludeFieldRule implements IRule {

    public List<String> transform(List<String> data, List<RuleGrammar> grammar, Rules ruleType) throws Exception{
        List<String> ret = new LinkedList<>();
        JSONObject jsonObject = JSON.parseObject(data.get(0));
        //TODO 目前先写死，后续可改
        jsonObject.remove("path");
        jsonObject.remove("@timestamp");
        jsonObject.remove("@version");
        jsonObject.remove("host");
        jsonObject.remove("message");
        jsonObject.remove("type");
        ret.add(JSON.toJSONString(jsonObject));
        return ret;
    }


    public static void main(String[] args) {
        String str = "{\"ums_op_\":\"i\",\"col_6\":\"2017/10/23 13:27:55.934 \",\"col_5\":\"2017-10-23T05:27:56.408Z\",\"col_4\":\"7476.473\",\"col_3\":\"1508728999461\",\"col_2\":\"1508736475934\",\"col_1\":\"0\",\"ums_ts_\":\"2017-10-23 13:27:55.000\",\"col_0\":\"/DBus/HeartBeat/Monitor/maasoracle/DBUS/DB_HEARTBEAT_MONITOR\",\"namespace\":\"heartbeat_log.dbus.heartbeat_log_table.0.0.0\",\"ums_id_\":\"107107739238931139\",\"ums_uid_\":\"559521\"}";
        JSONObject jsonObject = JSON.parseObject(str);
        if(StringUtils.contains(jsonObject.getString("namespace"), "heartbeat_log.dbus.heartbeat_log_table.0.0.0")) {
            System.out.println(true);
        }
    }
}

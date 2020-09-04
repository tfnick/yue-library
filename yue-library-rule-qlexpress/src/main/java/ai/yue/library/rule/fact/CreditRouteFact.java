package ai.yue.library.rule.fact;

import ai.yue.library.rule.dictionary.CapitalProduct;
import ai.yue.library.rule.utils.Lists;

import java.util.*;

public class CreditRouteFact {
    //流量参数
    String utmSource = "";
    String utmMedium = "";
    String action = "credit";
    Long current = System.currentTimeMillis();
    String sex = "M";
    Integer age = 22;
    String workCityCode = "440300";
    String memberLevel = "金牌";
    String phone = "13800138000";
    //客户端参数
    String fixCapitalNo = CapitalProduct.AABB201101.name();
    List<String> excludes = Lists.asList(CapitalProduct.AABB201101.name(), CapitalProduct.AABB201201.name());
    //我方规则要求

    //评分规则要求

    //资方规则要求

    //未匹配策略参数
    Boolean reserve = true;
    String reserveCapitalNo = CapitalProduct.AABB201101.name();
    Boolean demote = true;
    List<String> demoteCapitals = Lists.asList(CapitalProduct.BABB201202.name());
}

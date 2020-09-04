package ai.yue.library.rule.fact;

import java.util.HashMap;
import java.util.Map;

public class CreditRouteFact {
    //服务端输入动态字段-资金方个性化参数
    Map<String, Object> ips = new HashMap<>();
    //匹配模式
    String mode;
    //匹配字段-资金方共享
    boolean x1;                         //来源-客户端
    Integer x2;                         //来源-客户端
    Long x3;                            //来源-客户端
    String date;//yyyy-MM-dd HH:mm:ss   //来源-客户端

    Double y1;                          //来源-DB
    Double y2;                          //来源-DB
    String sTime;//08:00 业务开放时间     //来源-DB
    String eTime;//18:00                //来源-DB

    //输出字段
    Long capitalId;
    Double score;

    public Map<String, Object> getIps() {
        return ips;
    }

    public void setIps(Map<String, Object> ips) {
        this.ips = ips;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isX1() {
        return x1;
    }

    public void setX1(boolean x1) {
        this.x1 = x1;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    public Long getX3() {
        return x3;
    }

    public void setX3(Long x3) {
        this.x3 = x3;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getY1() {
        return y1;
    }

    public void setY1(Double y1) {
        this.y1 = y1;
    }

    public Double getY2() {
        return y2;
    }

    public void setY2(Double y2) {
        this.y2 = y2;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public Long getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(Long capitalId) {
        this.capitalId = capitalId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}

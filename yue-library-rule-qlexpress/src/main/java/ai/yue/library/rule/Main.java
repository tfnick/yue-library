package ai.yue.library.rule;

import ai.yue.library.rule.fact.CreditRouteFact;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

public class Main {
    public static void main(String[] args) throws Exception{
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a",1);
        context.put("b",2);
        context.put("c",3);
        String express = "a+b*c";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);

        CreditRouteFact fact = new CreditRouteFact();
        fact.setX1(true);
        fact.setX2(100);
        fact.setX3(200L);
        fact.getIps().put("custLevel", 5);
        context.put("fact", fact);

        express = "fact.x2 + fact.x3";
        r = runner.execute(express, context, null, true, false);
        System.out.println(r);

        express = "fact.x1 == true && fact.x2 > 100";
        r = runner.execute(express, context, null, true, false);
        System.out.println(r);

        express = "fact.ips.custLevel > 1 && fact.ips.custLevel < 8";
        r = runner.execute(express, context, null, true, false);
        System.out.println("custLevelRule -> " + r);
    }
}

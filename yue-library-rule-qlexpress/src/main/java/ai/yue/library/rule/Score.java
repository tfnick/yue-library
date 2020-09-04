package ai.yue.library.rule;

public interface Score {
    /**
     * 评价机制
     * @param expression
     * @param params
     * @return
     */
    Double score(String expression, ScoreParamBean params);
}

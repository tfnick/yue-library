package ai.yue.library.rule.dictionary;

public enum RecommendReason {
    //客户端固定路由
    CLIENT_FIX,
    //服务端固定路由
    SERVER_FIX,
    //服务端过滤路由
    SERVER_FILTER,
    //服务端兜底路由
    SERVER_RESERVE,
    //服务端降级路由（推荐非同类型产品，比如经营贷未匹配，推荐别的导流产品）
    SERVER_DEMOTE;

    RecommendReason() {
    }
}

package com.imageVerifyCode;

/**
 * 错误码.
 * 1000以下为系统常用错误码，请参考《行业应用中台错误码规范.docx》
 * 多容器集群纳管公共错误错误码范围 17000-17299，
 * 多容器集群纳管业务错误码范围 17300-17599
 */
public enum ErrorCode {

    /**
     * 系统常用错误码.
     */
    // 服务器请求正常
    CODE_0("0", "ok"),
    // 服务器异常
    CODE_01("-1", "Server exception"),
    // 服务器不可用
    CODE_1("1", "Server unavailable"),
    // Json 格式无效
    CODE_2("2", "JSON exception"),
    // 参数无效
    CODE_3("3", "Parameter is invalid:{}"),
    CODE_4("4", "Unauthorized"),
    CODE_5("5", "Token is invalid"),
    CODE_6("6", "Sign is invalid"),
    CODE_7("7", "To many Requests"),
    CODE_8("8", "Parameter is missing"),
    CODE_9("9", "Database Connection timed out"),
    CODE_10("10", "Database execution exception"),
    CODE_404("404", "Not found path"),
    CODE_11("11","17026"),
    /**
     * 自定义
     */
    CODE_17001("17001", "Service unavailable"),
    CODE_17002("17002", "JSON exception"),
    CODE_17003("17003", "Parameter is invalid"),
    CODE_17004("17004", "Db error"),
    CODE_17005("17005", "{0}参数不能为空"),
    CODE_17007("17007", "{0}参数有误"),
    CODE_17008("17008", "{0}重复"),
    CODE_17009("17009", "{0}不存在"),
    CODE_17010("17010", "{0}不一致"),
    CODE_17011("17011", "数据库执行异常"),
    CODE_17012("17012", "业务数据为空"),
    CODE_17013("17013", "查询{0}表返回数据为空"),
    CODE_17014("17014", "{0}已存在"),
    CODE_17015("17015", "{0} 下发失败"),
    USER_CREATE_FAILED("17016","项目关联仓库失败"),
    PROJECT_CREATE_FAILED("17017","harbor项目创建失败"),
    PROJECT_NAME_CONFICT("17018","该仓库名称已被他人使用"),
    DATA_DELETE_FAILED("17019","删除数据失败"),
    REGISTRY_CONFLICT("17020","该名称已存在"),
    REGISTRY_CREATE_FAILED("17021","连接失败"),
    REQUIRE_HARBOR_FAILED("17022","请求harbor失败"),
    PUSH_IMAGE_FAILED("17023","推送镜像失败"),
    PULL_IMAGE_FAILED("17024","拉取镜像失败"),
    DOCKER_DISCONNECT("17025","docker不可连接，无法进行操作"),
    TAG_IMAGE_FAILED("17026","tag镜像失败"),
    CODE_3001("3001", "Access token invalid"),
    CODE_3002("3002", "Access token expired"),
    CODE_5001("5001", "Parameter is missing"),
    CODE_5006("5006", "User not exist"),
    CODE_5008("5008", "Image format error"),
    CODE_5009("5009", "Image size error"),
    CODE_5010("5010", "File format error"),
    CODE_5011("5011", "File size error"),
    CODE_5014("5014", "Db error"),
    CODE_5016("5016", "该手机号码当天验证码获取超过限制，请改天尝试"),
    CODE_5017("5017", "获取验证码过于频繁，请5分钟后尝试"),
    CODE_5018("5018", "手机验证码校验失败"),
    CODE_5019("5019", "获取手机验证码失败"),
    CODE_5020("5020", "图片验证码不正确"),
    CODE_5021("5021", "图片验证码失效"),
    CODE_5022("5022", "该类型的文件已存在"),
    CODE_5023("5023", "上传的文件不是图片"),
    CODE_5024("5024", "上传文件失败"),
    CODE_5025("5025", "文件记录不存在"),
    CODE_5026("5026", "文件名过长"),
    CODE_5027("5027", "数据已存在"),
    CODE_5028("5028", "数据不存在"),
    CODE_5029("5029", "修改的密码不能与原始密码相同"),
    CODE_5030("5030", "调用第三方接口出错"),
    CODE_5031("5031", "用户密码不正确"),
    CODE_5032("5032", "该角色已关联用户，无法删除"),
    CODE_5033("5033", "邮件发送失败，请联系管理员！"),
    CODE_5034("5034", "用户手机号不存在"),
    CODE_5035("5035", "参数值不在范围内"),
    CODE_5036("5036", "该产品库存不足"),
    CODE_5037("5037", "发送事务消息异常"),
    CODE_5038("5038", "库存数不能为负数"),
    CODE_5039("5039", "用户积分不能为负数"),
    CODE_5040("5040", "文档id格式有误"),
    CODE_5041("5041", "该文档不存在或者不在当前版本");
    ;



    private final String errorCode;
    private final String errorMsg;

    ErrorCode(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

}

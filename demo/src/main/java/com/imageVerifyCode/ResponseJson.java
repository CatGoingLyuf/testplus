package com.imageVerifyCode;


/**
 * @author tangzy
 * @Title: ResponseJson
 * @ProjectName cloud-native-container-common
 * @Description: 输出json对象POJO类；
 * @date 2020/8/12 15:04
 */
public class ResponseJson {

    private String errorCode;
    private String errorMsg;
    private Object data;

    /**
     * 初始化构造方法ok
     */
    public ResponseJson(){
        this.errorCode = EnumCode.CODE_OK.getCode();
        this.errorMsg = EnumCode.CODE_OK.getText();
    }
    public ResponseJson(ErrorCode enumCode)
    {
        this.errorCode = enumCode.getCode();
        this.errorMsg = enumCode.getErrorMsg();
    }
    public ResponseJson(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ResponseJson(String errorCode, String errorMsg, Object data) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseJson{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }

    public enum EnumCode{
        /**
         * 服务器请求正常
         */
        CODE_OK("ok", "0"),
        /**
         * 服务不可用
         */
        CODE_UNAVAILABLE("Service unavailable", "1"),
        /**
         * 服务端异常
         */
        CODE_EXCEPTION("Server exception", "-1"),
        /**
         * Json格式无效
         */
        CODE_JSON_EXCEPTION("JSON exception", "2"),
        /**
         * 参数无效
         */
        CODE_PARAMETER_INVALID("Parameter is invalid", "3"),
    	/**
         * 404错误
         */
        CODE_NOTFOUND_EXCEPTION("Not found", "404");

        private String text;
        private String code = "0";

        private EnumCode(String text, String code){
            this.text = text;
            this.code = code;
        }

        public String getText() {
            return text;
        }

        public String getCode() {
            return code;
        }
    }


}

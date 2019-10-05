package cn.edu.mju.api.enums;

/**
 * 返回前台的状态信息
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
public enum  ResultEnum {

    SUCCESS(200,"success"),ERROR(500,"未知异常，请联系管理员");

    private int code;

    private String msg;

    ResultEnum(int code,String msg){
        this.code =  code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


}

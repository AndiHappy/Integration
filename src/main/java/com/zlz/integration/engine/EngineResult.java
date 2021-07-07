package com.zlz.integration.engine;

/**
 * 运行过程中的引擎结果值
 */
public class EngineResult {

    public static final EngineResult INI = new EngineResult(0, "OK");

    public static EngineResult Input_NULL = new EngineResult(10001, "input jsonObject is null");;

    private int code;
    private String msg;
    private Object result;

    public EngineResult(int code, String ok) {
        this.code = code;
        this.msg = ok;
    }
}

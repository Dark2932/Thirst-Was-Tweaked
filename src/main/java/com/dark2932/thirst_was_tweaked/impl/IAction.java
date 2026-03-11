package com.dark2932.thirst_was_tweaked.impl;

/**
 * @author Dark2932
 */
@FunctionalInterface
public interface IAction {

    /**
     * 执行对应操作 —— 例如修改玩家的口渴值、播放音效、发送消息等。
     * 多种行为需要通过一个通用的方式来触发时，此接口和此方法将派上用场。
     */
    void execute();

}
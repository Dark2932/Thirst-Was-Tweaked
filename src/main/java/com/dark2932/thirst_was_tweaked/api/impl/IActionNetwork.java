package com.dark2932.thirst_was_tweaked.api.impl;

/**
 * @author Dark2932
 */
public interface IActionNetwork extends IAction, IBufStorager {
    /*
      对于处理需要进行网络通信的行为，开发者必须使用此接口代替IAction。（例如PlayerInteractEvent.RightClickEmpty事件）
      这是由于处理网络数据包时，不能直接写入或读取IAction，故需要一个专门的接口来处理网络相关的行为。
      通过实现IActionNetwork接口，开发者可以继续在execute方法中编写行为，同时利用IBufStorager提供的方法来存储和读取对应数据。
     */
}

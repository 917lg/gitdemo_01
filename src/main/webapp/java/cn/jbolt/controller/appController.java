package cn.jbolt.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * <p> TODO</p >
 *
 * @author gong
 * @version V1.8.0
 * @date 2024/11/5 14:24
 */
public class appController extends Controller {

    public void index(){
        renderText("ttt");
    }
    @ActionKey("/ttt")
    public void mct(){
        renderText("mc");
    }
}

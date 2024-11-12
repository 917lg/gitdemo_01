package cn.jbolt;

import cn.jbolt.config.AppConfig;
import com.jfinal.core.JFinal;

/**
 * <p> TODO</p >
 *
 * @author gong
 * @version V1.8.0
 * @date 2024/11/4 17:11
 */
public class AppStarter {
    public static void main(String[] args) {
        JFinal.start("src/main/webapp",8081,"/");
    }
}

package cn.jbolt.config;

import cn.jbolt.controller.HelloController;
import cn.jbolt.controller.appController;
import cn.jbolt.model.MappingKit;
import cn.jbolt.model.books;
import cn.jbolt.model.btype;
import com.jfinal.config.*;
import com.jfinal.json.Json;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;

import java.time.LocalDateTime;


/**
 * <p> TODO</p >
 *
 * @author gong
 * @version V1.8.0
 * @date 2024/11/4 16:57
 */
public class AppConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {//配置JFinal常量值
        // 注册自定义的LocalDateTime序列化器
        constants.setJsonDatePattern("yyyy-MM-dd HH:mm:ss");
        PropKit.use("config.properties"); //加载配置文件
        //开启开发模式
        constants.setDevMode(true);
    }

    @Override
    public void configRoute(Routes routes) {//此方法用来配置访问路由
        routes.add("/hello", HelloController.class);
        routes.add("/tt", appController.class);
    }

    @Override
    public void configEngine(Engine engine) {//此方法用来配置Template Engine

    }

    @Override
    public void configPlugin(Plugins plugins) {//此方法用来配置JFinal的Plugin

        // 创建一个C3p0连接池插件，使用指定的数据库URL、用户名和密码
        C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://localhost/securtiy?characterEncoding=UTF-8&useSSL=false&zeroDateTimeBehavior=convertToNull", "root", "root");
        plugins.add(cp); // 将C3p0插件添加到插件管理器中

        // 创建一个ActiveRecord插件，使用前面创建的C3p0插件作为数据源
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        plugins.add(arp); // 将ActiveRecord插件添加到插件管理器中

        //创建一个EhCachePlugin缓存插件
        EhCachePlugin ep = new EhCachePlugin();
        plugins.add(ep);//将EhCachePlugin插件添加到插件管理器中

        //创建一个RedisPluginn缓存插件
        RedisPlugin redis = new RedisPlugin("test", "localhost");
        plugins.add(redis);//将RedisPluginn插件添加到插件管理器中(启用redis)

        MappingKit.mapping(arp);//所有映射在 MappingKit 中自动化搞定

        // 将数据库中的"books"表映射到books类，并指定主键为"bookID
//        arp.addMapping("books","bookID", books.class);
        // 将数据库中的"books"表映射到btype类，并指定主键为"tid"
//        arp.addMapping("btype", "tid", btype.class);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {//配置全局拦截器

    }

    @Override
    public void configHandler(Handlers handlers) {//此方法用来配置JFinal的Handler

    }
}

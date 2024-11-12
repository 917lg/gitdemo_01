package cn.jbolt.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * <p> TODO</p >
 *
 * @author gong
 * @version V1.8.0
 * @date 2024/11/6 14:06
 */
public class MappingKit {
    public static void mapping(ActiveRecordPlugin arp) {
        arp.addMapping("books", "bookID", books.class);
        arp.addMapping("btype", "tid", btype.class);
    }
}

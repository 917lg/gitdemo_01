package cn.jbolt.service;

import cn.jbolt.model.books;
import cn.jbolt.model.btype;

import java.util.List;

/**
 * <p> TODO</p >
 *
 * @author gong
 * @version V1.8.0
 * @date 2024/11/6 9:13
 */
public class btypeService {

    //该对象书全局共享的，只能用于数据库查询，不能承载对象
    private static final btype btypeDao = new btype().dao();

    public List<btype> btypeList(){
        String sql="select * from btype";
        List<btype> btypes = btypeDao.find(sql);
        return btypes;
    }

}

package cn.jbolt.service;

import cn.jbolt.model.books;
import cn.jbolt.utils.DateUtils;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p> TODO</p >
 *
 * @author gong
 * @version V1.8.0
 * @date 2024/11/5 17:17
 */
public class bookService {

    //该对象书全局共享的，只能用于数据库查询，不能承载对象
    private static final books bookDao = new books().dao();

    public List<books> listbooks() {

        String sql="SELECT bookID,bookName,bookCounts,detail,tname,booktime from books a , btype b where a.tid=b.tid";
        List<books> booksList = bookDao.find(sql);

        return booksList;
    }

    //分页获取一群人
    public Page<books> listPageBooks() {
        //当前页数1，每页两条
        return bookDao.paginate(1, 2, "select *", " from books");
    }

    //获取一个人
    public books getUserById(Integer id) {
        return bookDao.findById(id);
    }

    //以上内容全部是数据库查询操作，注：dao不能进行数据承载。
    //数据库更新操作不能使用dao去实现，可以通过Db工具类去实现。
    //数据承载需要通过new User().set("","");实现或者是Db.update()。Db同样也可以实现查询操作。

    //Db.update();插入数据
    public int addbook(){
        String sql="INSERT into books(bookName,bookCounts,booktime,detail,tid)VALUES('测试',5,NOW(),'测试中',1)";
        //方式一
        int update = Db.update(sql);
        return update;
    }

    //new books().set().set()...save();实现
    public boolean addbook2() {
        //方式二:不想写sql可以选择这种方式
        boolean bool = new books().set("bookName", "测试2").set("bookCounts", "5").set("booktime", new java.util.Date()).set("detail","测试中").set("tid","2").save();
        return bool;
    }

    public boolean addbook3() {
        //方式三:Db+Record实现数据更新
        Record record = new Record().set("bookName", "测试3").set("bookCounts", "6").set("booktime", new java.util.Date()).set("detail","测试中").set("tid","2");
        boolean books = Db.save("books", record);
        return books;
    }

    public boolean upbook(int i,String name){
        boolean update = new books().set("bookID", i).set("bookName", name).update();
        return update;
    }

    public boolean delbook(int i){
        boolean delete = new books().deleteById(i);
        return delete;
    }
    public void shiwu(){

        //开启事务
        Db.tx(() -> {
            boolean a=false;
            boolean b = new books().deleteById(5);
            boolean bool = new books().set("bookID",1).set("bookName", "测试2").set("bookCounts", "5").set("booktime", new Date()).set("detail","测试中").set("tid","2").save();
            if (b==true && bool==true){
                a=true;
            }
            return a;
        });


    }



}

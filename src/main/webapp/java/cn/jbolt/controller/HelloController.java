package cn.jbolt.controller;

import cn.jbolt.model.books;
import cn.jbolt.model.btype;
import cn.jbolt.service.bookService;
import cn.jbolt.service.btypeService;
import cn.jbolt.utils.BookValidator;
import cn.jbolt.utils.DemoInterceptor;
import cn.jbolt.utils.test;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.sun.xml.internal.txw2.TXW;


import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p >
 *
 * @author gong
 * @version V1.8.0
 * @date 2024/11/5 10:00
 */
public class HelloController extends Controller {

    bookService book=new bookService();

    btypeService btype=new btypeService();

    @ActionKey("/shiwu")
    public void shiwu(){
        book.shiwu();
    }

    @Before(CacheInterceptor.class)
    @CacheName("blogList")
    @ActionKey("/book")
    public void getBooks() {
        Cache test = Redis.use("test");
        test.set("1", "hello");
        Object o = test.get("1");
        List<books> listbooks = book.listbooks();
        setAttr("listbooks",listbooks);
        HashMap<String, Object> map = new HashMap<>();
        map.put("listbooks",listbooks);
        map.put("test",o);
        renderJson(map);
    }



    @ActionKey("/bookup")
    public void upBooks(){
        boolean up=false;
        Integer bookID = getParaToInt("bookID");
        String bookName = getPara("bookName");
        if (bookID!=null || bookID>0){
            up = book.upbook(bookID,bookName);
        }
        renderJson(up);
    }

    @ActionKey("/bookup2")
    public void upBooks2(){
        test bean = getBean(test.class, "");
        boolean up=false;
        Integer bookID = bean.getBookID();
        String bookName = bean.getBookName();
        if (bookID!=null || bookID>0){
            up = book.upbook(bookID,bookName);
        }
        renderJson(up);
    }

    @ActionKey("/bookdel")
    public void delBooks(){
        Integer bookID = getParaToInt("bookID");
        boolean del=false;
        if (bookID!=null || bookID>0){
            del = book.delbook(bookID);
        }
        renderJson(del);
    }

    @ActionKey("/bookpage")
    public void getBookspage(){
        Page<books> booksPage = book.listPageBooks();
        renderJson(booksPage);
    }

    @ActionKey("/getbook")
    public void getBooksone(){
        String bookIDs = getRequest().getParameter("bookID");
        int bookID = Integer.parseInt(bookIDs);
        books userById = book.getUserById(bookID);
        renderJson(userById);
    }

    @ActionKey("/getbook2")
    public void getBooksone2(){
        books model = getModel(books.class);
        books userById = book.getUserById(model.getInt("bookID"));
        renderJson(userById);
    }

    @ActionKey("/addbook")
    public void addBooks(){
        int addbook = book.addbook();
        renderJson(addbook);
    }

    @ActionKey("/addbook2")
    public void addBooks2(){
        boolean b = book.addbook2();
        renderJson(b);
    }

    @ActionKey("/addbook3")
    public void addBooks3(){
        boolean b = book.addbook3();
        renderJson(b);
    }

    @ActionKey("/btype")
    public void getbtype(){
        List<btype> btypes = btype.btypeList();
        renderJson(btypes);
    }

    @Before(DemoInterceptor.class)
    public void index(){
        System.out.println("hello world! jFinal");
        renderText("hello world! jFinal" );
    }


    @ActionKey("/long")
    public void test() {
        renderText("此方法是一个action");
    }

}

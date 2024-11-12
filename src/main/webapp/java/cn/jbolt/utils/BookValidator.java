package cn.jbolt.utils;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import java.io.IOException;

/**
 * <p> TODO</p >
 *
 * @author gong
 * @version V1.8.0
 * @date 2024/11/8 14:16
 */
public class BookValidator extends Validator implements Interceptor {
    @Override
    public void reset() {
        System.out.println("validate1");
    }

    @Override
    public void validate(Source source, Result result) throws SAXException, IOException {

        System.out.println("validate2");

    }

    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {

    }

    @Override
    public ErrorHandler getErrorHandler() {
        return null;
    }

    @Override
    public void setResourceResolver(LSResourceResolver resourceResolver) {

    }

    @Override
    public LSResourceResolver getResourceResolver() {
        return null;
    }

    @Override
    public void intercept(Invocation invocation) {
        System.out.println("validate3");
        invocation.invoke();
    }
}

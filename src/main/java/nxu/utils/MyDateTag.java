package nxu.utils;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspTagException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 业哥自定义标签的实现类(实现输出业哥定义格式的时间)
 */
public class MyDateTag extends TagSupport {

    // 对于有属性的标签，需要在标签处理类中设置同名的属性，并为属性设置get和set方法
    private String country;
    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();// 获取当前时间
            pageContext.getOut().write(country + "-" + city + " : " + sdf.format(date));
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}
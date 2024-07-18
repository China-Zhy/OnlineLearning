package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.bo.CourseDataBo;
import nxu.bo.CourseInfoBo;
import nxu.business.AppCourseService;
import nxu.business.AppCourseServiceImpl;
import nxu.entity.Course;
import nxu.entity.Notice;
import nxu.entity.Points;
import nxu.entity.User;
import nxu.service.*;
import nxu.service.impl.CourseServiceImpl;
import nxu.service.impl.NoticeServiceImpl;
import nxu.service.impl.PointsServiceImpl;
import nxu.service.impl.UserServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 课程应用层控制器 (张宏业)
 */
@WebServlet("/app/course/*")
public class AppCourseController extends BaseServlet {

    private static final AppCourseService app = new AppCourseServiceImpl();

    private static final CourseService courseService = new CourseServiceImpl();

    private static final UserService userService = new UserServiceImpl();

    private static final PointsService pointsService = new PointsServiceImpl();

    public static final NoticeService noticeService = new NoticeServiceImpl();

    // 前往后台管理页面
    public void goToLayuiAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getSession().getAttribute("Admin") != null) {
            req.getSession().setAttribute("isGoBack", false);
            req.getRequestDispatcher("/views/index.jsp").forward(req, resp);    // 前往后台管理系统
        } else {
            resp.sendRedirect("/views/user/userLogin.jsp");     // 前往登录页面
        }
    }

    // 前台页面根据课程种类的不同显示该种类的课程信息
    public void showCourseGroupByType(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<CourseDataBo> courseDataBo = app.getCourseDataBo();
        req.setAttribute("courseDataBo", courseDataBo);
        req.getRequestDispatcher("/pages/index.jsp").forward(req, resp);
    }

    // 用户点击某个课程链接后获取该课程的详情信息
    public void showCourseDetailsById(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        CourseInfoBo courseInfoBo = app.getCourseInfoBo(Integer.parseInt(req.getParameter("id")));
        if (req.getSession().getAttribute("Admin") != null) {
            User user = (User) req.getSession().getAttribute("Admin");
            int result = courseService.isUserHaveThisCourse(user.getId(), Integer.parseInt(req.getParameter("id")));
            boolean isHaveThisCourse = result == 1;     // 判断该用户是否拥有该课程
            req.setAttribute("isHaveThisCourse", isHaveThisCourse);
        }
        req.setAttribute("courseTypeName", req.getParameter("courseType"));
        req.setAttribute("courseInfoBo", courseInfoBo);
        req.getRequestDispatcher("/pages/courseInfo.jsp").forward(req, resp);
    }

    // 用户购买某课程
    public void userBuyCourseByCourseId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("Admin") == null) {

            resp.sendRedirect("/views/user/userLogin.jsp");    // 前往登录页面
        } else {
            User user = (User) req.getSession().getAttribute("Admin");
            int courseId = Integer.parseInt(req.getParameter("courseId"));

            HashMap<String, Object> map = new HashMap<>();
            map.put("pageIndex", 1);
            map.put("pageSize", 1);
            Course course = courseService.getCourse(map).getList().getFirst();  // 获取课程信息

            if (user.getScore() < course.getScore()) {  // 要判断用户拥有的积分是否足够购买该课程
                resp.sendRedirect("/pages/noScore.jsp");    // 购买失败
            } else {
                int result = courseService.insertData(user.getId(), courseId);

                User newUser = userService.queryUserById(user.getId());
                newUser.setState(0);    // 在UserDaoImpl中进行了逻辑判断
                newUser.setScore(-course.getScore());

                int isOk = userService.updateUser(newUser);
                if (result == 1 && isOk == 1) {
                    pointsService.insertPoints(new Points(0, user.getId(), "用户消费", -course.getScore(), null));
                    req.getSession().setAttribute("Admin", userService.queryUserById(user.getId()));
                    resp.sendRedirect("/pages/success.jsp");    // 购买成功
                } else {
                    resp.sendRedirect("/views/others/layuiError.jsp");    // 购买失败
                }
            }
        }
    }

    // 前往充值页面，顺便查询积分消费记录
    public void toRechargeScore(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 1000);
        map.put("userId", ((User) req.getSession().getAttribute("Admin")).getId());
        PageInfo<Points> allPoints = pointsService.getAllPoints(map);

        req.setAttribute("allPoints", allPoints.getList());
        req.getRequestDispatcher("/pages/recharge.jsp").forward(req, resp);
    }

    // 积分充值
    public void doRechargeScore(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObject = new JSONObject();
        String info;
        boolean result;
        if (req.getSession().getAttribute("Admin") != null) {
            User user = new User();
            user.setId(((User) req.getSession().getAttribute("Admin")).getId());
            user.setState(0);
            user.setScore(Integer.parseInt(req.getParameter("score")) * 100);
            int isOk = userService.updateUser(user);
            info = isOk > 0 ? "充值成功" : "充值失败";
            result = isOk > 0;
            if (result) {
                req.getSession().setAttribute("Admin", userService.queryUserById(user.getId()));
                pointsService.insertPoints(new Points(0, user.getId(), "用户充值", (Integer.parseInt(req.getParameter("score")) * 100), null));
            }
            jsonObject.put("result", result);
        } else {
            info = "充值失败，因为您还没有登录";
            jsonObject.put("result", false);
        }
        jsonObject.put("info", info);
        resp.setContentType("application/json;");
        resp.getWriter().write(jsonObject.toString());
    }

    // 获取某用户所拥有的全部课程
    public void getAllCourseByUserId(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getSession().getAttribute("Admin") == null) {
            resp.sendRedirect("/views/user/userLogin.jsp");    // 前往登录页面
        } else {
            User user = (User) req.getSession().getAttribute("Admin");
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId", user.getId());
            map.put("pageIndex", Integer.parseInt(req.getParameter("pageIndex")));
            map.put("pageSize", Integer.parseInt(req.getParameter("pageSize")));

            PageInfo<Course> course = courseService.getMyCourse(map);
            req.setAttribute("courses", course.getList());
            req.setAttribute("total", course.getTotal());
            req.setAttribute("pages", course.getPages());
            req.setAttribute("pageNum", course.getPageNum());
            req.getRequestDispatcher("/pages/myCourse.jsp").forward(req, resp);
        }
    }

    // 查看谋课程的公告(分页查询)
    public void getSomeCourseNotice(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HashMap<String, Object> map = new HashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 100);
        map.put("target", Integer.parseInt(req.getParameter("target")));
        PageInfo<Notice> notice = noticeService.getNotice(map);
        req.setAttribute("notices", notice.getList());
        req.setAttribute("courseName", req.getParameter("courseName"));
        req.getRequestDispatcher("/pages/courseNotice.jsp").forward(req, resp);
    }
}
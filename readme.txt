《基于Servlet&JSP的在线学习平台》

团队成员：张宏业、樊雪儿、胡昊、唐馨源

指导教师：周波【产品经理】

开发规范：
    ① 每个文件以 文档注释 形式标明 文件功能 和 作者 (XXX)，参数和返回值都要添加相应注释。
    ② 各模块按照 实体+功能层次 命名，例如 UserMapper、UserService、UserTest、UserController等。
    ③ 后台每个功能模块写在 web/views/你的功能模块名/模块页面。
    ④ 前台每个功能模块写在 web/pages/你的功能模块名/模块页面。
    ⑤ 后端页面命名规范：xxxTable、xxxInsert、xxxEdit等。

其他事项：
    ① file_info、homework_file、ownership这三个是关系表(多对多)，不存在entity。
    ② 开发顺序：mapper接口 → mapper.xml → service → serviceImpl → Test → controller → 浏览器。

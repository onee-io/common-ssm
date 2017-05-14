# CommonSSM -- 通用SSM项目架构

> 集成SSM框架的通用项目，用于新启项目的快速搭建，可根据实际需求对项目配置和依赖进行修改，
项目中实现了一个简单的用户信息增删改查示例，用于说明MyBatis的基本使用以及一些对应原则，
具体规范原则可参考项目`document`目录下的[阿里巴巴Java开发手册](./document/阿里巴巴Java开发手册.pdf)，
项目分别提供接口访问形式及页面展示，接口设计遵循RESTful规范，只用作使用方式的展示，
不做任何业务或校验处理，实现形式可用作参考，页面渲染部分抽取公共头部分，可在任意项目中渲染JSP时通用。

## 技术栈

MyBatis + Spring + SpringMVC + Redis

## 开发测试环境

IDEA 2017 + JDK 1.8 + Tomcat 8.0 + Maven 3.3.9 + MySQL 5.5 + Redis 3.2.5

## 目录结构说明

```
CommonSSM/
|-- src/
|   |── main/                       -- 源文件包
|   |   |── java/top/onee/ssm/
|   |   |   |-- common/             -- 存放公共类、公共常量等
|   |   |   |-- controller/         -- 存放Controllrer
|   |   |   |-- dao/                -- 存放DAO层接口类
|   |   |   |-- dto/                -- 存放用于数据传输的类
|   |   |   |-- entity/             -- 存放与数据表一一对应的实体类
|   |   |   |-- expand/
|   |   |   |   |-- aop/            -- 存放切面类
|   |   |   |   |-- enums/          -- 存放枚举类，如HTTP返回的状态码等
|   |   |   |   |-- excepiton/      -- 存放自定义异常类
|   |   |   |   └── util/           -- 存放自定义工具类
|   |   |   └── service/            -- 存放Service
|   |   |-- resources
|   |   |   |-- config/             -- 存放配置文件，如JDBC、Redis等
|   |   |   |-- mapper/             -- 存放DAO层接口实现文件
|   |   |   └── spring/             -- 存放配置SpringContext的所有文件
|   |   └── webapp/
|   |       |-- resources
|   |       |   |-- css             -- 存放CSS文件
|   |       |   |-- img             -- 存放图片
|   |       |   └── js              -- 存放JavaScript文件
|   |       |-- WEB-INF
|   |       |   |-- jsp/            -- 存放渲染页面
|   |       |   └── web.xml         -- 项目配置文件
|   |       └── index.jsp           -- 默认主页
|   └── test/                       -- 测试包
|       |── java/top/onee/ssm/      -- 与源文件目录结构对应
|       └── resources               -- 存放测试所需的资源文件
|-- document/                       -- 存放项目相关文档
└── pom.xml                         -- Maven依赖管理文件
```

## 已集成框架

- `spring` 4.3.5        -- Sring框架
- `spring mvc` 4.3.5    -- 前端控制器
- `logback` 1.1.8       -- 记录日志
- `logstash` 4.9        -- 记录日志到ELK
- `druid` 1.0.18        -- 数据库连接池
- `mybatis` 3.4.0       -- ORM框架
- `guava` 21.0          -- Google工具包
- `fastjson` 1.2.10     -- JSON转换
- `jedis` 2.8.2         -- Redis客户端
- `aspectj` 1.8.10      -- 支持切面
- `protostuff` 1.1.1    -- 序列化工具
- `pagehelper` 5.0.0    -- 分页插件
- `shiro` 1.3.2         -- 权限控制
- `zxing` 3.2.1         -- 二维码生成解析

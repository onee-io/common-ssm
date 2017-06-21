# CommonSSM -- 通用SSM独立项目架构

> 集成SSM框架的独立项目架构，不支持分布式部署，用于新启简单项目的快速搭建，可根据实际需求对项目配置和依赖进行修改，
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
|   |   |   |-- modal/              -- 存放POJO类
|   |   |   |   |-- BO/             -- 存放业务对象
|   |   |   |   |-- DO/             -- 存放数据表一一对应的对象
|   |   |   |   |-- DTO/            -- 存放数据传输对象
|   |   |   |   |-- Query/          -- 存放数据查询对象
|   |   |   |   └── VO/             -- 存放页面展示
|   |   |   |-- expand/
|   |   |   |   |-- aop/            -- 存放切面类
|   |   |   |   |-- enums/          -- 存放枚举类，如HTTP返回的状态码等
|   |   |   |   |-- excepiton/      -- 存放自定义异常类
|   |   |   |   └── util/           -- 存放自定义工具类
|   |   |   └── service/            -- 存放Service
|   |   |-- resources
|   |   |   |-- config/             -- 存放配置文件，如JDBC、Redis等
|   |   |   |-- mapper/             -- 存放DAO层接口实现文件
|   |   |   |-- mybatis-generator/  -- 存放自动生成实体类的工具
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

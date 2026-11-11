# 遗小课

> 学一项非遗，上一堂小课。

面向中小学生的非遗学习 APP。用卡片化展示、工序图解和小测验降低青少年了解非遗的门槛，用学习进度和打卡记录激励持续学习。

综合实训二 · 期末大作业

---

## 一、项目简介

| 项 | 内容 |
|---|---|
| APP 名称 | 遗小课 |
| 副标题 | 面向中小学生的非遗学习 APP |
| Slogan | 学一项非遗，上一堂小课 |
| 目标用户 | 小学高年级至初中学生，及陪同的家长 |
| 核心闭环 | 浏览项目 → 看工序与故事 → 做小测验 → 收藏/进度记录 → 打卡 |
| 技术路线 | 纯本地离线，SQLite 存储，无服务器依赖 |

**为什么做**：非遗传承面临青少年认知断层；调研显示中小学生对非遗了解少，但愿意通过图文和小测验学习。所以做一款卡片化、有测验和打卡激励的非遗学习 APP。

## 二、技术栈

| 项 | 选型 | 说明 |
|---|---|---|
| 语言 | Java | 课程要求 Android + Java |
| 构建 | Gradle 9.5 + AGP 9.3（Kotlin DSL） | Android Studio 默认生成，已实际编译通过 |
| 最低版本 | minSdk 24（Android 7.0） | 覆盖绝大多数在用设备 |
| 编译版本 | compileSdk 37 / targetSdk 37 | |
| 数据存储 | SQLite（SQLiteOpenHelper） | 三张表：project / favorite / study_record |
| 界面 | 原生 Activity + XML 布局 | RecyclerView + CardView 卡片列表 |
| 网络 | 无 | WebView 加载 assets 内本地 HTML |

## 三、功能范围

**必做（核心闭环）**

1. 非遗项目列表（分类卡片）
2. 项目详情页（历史、工序、传承人故事）
3. 小测验（3–5 道选择题）
4. 收藏
5. 学习进度
6. 打卡记录
7. 我的页面（收藏 / 进度 / 打卡汇总）

**加分（只做 1–2 个做深）**

- 打卡日历
- 搜索 / 分类筛选

**想法池（记录但不做，答辩作展望）**

语音朗读、地图导览、社区、上传作品、AI 推荐

## 四、目录结构

```
yixiaoke/
├── app/src/main/
│   ├── java/com/musu/yixiaoke/
│   │   ├── MainActivity.java          首页
│   │   ├── DetailActivity.java        详情页
│   │   ├── QuizActivity.java          测验页
│   │   ├── MineActivity.java          我的页面
│   │   ├── CheckinActivity.java       打卡页
│   │   ├── db/                        数据库（DBHelper、DAO）
│   │   ├── adapter/                   RecyclerView Adapter
│   │   ├── model/                     数据实体
│   │   └── util/                      工具类
│   ├── res/
│   │   ├── layout/                    布局
│   │   ├── drawable/                  图形资源
│   │   ├── values/                    颜色、字符串、主题
│   │   └── assets/                    非遗图文、本地 HTML
│   └── AndroidManifest.xml
├── docs/                              设计文档、调研、阶段留痕
└── README.md
```

## 五、分支规范

`main` 分支受保护，**禁止直接推送**，所有改动走功能分支 + PR。

```
feature/f0-scaffold          工程骨架与文档骨架
feature/f1-home-list        首页列表
feature/f2-detail           详情页
feature/f3-quiz             测验页
feature/f4-favorite         收藏
feature/f5-progress         学习进度
feature/f6-checkin          打卡
feature/f7-mine             我的页面
```

分支命名：`feature/<编号>-<功能短名>`，修 bug 用 `fix/<问题短名>`。

## 六、提交信息规范

提交信息带**阶段前缀**，便于答辩时展示"按软件工程分阶段推进"：

```
[S1调研] 补充问卷题目与样本说明
[S2选型] 确定 Java + SQLite 技术路线
[S3设计] 补数据库 ER 图
[S5编码] 首页 RecyclerView 卡片布局
[S5编码] 首页点击跳转详情并传 projectId
[S6测试] 修复重复打卡导致的记录异常
[S7文档] 完成详细设计章节
```

单个功能至少 3 次提交：布局 → 逻辑 → 修问题。

## 七、PR 流程

1. 从最新的 `main` 拉功能分支
2. 开发，多次提交
3. 推送分支，在 GitHub 开 PR，标题带阶段前缀
4. 自己 Review 变更（重点看 XML 布局和 Java 逻辑）→ Squash merge
5. 删除已合并的分支

## 八、小组分工

> 填写要求：课纲明确要求"写清楚和具体，切忌笼统、不落地"。每一行都要有**可交付物**。

| 成员 | 学号 | 负责内容 | 可交付物 | Git 提交 |
|---|---|---|---|---|
| 万维唯 | 2406100225 | 组长 · 主力开发/架构：架构设计、数据库与 DAO、内容浏览与测评核心代码、构建与 Git 管理 | `DBHelper`、三个 DAO、`MainActivity`/`DetailActivity`/`QuizActivity`、设计文档 | `feature/f1-home-list`、`f2-detail`、`f3-quiz` |
| 袁浩翔 | 2406100227 | 调研与内容：竞品分析、非遗资料采集与改写、8–12 个项目条目、调研报告 | `docs/调研报告.md`、`assets/projects.json` 内容、附录 A/B | `feature/content-data` |
| 张梓钧 | 2406100229 | 外围页面与视觉："我的"页面、打卡页界面、图标与配色资源、空态与加载态 | `MineActivity`、`CheckinActivity`、`res/drawable`、`res/values/colors.xml` | `feature/f7-mine`、`f6-checkin` |
| 赵元新 | 2406100231 | 测试与答辩材料：测试用例设计、真机测试执行与缺陷记录、PPT 与演示脚本 | `docs/测试报告.md`、缺陷清单、答辩 PPT | `fix/*` 缺陷修复提交、`docs/ppt` |

## 九、阶段计划与留痕

| 阶段 | 日期 | 交付物 | 状态 |
|---|---|---|---|
| S0 组织 | 11/11–11/12 | 仓库、工程骨架、README | 已完成 |
| S1 调研与需求 | 11/12–11/16 | 调研报告、需求说明、功能结构图 | 待开始 |
| S2 可行性与选型 | 11/17 | 技术选型表、保底方案 | 待开始 |
| S3 总体设计 | 11/17–11/19 | 模块划分、页面流程、ER 图 | 待开始 |
| S4 详细设计 | 11/19–11/20 | 类设计、知识点矩阵、UI 稿 | 待开始 |
| S5a 编码·骨架 | 11/23–11/29 | 列表→详情跳转跑通 | 待开始 |
| S5b 编码·数据 | 11/30–12/06 | 核心闭环完整 | 待开始 |
| S5c 编码·加分 | 12/07–12/11 | 知识点全覆盖，功能冻结 | 待开始 |
| S6 测试 | 12/12–12/14 | 测试报告 | 待开始 |
| S7 文档与答辩 | 12/12–12/17 | 设计文档、PPT、演示脚本 | 待开始 |
| S8 提交 | 12/18 | 打包提交 | 待开始 |

## 十、开发日志

| 日期 | 内容 |
|---|---|
| 2026-11-11 | 创建项目基础工程，上传 GitHub；移除误提交的 .idea 配置 |
| 2026-11-11 | 包名改为 `com.musu.yixiaoke`；补回重构时丢失的 Java 源码与测试文件；.gitignore 忽略 `.idea/` 与打包产物；加入 README 与设计文档骨架；`assembleDebug` 编译通过 |

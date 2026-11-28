package com.musu.yixiaoke.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* 该类为骨架期唯一数据出口*/
public final class SampleData {
    private static final List<Project> PROJECTS = new ArrayList<>();
    /*该文案仅为示例，最终内容以后续调研内容整理为准*/
    static {
        PROJECTS.add(new Project(1, "京剧脸谱", Project.CATEGORY_DRAMA, "北京", 3,
                "示例：一笔一色，把忠奸善恶画在脸上"));
        PROJECTS.add(new Project(2, "景泰蓝制作技艺", Project.CATEGORY_CRAFT, "北京", 3,
                "示例：铜胎掐丝，点蓝烧窑，一件器物上百道工序"));
        PROJECTS.add(new Project(3, "北京兔儿爷", Project.CATEGORY_FINE_ART, "北京", 1,
                "示例：中秋应节的泥塑小神，兔首人身披甲胄"));
        PROJECTS.add(new Project(4, "杨柳青木版年画", Project.CATEGORY_FINE_ART, "天津", 2,
                "示例：五色套印，把吉祥年景贴上门楣"));
        PROJECTS.add(new Project(5, "景德镇手工制瓷", Project.CATEGORY_CRAFT, "江西", 3,
                "示例：一坯二土，七十二道工序方能成瓷"));
        PROJECTS.add(new Project(6, "苏绣", Project.CATEGORY_CRAFT, "江苏", 2,
                "示例：一根丝线劈成十六股，针脚细过发丝"));
        PROJECTS.add(new Project(7, "皮影戏", Project.CATEGORY_DRAMA, "陕西", 2,
                "示例：灯影之下，一张驴皮演千军万马"));
        PROJECTS.add(new Project(8, "二十四节气", Project.CATEGORY_FOLK, "全国", 1,
                "示例：顺天应时的农耕时间表，至今仍在用"));
        PROJECTS.add(new Project(9, "祭灶", Project.CATEGORY_FOLK, "全国", 1,
                "示例：腊月廿三送灶王，糖瓜粘嘴多讲好话"));
    }
    //私有化构造函数，禁止外部new实例化
    private SampleData() {
    }

    public static List<Project> all() {
        return Collections.unmodifiableList(PROJECTS);
    }

    public static Project findById(int id) {
        for (Project project : PROJECTS) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }
}

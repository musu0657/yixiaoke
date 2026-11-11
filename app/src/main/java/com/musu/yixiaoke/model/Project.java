package com.musu.yixiaoke.model;

/**
 * 非遗项目实体。
 *
 * <p>当前数据由 {@code MainActivity} 内的示例数据提供（S5a 骨架阶段），
 * S5b 阶段改为从 SQLite 的 {@code project} 表读取，字段与表结构一一对应。</p>
 */
public class Project {

    /** 分类常量，同时用作筛选条件 */
    public static final String CATEGORY_ALL = "全部";
    public static final String CATEGORY_CRAFT = "传统技艺";
    public static final String CATEGORY_FINE_ART = "传统美术";
    public static final String CATEGORY_DRAMA = "传统戏剧";
    public static final String CATEGORY_FOLK = "民俗";

    private final String name;
    private final String category;
    private final String region;
    /** 难度：1 入门 / 2 进阶 / 3 挑战 */
    private final int difficulty;
    private final String summary;

    public Project(String name, String category, String region, int difficulty, String summary) {
        this.name = name;
        this.category = category;
        this.region = region;
        this.difficulty = difficulty;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getRegion() {
        return region;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getSummary() {
        return summary;
    }

    /** 封面占位文字：取项目名首字，后续替换为 assets 内的图片 */
    public String getCoverText() {
        return name.isEmpty() ? "非" : name.substring(0, 1);
    }
}

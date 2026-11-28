package com.musu.yixiaoke;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.musu.yixiaoke.adapter.ProjectAdapter;
import com.musu.yixiaoke.model.Project;
import com.musu.yixiaoke.model.SampleData;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页：分类筛选 + 非遗项目卡片列表。
 *
 * <p>S5a 骨架阶段，列表数据为示例数据；S5b 阶段改为由 DAO 从 SQLite 读取。
 * 点击卡片目前只给出提示，详情页在 S5a 后半段接入。</p>
 */
public class MainActivity extends AppCompatActivity {

    /** 分类顺序与 chips 的声明顺序一一对应 */
    private static final String[] CATEGORIES = {
            Project.CATEGORY_ALL,
            Project.CATEGORY_CRAFT,
            Project.CATEGORY_FINE_ART,
            Project.CATEGORY_DRAMA,
            Project.CATEGORY_FOLK
    };

    private final List<Project> allProjects = new ArrayList<>();
    private final List<Project> shownProjects = new ArrayList<>();
    private final List<TextView> chipViews = new ArrayList<>();

    private ProjectAdapter adapter;
    private TextView emptyView;
    private String currentCategory = Project.CATEGORY_ALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        applySystemBarInsets();
        bindChips();
        bindList();
        loadSampleProjects();
        selectCategory(0);
    }

    /** 边到边显示：把系统栏高度补成根布局内边距，避免内容被状态栏遮挡 */
    private void applySystemBarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 浅色背景 + 浅色主题，状态栏图标必须转深色，否则在深色系统模式下看不见
        WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView())
                .setAppearanceLightStatusBars(true);
    }

    private void bindChips() {
        chipViews.add(findViewById(R.id.chip_all));
        chipViews.add(findViewById(R.id.chip_craft));
        chipViews.add(findViewById(R.id.chip_fine_art));
        chipViews.add(findViewById(R.id.chip_drama));
        chipViews.add(findViewById(R.id.chip_folk));

        for (int i = 0; i < chipViews.size(); i++) {
            final int index = i;
            chipViews.get(i).setOnClickListener(v -> selectCategory(index));
        }
    }

    private void bindList() {
        emptyView = findViewById(R.id.empty_view);

        RecyclerView listView = findViewById(R.id.project_list);
        listView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectAdapter(shownProjects,
                project -> startActivity(DetailActivity.newIntent(this, project.getId())));
        listView.setAdapter(adapter);
    }

    /** 切换分类：更新选中态并刷新列表 */
    private void selectCategory(int index) {
        currentCategory = CATEGORIES[index];
        for (int i = 0; i < chipViews.size(); i++) {
            chipViews.get(i).setSelected(i == index);
        }
        applyFilter();
    }

    /** 按当前分类过滤，并控制空态显隐 */
    private void applyFilter() {
        shownProjects.clear();
        for (Project project : allProjects) {
            if (Project.CATEGORY_ALL.equals(currentCategory)
                    || currentCategory.equals(project.getCategory())) {
                shownProjects.add(project);
            }
        }
        adapter.notifyDataSetChanged();
        emptyView.setVisibility(shownProjects.isEmpty() ? View.VISIBLE : View.GONE);
    }

    /**
     * 示例数据：全国知名项目 + 北京本地项目。
     * S5b 阶段替换为 DBHelper 从 assets 导入后查 project 表。
     */
    private void loadSampleProjects() {
        allProjects.addAll(SampleData.all());
    }
}

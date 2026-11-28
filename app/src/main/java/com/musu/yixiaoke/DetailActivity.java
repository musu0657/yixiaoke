package com.musu.yixiaoke;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.musu.yixiaoke.model.Project;
import com.musu.yixiaoke.model.SampleData;

/**
 * 项目详情页。S5a 只展示列表已有的字段，历史渊源 / 工序 / 传承人故事在 S5b 接入内容后补。
 * 参数：projectId 通过 Intent extra 传入，取不到时直接关闭页面，避免空指针。
 */
public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_PROJECT_ID = "extra_project_id";
    private static final int INVALID_ID = -1;

    public static Intent newIntent(Context context, int projectId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_PROJECT_ID, projectId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        applySystemBarInsets();

        int projectId = getIntent().getIntExtra(EXTRA_PROJECT_ID, INVALID_ID);
        Project project = SampleData.findById(projectId);
        if (project == null) {
            finish();
            return;
        }
        bind(project);
    }

    private void applySystemBarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView())
                .setAppearanceLightStatusBars(true);
    }

    private void bind(Project project) {
        String[] labels = getResources().getStringArray(R.array.difficulty_labels);
        String difficulty = labels[project.getDifficultyIndex()];

        TextView name = findViewById(R.id.detail_name);
        TextView meta = findViewById(R.id.detail_meta);
        TextView summary = findViewById(R.id.detail_summary);

        name.setText(project.getName());
        meta.setText(getString(R.string.detail_meta_format,
                project.getCategory(), project.getRegion(), difficulty));
        summary.setText(project.getSummary());

        findViewById(R.id.detail_back).setOnClickListener(v -> finish());
    }
}
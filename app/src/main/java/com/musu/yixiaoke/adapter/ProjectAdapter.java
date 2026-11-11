package com.musu.yixiaoke.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.musu.yixiaoke.R;
import com.musu.yixiaoke.model.Project;

import java.util.List;

/**
 * 首页非遗项目列表适配器。
 *
 * <p>数据源由外部持有（{@code List<Project>}），分类切换后外部改内容再通知刷新，
 * 因此适配器不自己保存业务状态。</p>
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    /** 列表项点击回调，由 Activity 实现跳转逻辑 */
    public interface OnProjectClickListener {
        void onProjectClick(Project project);
    }

    private final List<Project> projects;
    private final OnProjectClickListener listener;

    public ProjectAdapter(List<Project> projects, OnProjectClickListener listener) {
        this.projects = projects;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.bind(projects.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {

        private final TextView coverText;
        private final TextView nameText;
        private final TextView summaryText;
        private final TextView metaText;
        private final TextView difficultyText;
        private final String[] difficultyLabels;

        ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            coverText = itemView.findViewById(R.id.tv_cover);
            nameText = itemView.findViewById(R.id.tv_name);
            summaryText = itemView.findViewById(R.id.tv_summary);
            metaText = itemView.findViewById(R.id.tv_meta);
            difficultyText = itemView.findViewById(R.id.tv_difficulty);
            difficultyLabels = itemView.getResources().getStringArray(R.array.difficulty_labels);
        }

        void bind(final Project project, final OnProjectClickListener listener) {
            coverText.setText(project.getCoverText());
            nameText.setText(project.getName());
            summaryText.setText(project.getSummary());
            metaText.setText(project.getCategory() + " · " + project.getRegion());

            final String label = labelOf(project.getDifficulty());
            difficultyText.setText(itemView.getContext()
                    .getString(R.string.difficulty_format, label));

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onProjectClick(project);
                }
            });
        }

        private String labelOf(int difficulty) {
            int index = difficulty - 1;
            if (index < 0 || index >= difficultyLabels.length) {
                index = 0;
            }
            return difficultyLabels[index];
        }
    }
}

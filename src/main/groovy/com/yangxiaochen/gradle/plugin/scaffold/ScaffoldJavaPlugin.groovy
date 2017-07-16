package com.yangxiaochen.gradle.plugin.scaffold

import com.yangxiaochen.gradle.plugin.scaffold.task.ModuleCreateTask
import com.yangxiaochen.gradle.plugin.scaffold.task.SourceSetCreateTask
import org.gradle.api.Plugin
import org.gradle.api.Project
/**
 * @author yangxiaochen
 * @date 2017/7/14 15:31
 */
class ScaffoldJavaPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.getTasks().create("createSrc", SourceSetCreateTask.class)
        project.getTasks().create("createModule", ModuleCreateTask.class)
    }
}



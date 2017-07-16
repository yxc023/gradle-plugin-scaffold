package com.yangxiaochen.gradle.plugin.scaffold

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
/**
 * @author yangxiaochen
 * @date 2017/7/14 15:31
 */
class ScaffoldJavaPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.getTasks().create("createSrc", SourceSetCreateTask.class)

    }
}

class SourceSetCreateTask extends DefaultTask {

    public static final String mainJava = "src/main/java";
    public static final String mainResource = "src/main/resources";
    public static final String testJava = "src/test/java";
    public static final String testResource = "src/test/resources";

    @TaskAction
    public void greet() {
        getProject().mkdir(mainJava);
        getProject().mkdir(mainResource);
        getProject().mkdir(testJava);
        getProject().mkdir(testResource);
    }

//    @Inject
    public SourceSetCreateTask() {
        setGroup("scaffold")
        setDescription("Generate src dirs.")
    }
}

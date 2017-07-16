package com.yangxiaochen.gradle.plugin.scaffold.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author yangxiaochen
 * @date 2017/7/16 15:41
 */
class SourceSetCreateTask extends DefaultTask {

    public static final String mainJava = "src/main/java"
    public static final String mainResource = "src/main/resources"
    public static final String testJava = "src/test/java"
    public static final String testResource = "src/test/resources"

    SourceSetCreateTask() {
        setGroup("scaffold")
        setDescription("Generate src dir and child dirs.")
    }

    @TaskAction
    void createDir() {
        getProject().mkdir(mainJava)
        println "create dir: $mainJava"

        getProject().mkdir(mainResource)
        println "create dir: $mainResource"

        getProject().mkdir(testJava)
        println "create dir: $testJava"

        getProject().mkdir(testResource)
        println "create dir: $testResource"
    }


}

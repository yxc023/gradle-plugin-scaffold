package com.yangxiaochen.gradle.plugin.scaffold.task

import org.gradle.api.DefaultTask
import org.gradle.api.internal.tasks.options.Option
import org.gradle.api.tasks.TaskAction

/**
 * @author yangxiaochen
 * @date 2017/7/16 15:51
 */
class ModuleCreateTask extends DefaultTask {

    public static final String mainJava = "src/main/java"
    public static final String mainResource = "src/main/resources"
    public static final String testJava = "src/test/java"
    public static final String testResource = "src/test/resources"

    String moduleName

    ModuleCreateTask() {
        setGroup("scaffold")
        setDescription("Generate module. usege: gradle createModule --name moduleName")
    }

    @Option(option = "name", description = "input a moduleName")
    void setModuleName(String moduleName) {
        this.moduleName = moduleName
    }

    @TaskAction
    void createModule() {
        if (moduleName == null) {
            throw new IllegalArgumentException("No module name, usege: gradle createModule --name moduleName")
        }

        project.mkdir moduleName
        project.file("settings.gradle").append("\ninclude '$moduleName'")
        println "create dir: $moduleName"


        project.mkdir "$moduleName/$mainJava"
        println "create dir: $moduleName/$mainJava"

        project.mkdir "$moduleName/$mainResource"
        println "create dir: $moduleName/$mainResource"

        project.mkdir "$moduleName/$testJava"
        println "create dir: $moduleName/$testJava"

        project.mkdir "$moduleName/$testResource"
        println "create dir: $moduleName/$testResource"


        project.file("$moduleName/build.gradle") << """
plugins {
    id 'java'
}

group '$project.group'
version '$project.version'

repositories {
    jcenter()
}

dependencies {
    testCompile 'junit:junit:4.12'
}
"""
        println "create file: $moduleName/build.gradle"

    }
}

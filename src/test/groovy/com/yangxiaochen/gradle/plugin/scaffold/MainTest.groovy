package com.yangxiaochen.gradle.plugin.scaffold

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

/**
 * @author yangxiaochen
 * @date 2017/7/16 14:38
 */
class MainTest {
    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder()
    private File buildFile
    List<File> pluginClasspath

    @Before
    void setup() throws IOException {
        buildFile = testProjectDir.newFile("build.gradle")
    }

    @Test
    void createTasks() {
        buildFile << this.class.getResourceAsStream("/build.gradle")
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("tasks")
                .withPluginClasspath()
                .build()

        println result.output
        assertTrue(result.getOutput().contains("Scaffold"))
        assertTrue(result.getOutput().contains("createSrc"))
        assertTrue(result.getOutput().contains("createModule"))
        assertEquals(result.task(":tasks").getOutcome(), TaskOutcome.SUCCESS)
    }

    @Test
    void createSrcTest() {

        buildFile << this.class.getResourceAsStream("/build.gradle")

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("createSrc")
                .withPluginClasspath()
                .build()

        println result.output
        assertTrue(new File(testProjectDir.getRoot(), 'src/main/java').exists())
        assertTrue(new File(testProjectDir.getRoot(), 'src/main/resources').exists())
        assertTrue(new File(testProjectDir.getRoot(), 'src/test/java').exists())
        assertTrue(new File(testProjectDir.getRoot(), 'src/test/resources').exists())
        assertTrue(result.getOutput().contains("create dir:"))
        assertEquals(result.task(":createSrc").getOutcome(), TaskOutcome.SUCCESS)
    }

    @Test(expected = Exception.class)
    void createModuleExceptionTest() {
        buildFile << this.class.getResourceAsStream("/build.gradle")

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("createModule")
                .withPluginClasspath()
                .build()

        println result.output

    }

    @Test
    void createModuleTest() {
        buildFile << this.class.getResourceAsStream("/build.gradle")

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("createModule", "--name", "foo")
                .withPluginClasspath()
                .build()

        println result.output

        assertTrue(new File(testProjectDir.getRoot(), 'foo/src/main/java').exists())
        assertTrue(new File(testProjectDir.getRoot(), 'foo/src/main/resources').exists())
        assertTrue(new File(testProjectDir.getRoot(), 'foo/src/test/java').exists())
        assertTrue(new File(testProjectDir.getRoot(), 'foo/src/test/resources').exists())

        def settingContent = new String(new File(testProjectDir.getRoot(), "settings.gradle").bytes)
        println settingContent
        assertTrue(settingContent.contains("include 'foo'"))

        def buildContent = new String(new File(testProjectDir.getRoot(), "foo/build.gradle").bytes)
        println buildContent
        assertTrue(buildContent.contains("group 'com.yangxiaochen.scaffold.test'"))
        assertTrue(buildContent.contains("version '1.2.3'"))

        assertTrue(result.getOutput().contains("create dir:"))
        assertEquals(result.task(":createModule").getOutcome(), TaskOutcome.SUCCESS)
    }

}

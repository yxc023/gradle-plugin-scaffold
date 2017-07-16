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

//        def pluginClasspathResource = getClass().classLoader.findResource("plugin-classpath.txt")
//        if (pluginClasspathResource == null) {
//            throw new IllegalStateException("Did not find plugin classpath resource, run `testClasses` build task.")
//        }
//
//        pluginClasspath = pluginClasspathResource.readLines().collect { new File(it) }
    }

    @Test
    void testHelloWorldTask() throws IOException {

        buildFile << this.class.getResourceAsStream("/build.gradle")

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("createSrc")
                .withPluginClasspath()
                .build()


        println testProjectDir.getRoot().getPath()
        assertTrue(new File(testProjectDir.getRoot(), 'src/main/java').exists())
        assertTrue(new File(testProjectDir.getRoot(), 'src/main/resources').exists())
        assertTrue(new File(testProjectDir.getRoot(), 'src/test/java').exists())
        assertTrue(new File(testProjectDir.getRoot(), 'src/test/resources').exists())
        assertEquals(result.task(":createSrc").getOutcome(), TaskOutcome.SUCCESS)
    }

}

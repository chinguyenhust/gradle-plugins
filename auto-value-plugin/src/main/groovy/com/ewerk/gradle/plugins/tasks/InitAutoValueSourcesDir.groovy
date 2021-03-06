package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.AutoValuePlugin
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * This task is responsible for removing and recreating the configured auto-value source roots.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class InitAutoValueSourcesDir extends DefaultTask {

  private static final Logger LOG = Logging.getLogger(InitAutoValueSourcesDir.class)

  static final String DESCRIPTION = "Creates the Auto-value sources dir."

  InitAutoValueSourcesDir() {
    this.group = AutoValuePlugin.TASK_GROUP
    this.description = DESCRIPTION
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  createSourceFolders() {

    def autoValueSourcesDir = project.file(project.autoValue.autoValueSourcesDir)

    LOG.info("Create source set ${autoValueSourcesDir}.")

    project.sourceSets.main.java.srcDirs.each { d ->
      if (d.absolutePath == autoValueSourcesDir.absolutePath) {
        throw new GradleException("The configured autoValueSourcesDir must specify a separate location to existing source code.")
      }
    }

    project.file(project.autoValue.autoValueSourcesDir).mkdirs()
  }
}
#!/usr/bin/env kotlin

@file:Repository("https://repo1.maven.org/maven2/")
@file:DependsOn("io.github.typesafegithub:github-workflows-kt:1.14.0")
@file:Repository("https://github-workflows-kt-bindings.colman.com.br/binding/")
@file:DependsOn("actions:setup-java:v3")
@file:DependsOn("actions:checkout:v3")
@file:DependsOn("gradle:actions__setup-gradle:v3")

import io.github.typesafegithub.workflows.actions.actions.Checkout
import io.github.typesafegithub.workflows.actions.actions.SetupJava
import io.github.typesafegithub.workflows.actions.gradle.ActionsSetupGradle
import io.github.typesafegithub.workflows.domain.RunnerType
import io.github.typesafegithub.workflows.domain.triggers.Push
import io.github.typesafegithub.workflows.dsl.expressions.expr
import io.github.typesafegithub.workflows.dsl.workflow
import io.github.typesafegithub.workflows.yaml.writeToFile

val workflow =
    workflow(
        name = "run reproducer script kt-67618",
        on = listOf(
            Push(branches = listOf("main")),
        ),
        sourceFile = __FILE__.toPath(),
    ) {
        val buildJob =
            job(
                id = "build",
                runsOn = RunnerType.UbuntuLatest,
                timeoutMinutes = 30,
            ) {
                uses(
                    name = "checkout",
                    action = Checkout(),
                )
                uses(
                    name = "Set up JDK",
                    action =
                    SetupJava(
                        javaVersion = "11",
                        distribution = SetupJava.Distribution.Zulu,
                    ),
                )
                uses(action = ActionsSetupGradle())
                run(
                    name = "Publish to mavenLocal",
                    command = "./gradlew publishToMavenLocal",
                )
                run(
                    name = "execute script",
                    command = """
                        './kt-67618.main.kts'
                    """.trimIndent(),
                )
            }
    }

workflow.writeToFile(addConsistencyCheck = true)

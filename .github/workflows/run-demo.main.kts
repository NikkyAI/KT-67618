#!/usr/bin/env kotlin

@file:Repository("https://repo1.maven.org/maven2/")
@file:DependsOn("io.github.typesafegithub:github-workflows-kt:1.14.0")
@file:Repository("https://github-workflows-kt-bindings.colman.com.br/binding/")
@file:DependsOn("actions:checkout:v4")

import io.github.typesafegithub.workflows.actions.actions.Checkout
import io.github.typesafegithub.workflows.domain.RunnerType
import io.github.typesafegithub.workflows.domain.triggers.Push
import io.github.typesafegithub.workflows.dsl.workflow
import io.github.typesafegithub.workflows.yaml.writeToFile

val workflow = workflow(
    name = "run reproducer script kt-67618",
    on = listOf(
        Push(branches = listOf("main")),
    ),
    sourceFile = __FILE__.toPath(),
) {
    val buildJob = job(
        id = "build",
        runsOn = RunnerType.UbuntuLatest,
        timeoutMinutes = 5,
    ) {
        uses(
            name = "checkout",
            action = Checkout(),
        )
        run(
            name = "Publish to mavenLocal",
            command = "./gradlew publishToMavenLocal",
        )
        run(
            name = "execute script",
            command = "./kt-67618.main.kts",
        )
    }
}

workflow.writeToFile(addConsistencyCheck = true)

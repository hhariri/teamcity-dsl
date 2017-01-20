package Wasabi.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.GradleBuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v10.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v10.triggers.VcsTrigger.*
import jetbrains.buildServer.configs.kotlin.v10.triggers.vcs


class Wasabi_Build(val dependentBuilds: List<BuildType>) : BuildType({
    uuid = "53f3d94a-20c6-43a2-b056-baa19e55fd40"
    extId = "Wasabi_Build"
    name = "Build"

    vcs {
        root(Wasabi.vcsRoots.Wasabi_HomeGitWasabiGitRefsHeadsMaster)

    }


    steps {

        gradle {
            tasks = "clean build"
            useGradleWrapper = true
            gradleWrapperPath = ""
        }
    }

    triggers {
        vcs {
        }
    }

    dependencies {
        dependentBuilds.forEach {
            dependency(it) {
                snapshot {
                    onDependencyFailure = FailureAction.FAIL_TO_START
                }
            }
        }
    }
})

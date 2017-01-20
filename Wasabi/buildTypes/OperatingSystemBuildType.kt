package Wasabi.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.BuildType
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v10.toExtId
import jetbrains.buildServer.configs.kotlin.v10.triggers.vcs


class OperatingSystemBuildType(buildParameters: BuildParameters) : BuildType() {
    init {
        val paramToId = buildParameters.name.toExtId()
        uuid = "53f3d94a-20c6-43a2-b056-baa19e55fd40-$paramToId"
        extId = "BuildType$paramToId"
        name = "Build for ${buildParameters.name}"

        vcs {
            root(Wasabi.vcsRoots.Wasabi_HomeGitWasabiGitRefsHeadsMaster)

        }
        steps {

            gradle {
                tasks = "clean build"
                useGradleWrapper = true
                gradleWrapperPath = ""
                gradleParams = "-D connections=${buildParameters.connections}"
            }
        }
        triggers {
            vcs {
            }
        }

        requirements {
            contains("teamcity.agent.jvm.os.name", buildParameters.operatingSystem)
        }
    }

}


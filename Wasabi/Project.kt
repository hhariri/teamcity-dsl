package Wasabi

import Wasabi.buildTypes.BuildParameters
import Wasabi.buildTypes.OperatingSystemBuildType
import Wasabi.buildTypes.Wasabi_Build
import Wasabi.vcsRoots.Wasabi_HomeGitWasabiGitRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v10.Project


object Project : Project({
    uuid = "9179636a-39a3-4c2c-aa7e-6e2ea7cfbc5b"
    extId = "Wasabi"
    parentId = "_Root"
    name = "Wasabi"

    vcsRoot(Wasabi_HomeGitWasabiGitRefsHeadsMaster)


    val operatingSystems = listOf(
            BuildParameters("Windows Build", "windows", 30),
            BuildParameters("MacOS Build", "osx", 20),
            BuildParameters("Linux Build", "ubuntu", 20)
    )

    val builds = operatingSystems.map(::OperatingSystemBuildType)

    builds.forEach {
        buildType(it)
    }

    buildType(Wasabi_Build(builds))


})


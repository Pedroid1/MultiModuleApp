pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ModularizationPluginSetup"
include(":app")
include(":core")
include(":core:common")
include(":core:designsystem")
include(":feature")
include(":feature:onboarding")
include(":core:data")
include(":feature:home")
include(":core:domain")
include(":core:testing")

pluginManagement {
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

rootProject.name = "MusicPlayerSample"
include(":app")
include(":core:ui")
include(":core:local")
include(":core:model")
include(":core:data")
include(":core:domain")
include(":feature:album_list")
include(":core:util")
include(":common:serialization")
include(":feature:album_detail")
include(":common:musicplayer")
include(":common:musicplayer-ui")

pluginManagement {
    repositories {
        // Resolve “id(…) version(…)” plugins here:
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    // Prevent modules from declaring their own repos
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "URL Shortener"
include(":app")
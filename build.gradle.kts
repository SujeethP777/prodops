// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugin.androidApp) version Plugin.Version.androidApp apply false
    id(Plugin.androidKotlin) version Plugin.Version.android apply false
    id(Plugin.hilt) version Plugin.Version.hilt apply false
    id(Plugin.androidLibrary) version Plugin.Version.androidLibrary apply false
    id(Plugin.ktlint) version Dependency.Version.ktlint apply false
    kotlin(Plugin.jsonSerialization) version Plugin.Version.jsonSerialization apply false
}

allprojects {
    apply(plugin = Plugin.ktlint)
}
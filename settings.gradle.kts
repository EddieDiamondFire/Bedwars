pluginManagement{
    val kotlinVersion: String by settings
    val shadowJarVersion: String by settings
    val minecrellVersion: String by settings

    repositories{
        mavenCentral()
        maven{
            url = uri("https://plugins.gradle.org/m2/")
        }
        gradlePluginPortal()
    }

    plugins{
        kotlin("jvm") version kotlinVersion
        id("com.github.johnrengelman.shadow") version shadowJarVersion
        id("net.minecrell.plugin-yml.bukkit") version minecrellVersion
    }
}

rootProject.name = "Bedwars"
include(":api", ":bukkit")


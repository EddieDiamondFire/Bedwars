import java.util.regex.Pattern.compile

plugins{
    kotlin("jvm")
    id("net.minecrell.plugin-yml.bukkit")
}

repositories{
    maven{
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven{
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
    maven{
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven("https://maven.enginehub.org/repo/")
}

dependencies{
    implementation(project(":api"))
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
    compile("io.github.eddiediamondfire:ComplexLibrary:dev-SNAPSHOT")
    implementation("org.slf4j:slf4j-api:${project.properties["slf4j"]}")
    implementation("org.slf4j:slf4j-log4j12:${project.properties["slf4j"]}")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.6-SNAPSHOT")
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.3.0-SNAPSHOT")
}

bukkit{
    main = "io.github.eddiediamondfire.bedwars.Bedwars"

    apiVersion = "1.17"
    name = rootProject.name
    version = rootProject.version.toString()
    authors = listOf("EddieDiamondFie (ScxLore1216)")
    depend = listOf("WorldGuard", "WorldEdit", "Vault")
    defaultPermission = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.Permission.Default.OP
    prefix = "Bedwars"
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.STARTUP

    commands{
        register("bedwars"){
            description = "The Main Command for Bedwars"
            aliases = listOf("bw", "BW")
        }
    }
}

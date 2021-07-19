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
    maven{
        url = uri("https://jitpack.io")
    }
    maven("https://repo.opencollab.dev/maven-snapshots/")
}

dependencies{
    implementation(project(":api"))
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
    implementation("org.json:json:20210307")
    compile("io.github.eddiediamondfire:ComplexLibrary:dev-SNAPSHOT")
    implementation("org.slf4j:slf4j-api:${project.properties["slf4j"]}")
    implementation("org.slf4j:slf4j-log4j12:${project.properties["slf4j"]}")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
}

bukkit{
    main = "io.github.eddiediamondfire.bedwars.Bedwars"

    apiVersion = "1.17"
    name = rootProject.name
    version = rootProject.version.toString()
    authors = listOf("EddieDiamondFie (ScxLore1216)")

    commands{
        register("bedwars"){
            description = "The Main Command for Bedwars"
            aliases = listOf("bw", "BW")
        }
    }
}
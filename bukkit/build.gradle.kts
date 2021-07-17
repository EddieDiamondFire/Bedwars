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
}

dependencies{
    implementation(project(":api"))
    implementation("com.google.code.gson:gson:2.8.7")
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
    compile("com.github.simplix-softworks:simplixstorage:3.2.3")
    implementation("com.google.code.gson:gson:2.8.7")
    implementation("org.json:json:20210307")
    compile("io.github.eddiediamondfire:ComplexLibrary:dev-SNAPSHOT")
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
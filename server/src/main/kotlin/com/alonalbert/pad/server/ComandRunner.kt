package com.alonalbert.pad.server

import com.alonalbert.pad.model.AutoDeleteResult
import com.alonalbert.pad.server.importconfig.ImportConfig
import com.alonalbert.pad.server.plex.PlexAutoDeleter
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EntityScan("com.alonalbert.pad.*")
@ComponentScan("com.alonalbert.pad.*")
class CommandRunner(
    private val plexAutoDeleterCommand: PlexAutoDeleter,
    private val importConfigCommand: ImportConfig,
) : CommandLineRunner {
    override fun run(vararg args: String) {
        runBlocking {
            when (args.firstOrNull()) {
                "import-config" -> importConfigCommand.import()
                "auto-watch" -> plexAutoDeleterCommand.runAutoWatcher()
                "auto-delete" -> runAutoDelete()
                else -> {}
            }
        }
    }

    private suspend fun runAutoDelete(): AutoDeleteResult {
        val result = plexAutoDeleterCommand.runAutoDeleter()
        println("============================================")
        println(result)
        return result
    }
}

fun main(args: Array<String>) {
    val application = SpringApplication(CommandRunner::class.java)
    application.setDefaultProperties(
        mapOf(
            "spring.main.web-application-type" to "NONE",
            "logging.level.com.alonalbert.pad.server." to "DEBUG",
        )
    )
    application.run(*args).close()
}
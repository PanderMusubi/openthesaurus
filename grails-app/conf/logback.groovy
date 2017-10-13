import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        //TODO merge next line with old patter "[%d{yyyy-MM-dd HH:mm:ss}] %-5p %c{1} - %m%n"
        pattern = "%level %logger - %msg%n"
    }
}

root(ERROR, ["STDOUT"])

logger("grails.app", INFO)

logger("org.codehaus.groovy.grails.web.servlet", ERROR) // controllers
logger("org.codehaus.groovy.grails.web.pages", ERROR) // GSP
logger("org.codehaus.groovy.grails.web.sitemesh", ERROR) // layouts
logger("org.codehaus.groovy.grails.web.mapping.filter", ERROR) // URL mapping
logger("org.codehaus.groovy.grails.web.mapping", ERROR) // URL mapping
logger("org.codehaus.groovy.grails.commons", ERROR) // core / classloading
logger("org.codehaus.groovy.grails.plugins", ERROR) // plugins
logger("org.codehaus.groovy.grails.orm.hibernate", ERROR) // hibernate integration
logger("org.springframework", ERROR)
logger("org.hibernate", ERROR)

logger("org.mortbay.log", WARN)

// Uncomment following line to see hibernate's sql code
//logger("org.hibernate", DEBUG)

if (Environment.current == Environment.DEVELOPMENT) {
    def targetDir = BuildSettings.TARGET_DIR
    if (targetDir) {

        appender("FULL_STACKTRACE", FileAppender) {

            file = "${targetDir}/stacktrace.log"
            append = true
            encoder(PatternLayoutEncoder) {
                pattern = "%level %logger - %msg%n"
            }
        }
        logger("StackTrace", ERROR, ["FULL_STACKTRACE"], false)
    }
}

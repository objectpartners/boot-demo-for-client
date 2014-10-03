package hello

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.orm.hibernate.cfg.HibernateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.hibernate.SessionFactory

import javax.annotation.PostConstruct

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = 'hello')
class Application extends SpringBootServletInitializer {

    private static applicationClass = Application

    static void main(String[] args) {
        SpringApplication.run Application, args
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.sources(applicationClass, BootStrap)
    }

    // this section is based upon this post
    //  https://github.com/spring-guides/gs-accessing-data-gorm/issues/5
    @Configuration
    static class BootStrap {

        @Autowired
        ApplicationContext applicationContext

        @Autowired
        SessionFactory sessionFactory

        @Autowired
        GrailsApplication grailsApplication

        @PostConstruct
        def bootstrap() {
            HibernateUtils.enhanceSessionFactory(sessionFactory, grailsApplication, applicationContext)
        }
    }
}

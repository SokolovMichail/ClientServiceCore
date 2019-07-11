package com.example.clientservicecore.systemtests
//
//import org.springframework.jdbc.datasource.DriverManagerDataSource
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.PropertySource
//import org.springframework.core.env.Environment
//import org.springframework.transaction.annotation.EnableTransactionManagement
//import javax.sql.DataSource
//
//
//@Configuration
//
//@EnableTransactionManagement
//class StudentJpaConfig {
//
//    @Autowired
//    private val env: Environment? = null
//
//    @Bean
//    fun dataSource(): DataSource {
//        val dataSource = DriverManagerDataSource()
//       // dataSource.setDriverClassName(env!!.getProperty("jdbc.driverClassName"))
//        dataSource.url = env!!.getProperty("jdbc.url")
//        dataSource.username = env!!.getProperty("jdbc.user")
//        dataSource.password = env!!.getProperty("jdbc.pass")
//
//        return dataSource
//    }
//
//    // configure entityManagerFactory
//
//    // configure transactionManager
//
//    // configure additional Hibernate Properties
//}
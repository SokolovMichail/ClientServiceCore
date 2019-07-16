package com.example.clientservicecore.clientprocessors

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.FIELD, AnnotationTarget.CLASS)
annotation class MyLoggableAnnotation()

@Aspect
@Component
class LoggingAspect
{
    private val logger = KotlinLogging.logger {}

    @Before("execution(* com.example.clientservicecore.clientprocessors.AbstractProcessor.*(..))")
    fun logMethodAbstractProcessor(jp:JoinPoint) {
        logger.info("This catches anything happening in AbstractProcessor, for example ${jp.signature}")
    }


    @Before("execution(* com.example.clientservicecore.clientprocessors.ProcessorExecutionBasedLogging.*(..))")
    fun logMethodProcessorExecutionBasedLogging(jp:JoinPoint) {

        logger.info("This catches anything happening in ProcessorExecutionBasedLogging, for example ${jp.signature}")
    }

    @Before("within(com.example.clientservicecore.clientprocessors.ProcessorWithinBasedLogging)")
    fun logMethodProcessorWithinBasedLogging(jp:JoinPoint) {

        logger.info("This catches anything happening in ProcessorWithinBasedLogging, for example ${jp.signature}")
    }

//    @Before("execution(* *..process*(com.example.clientservicecore.сlientmodel.ClientDTO,String))")
//    fun logMethodProcessorArgBasedLogging(jp:JoinPoint) {
//
//        logger.info("This catches anything happening in ProcessorArgBasedLogging, for example ${jp.signature}")
//    }

    @Before("@annotation(MyLoggableAnnotation)")
    fun logMethodAnnotationBased(jp:JoinPoint) {

        logger.info("This catches anything happening in ProcessorAnnotationBasedLogging, for example ${jp.signature}")
    }
}
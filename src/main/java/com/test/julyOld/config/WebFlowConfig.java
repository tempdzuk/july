package com.test.julyOld.config;

import com.recruiting.converter.StringToDateConverter;
import com.recruiting.converter.StringToWorkingHoursSchemeConverter;
import com.recruiting.repository.WorkingHoursSchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.ConversionService;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.security.SecurityFlowExecutionListener;

import java.util.List;

/**
 * @author Marta Ginosyan
 */

@Configuration
@EnableWebMvc
@EnableAutoConfiguration
public class WebFlowConfig extends AbstractFlowConfiguration {

    @Autowired
    private WebMVCConfig webMvcConfig;

    @Autowired
    private List<ViewResolver> viewResolvers;

    @Autowired
    private WorkingHoursSchemeRepository workingHoursSchemeRepository;

    @Bean
    public FlowExecutor flowExecutor() {
        return getFlowExecutorBuilder(flowRegistry()).addFlowExecutionListener(new SecurityFlowExecutionListener(), "*")
                .build();
    }

    @Bean
    public FlowDefinitionRegistry flowRegistry() {
        return getFlowDefinitionRegistryBuilder(flowBuilderServices()).setBasePath("classpath*:/templates")
                .addFlowLocationPattern("/**/*-flow.xml")
                .build();
    }

    @Bean
    public FlowBuilderServices flowBuilderServices() {
        FlowBuilderServices flowBuilderServices = getFlowBuilderServicesBuilder().setViewFactoryCreator(mvcViewFactoryCreator())
                .setDevelopmentMode(true)
                .build();
        flowBuilderServices.setConversionService(getConversionService());
        return flowBuilderServices;
    }

    @Bean
    public MvcViewFactoryCreator mvcViewFactoryCreator() {
        viewResolvers.add(this.webMvcConfig.ajaxThymeleafViewResolver());

        MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
        factoryCreator.setViewResolvers(viewResolvers);
        factoryCreator.setUseSpringBeanBinding(true);
        return factoryCreator;
    }

    @Bean
    public ConversionService getConversionService() {
        DefaultConversionService extendedConverterService = new DefaultConversionService();
        extendedConverterService.addConverter(new StringToDateConverter());
        extendedConverterService.addConverter(new StringToWorkingHoursSchemeConverter(workingHoursSchemeRepository));
        return extendedConverterService;
    }

}

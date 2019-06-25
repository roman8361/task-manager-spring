package ru.kravchenko.spring.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Roman Kravchenko
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{};
    }

    protected Class<?>[] getServletConfigClasses() { return new Class[]{WebMvcConfig.class}; }

    protected String[] getServletMappings() { return new String[]{"/"}; }

}


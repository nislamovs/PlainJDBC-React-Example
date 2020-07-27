package com.example.jdbcexample.configuration;

import org.jsondoc.core.annotation.flow.ApiFlow;
import org.jsondoc.core.annotation.global.ApiChangelog;
import org.jsondoc.core.annotation.global.ApiChangelogSet;
import org.jsondoc.core.annotation.global.ApiGlobal;
import org.jsondoc.core.annotation.global.ApiGlobalSection;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ApiGlobal(
      sections = {
              @ApiGlobalSection(title = "Stack", paragraphs = {"Java8", "Spring", "Plain JDBC", "Triggers, storedProcedures, functions, cronJobs", "React", "Spock"}),
              @ApiGlobalSection(title = "About", paragraphs = {"Author : Nizami Islamovs", "Email : nizami.islamovs@gmail.com"})
      }
)
@ApiChangelogSet(
        changlogs = {
                @ApiChangelog(changes = "Functionality created", version = "1.0.0"),
                @ApiChangelog(changes = "Functionality not created yet. And probably will not be created. Because JsonDon is such a crap...", version = "1.0.1")
        }
)
public class JsonDocConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/jsondoc-ui.html?url=http://localhost:8080/jsondoc");
    }
}

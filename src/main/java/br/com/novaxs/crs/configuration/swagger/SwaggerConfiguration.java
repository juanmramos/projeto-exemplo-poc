package br.com.novaxs.crs.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

/**
 * Swagger Configure Class.
 *
 * @author Juan Ramos
 * @see <a href="https://swagger.io/">https://swagger.io/</a>
 *
 */
@Configuration
public class SwaggerConfiguration {
    /**
     * Returns a {@link Docket}.
     *
     * @return {@link Docket}
     */
    @Bean
    public Docket swaggerSpringfoxDocket() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.novaxs.crs"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalOperationParameters(Arrays.asList(
                        new ParameterBuilder()
                                .name("Authorization")
                                .description("Header para Token JWT")
                                .modelRef(new ModelRef("String"))
                                .parameterType("header")
                                .required(false)
                                .build()
                ));

        return docket;
    }

    private ApiInfo apiInfo() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("NOVAXS - CRS")
                .description("API - NOVAXS - CRS")
                .version("1.0.0")
                .termsOfServiceUrl("API")
                .build();

        return apiInfo;
    }
}

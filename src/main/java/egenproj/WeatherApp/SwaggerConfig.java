package egenproj.WeatherApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import io.swagger.models.Contact;
//import io.swagger.annotations.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				
					.useDefaultResponseMessages(false)
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
					.build()
					.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("Pradeep", "https://pp.io", "talkwithpradeep@gmail.com");
		ApiInfo info = new ApiInfo("Weather app", "A simple REST API", "1.0.0", "TnC", contact, "MIT",
				"https://opensource.org/licenses/MIT");
		return info;
	}

}

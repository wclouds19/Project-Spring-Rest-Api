package bootcamp.rest;

import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import bootcamp.rest.helpers.AuditorAwareHelpers;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class NewsRestApiApplication {

	public static void main(String[] args) {
		
		//SpringApplication.run(NewsRestApiApplication.class, args);		
		SpringApplication application = new SpringApplication(NewsRestApiApplication.class); 
        application.setBannerMode(Mode.OFF); //Customizing Banner
		application.run(args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditorAwareHelpers();
	}
}
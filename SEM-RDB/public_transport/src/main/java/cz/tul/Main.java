package cz.tul;

import cz.tul.data.Brand;
import cz.tul.service.BrandService;
import cz.tul.service.CountryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("cz.tul.repositories")
@EntityScan("cz.tul.data")
public class Main {

  @Bean
  public BrandService brandService() {
    return new BrandService();
  }

  public static void main(String[] args) {

    SpringApplication app = new SpringApplication(Main.class);
    ApplicationContext context = app.run(args);

    BrandService brandService = context.getBean(BrandService.class);
    List<Brand> brands = brandService.getAllBrands();
    System.out.println(brands);

  }
}

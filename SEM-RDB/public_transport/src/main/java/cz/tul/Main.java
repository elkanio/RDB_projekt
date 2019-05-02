package cz.tul;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static javafx.application.Application.launch;

@SpringBootApplication
@EnableJpaRepositories("cz.tul.repositories")
public class Main extends Application {

  private ConfigurableApplicationContext springContext;
  private Parent root;

  @Override
  public void init() throws Exception {
    springContext = SpringApplication.run(Main.class);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
    fxmlLoader.setControllerFactory(springContext::getBean);
    root = fxmlLoader.load();
  }

  public void start(Stage stage) throws Exception {

    Scene scene = new Scene(root);
    scene.getStylesheets().add("/styles/Styles.css");

    stage.setTitle("Public Transportation Database");
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  public static void main(String[] args) {


    SpringApplication.run(Main.class,args);


    launch(args);

  }
}

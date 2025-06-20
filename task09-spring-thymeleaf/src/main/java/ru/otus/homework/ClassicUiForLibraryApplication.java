package ru.otus.homework;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock
@SpringBootApplication
public class ClassicUiForLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassicUiForLibraryApplication.class, args);
		System.out.println("http://localhost:8080/authors");
	}

}

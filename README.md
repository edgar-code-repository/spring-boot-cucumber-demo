CUCUMBER DEMO
-----------------------------------------------------------------

Behaviour driven development with Spring Boot and Cucumber.

A few Cucumber examples are created to test Rest endpoints.

-----------------------------------------------------------------

**Dependencies added to pom.xml file**

```

    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>${cucumber.version}</version>
        <scope>test</scope>
    </dependency>
    
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>${cucumber.version}</version>
        <scope>test</scope>
    </dependency>
    
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-spring</artifactId>
        <version>${cucumber.version}</version>
        <scope>test</scope>
    </dependency>

```
-----------------------------------------------------------------

**Cucumber configuration**

In this configuration class we can tell where to find the Cucumber features 
files and where the glue classes are located (steps):

```

    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = {"src/test/resources/features"},
            plugin = {"pretty"},
            glue = {"com.example.demo.cucumber.base", "com.example.demo.cucumber.steps"})
    public class CucumberRunner {
    
    }

```

-----------------------------------------------------------------

**Create book endpoint (book with id 101):**

![SaveBook1](screenshots/save-book-1.png)

-----------------------------------------------------------------

**Create book endpoint (book with id 102):**

![SaveBook2](screenshots/save-book-2.png)

-----------------------------------------------------------------

**Retrieve all books endpoint:**

![GetBooks](screenshots/retrieve-all-books.png)

-----------------------------------------------------------------

**Update book endpoint (book with id 101):**

![UpdateBook1](screenshots/update-book-1.png)

-----------------------------------------------------------------

**Delete book endpoint (book with id 102):**

![DeleteBook2](screenshots/delete-book-2.png)

-----------------------------------------------------------------

**Retrieve all books again:**

![GetBooksAgain](screenshots/retrieve-books-again.png)

-----------------------------------------------------------------
package com.example.gamezone.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import java.util.stream.Collectors;
@Service
public class AIQuizService {

    public String generateQuiz(String topic) {

        topic = topic.toLowerCase().trim();

        List<String> questions = new ArrayList<>();

        if (topic.contains("java")) {

            questions.add(
                "1. Which keyword is used to inherit a class in Java?\n"
                + "A) implement\n"
                + "B) extends\n"
                + "C) inherits\n"
                + "D) super\n"
                + "Correct Answer: B) extends"
            );

            questions.add(
                "2. Which method is the entry point of a Java application?\n"
                + "A) start()\n"
                + "B) run()\n"
                + "C) main()\n"
                + "D) execute()\n"
                + "Correct Answer: C) main()"
            );

            questions.add(
                "3. Which keyword is used to create an object in Java?\n"
                + "A) create\n"
                + "B) object\n"
                + "C) new\n"
                + "D) class\n"
                + "Correct Answer: C) new"
            );

            questions.add(
                "4. Which collection does not allow duplicate elements?\n"
                + "A) List\n"
                + "B) Set\n"
                + "C) ArrayList\n"
                + "D) Vector\n"
                + "Correct Answer: B) Set"
            );

            questions.add(
                "5. Which keyword is used to handle exceptions?\n"
                + "A) error\n"
                + "B) exception\n"
                + "C) try\n"
                + "D) handle\n"
                + "Correct Answer: C) try"
            );

        } else if (topic.contains("python")) {

            questions.add(
                "1. Which symbol is used for comments in Python?\n"
                + "A) //\n"
                + "B) #\n"
                + "C) /* */\n"
                + "D) <!-- -->\n"
                + "Correct Answer: B) #"
            );

            questions.add(
                "2. Which keyword is used to define a function in Python?\n"
                + "A) function\n"
                + "B) define\n"
                + "C) def\n"
                + "D) fun\n"
                + "Correct Answer: C) def"
            );

            questions.add(
                "3. Which data type stores key-value pairs in Python?\n"
                + "A) List\n"
                + "B) Tuple\n"
                + "C) Set\n"
                + "D) Dictionary\n"
                + "Correct Answer: D) Dictionary"
            );

            questions.add(
                "4. Which function is used to display output in Python?\n"
                + "A) display()\n"
                + "B) print()\n"
                + "C) output()\n"
                + "D) show()\n"
                + "Correct Answer: B) print()"
            );

            questions.add(
                "5. Which symbol is used for exponentiation in Python?\n"
                + "A) ^\n"
                + "B) **\n"
                + "C) //\n"
                + "D) %%\n"
                + "Correct Answer: B) **"
            );

        } else if (topic.contains("dbms") || topic.contains("database")) {

            questions.add(
                "1. What does DBMS stand for?\n"
                + "A) Database Management System\n"
                + "B) Data Backup Management System\n"
                + "C) Database Monitoring Service\n"
                + "D) Data Management Software\n"
                + "Correct Answer: A) Database Management System"
            );

            questions.add(
                "2. Which language is commonly used to query relational databases?\n"
                + "A) HTML\n"
                + "B) CSS\n"
                + "C) SQL\n"
                + "D) XML\n"
                + "Correct Answer: C) SQL"
            );

            questions.add(
                "3. Which SQL command is used to retrieve data?\n"
                + "A) GET\n"
                + "B) SELECT\n"
                + "C) FETCHDATA\n"
                + "D) READ\n"
                + "Correct Answer: B) SELECT"
            );

            questions.add(
                "4. Which key uniquely identifies a record?\n"
                + "A) Foreign Key\n"
                + "B) Candidate Key\n"
                + "C) Primary Key\n"
                + "D) Alternate Key\n"
                + "Correct Answer: C) Primary Key"
            );

            questions.add(
                "5. Which SQL command is used to remove a table?\n"
                + "A) DELETE\n"
                + "B) REMOVE\n"
                + "C) DROP\n"
                + "D) CLEAR\n"
                + "Correct Answer: C) DROP"
            );

        } else {

            questions.add(
                "1. What is the main purpose of a computer program?\n"
                + "A) To process instructions\n"
                + "B) To increase screen size\n"
                + "C) To change hardware color\n"
                + "D) To create electricity\n"
                + "Correct Answer: A) To process instructions"
            );

            questions.add(
                "2. Which component performs calculations in a computer?\n"
                + "A) Monitor\n"
                + "B) CPU\n"
                + "C) Keyboard\n"
                + "D) Printer\n"
                + "Correct Answer: B) CPU"
            );

            questions.add(
                "3. Which device is mainly used for permanent data storage?\n"
                + "A) RAM\n"
                + "B) Cache\n"
                + "C) SSD\n"
                + "D) Register\n"
                + "Correct Answer: C) SSD"
            );

            questions.add(
                "4. What does URL stand for?\n"
                + "A) Uniform Resource Locator\n"
                + "B) Universal Record Link\n"
                + "C) User Resource Language\n"
                + "D) Uniform Routing Link\n"
                + "Correct Answer: A) Uniform Resource Locator"
            );

            questions.add(
                "5. Which technology is used to style HTML pages?\n"
                + "A) Java\n"
                + "B) SQL\n"
                + "C) CSS\n"
                + "D) MySQL\n"
                + "Correct Answer: C) CSS"
            );
        }

        Collections.shuffle(questions);

        StringBuilder result = new StringBuilder();

        result.append("🤖 AI Quiz Generated for: ")
              .append(topic)
              .append("\n\n");

        for (String question : questions) {
            result.append(question)
                  .append("\n\n");
        }

        return result.toString();
    }
}
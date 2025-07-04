# SpringBoot-SendingSimpleEmail

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0%2B-brightgreen) ![Java](https://img.shields.io/badge/Java-17%2B-blue) 

A **Spring Boot application to send simple plain-text emails using Gmail SMTP**.

---

## ✨ Features

* Send plain-text emails with subject and body.
* Uses Gmail SMTP with TLS.
* Executes email sending automatically using `CommandLineRunner`.
* Ready for integration into larger systems for notification services.

---

## 🛠️ Tech Stack

* Java 17+
* Spring Boot 3.x
* Spring Boot Starter Mail
* Gmail SMTP

---

## 📁 Project Structure

```
src/main/java
 └── com.sharad
      ├── service
      │     ├── EmailService.java
      │     └── EmailServiceImpl.java
      └── runner
            └── EmailRunner.java

src/main/resources
 └── application.properties
```

---

## ⚙️ Configuration

Add the following to `src/main/resources/application.properties`:

```properties
spring.application.name=SpringBoot-SendingSimpleEmail

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_gmail@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

**Replace:**

* `your_gmail@gmail.com` with your Gmail address.
* `your_app_password` with your **Google App Password** (not your regular password).

---

## 🚀 How It Works

* On startup, `EmailRunner` automatically triggers.
* Calls `EmailService.sendSimpleEmail(...)` to send the email.
* The email will be sent to the configured recipient.
* You will see `Mail Sent Successfully...` in the console on success.

---

## 🩺 Testing Locally

1️⃣ Clone the repository:

```bash
git clone <repo-url>
cd SpringBoot-SendingSimpleEmail
```

2️⃣ Update `application.properties` with your Gmail credentials and recipient email inside `EmailRunner`.

3️⃣ Run the application:

```bash
./mvnw spring-boot:run
```

Or:

```bash
mvn clean install
java -jar target/SpringBoot-SendingSimpleEmail-0.0.1-SNAPSHOT.jar
```

✅ Check your recipient inbox for the test email.

---

# Spring Boot Email Sending with MimeMessage and Purchased Items Project Example

## 1️⃣ What is MimeMessage?

* Used to send rich content emails (HTML, attachments, inline images).
* Provided by JavaMail API and used via `JavaMailSender` in Spring Boot.
* Allows setting sender, recipients, subject, body, and attachments.

## 2️⃣ Dependencies Needed

Add in `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

## 3️⃣ Property Configuration

`application.properties`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## 4️⃣ Steps to Send Mail Using MimeMessage

1. Autowire `JavaMailSender`.
2. Create `MimeMessage` using `createMimeMessage()`.
3. Use `MimeMessageHelper` to set from, to, subject, body.
4. Attach files using `addAttachment()`.
5. Send using `javaMailSender.send(message)`.

## 5️⃣ Project Example

### Service Interface

```java
public interface PurchasedService {
    String purchasedItem(String[] items, double[] prices, String[] emails) throws Exception;
}
```

### Service Implementation

```java
@Service
public class PurchasedServiceImpl implements PurchasedService {
    @Autowired
    private JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public String purchasedItem(String[] items, double[] prices, String[] emails) throws Exception {
        double billAmount = Arrays.stream(prices).sum();
        String message = Arrays.toString(items) + " - " + Arrays.toString(prices) + " purchased. Total: " + billAmount;
        String status = sendMessage(message, emails);
        return message + " --- " + status;
    }

    public String sendMessage(String message, String[] toEmails) throws Exception {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(fromEmail);
        helper.setTo(toEmails);
        helper.setSubject("Purchased Items");
        helper.setText(message);
        helper.addAttachment("items.png", new ClassPathResource("Nat.png"));
        sender.send(mimeMessage);
        return "Mail sent successfully to " + Arrays.toString(toEmails);
    }
}
```

### Runner Class

```java
@Component
public class EmailRunner implements CommandLineRunner {
    @Autowired
    private PurchasedService purchasedService;

    @Override
    public void run(String... args) throws Exception {
        String[] items = {"item1", "item2", "item3"};
        double[] prices = {100.0, 200.0, 300.0};
        String[] emails = {"email1@gmail.com", "email2@gmail.com"};
        purchasedService.purchasedItem(items, prices, emails);
    }
}
```

## 6️⃣ Folder Structure

```
src/main/java/com/sharad
    ├── runner/EmailRunner.java
    ├── service/PurchasedService.java
    ├── service/PurchasedServiceImpl.java
src/main/resources
    ├── application.properties
    ├── Nat.png
```

## 7️⃣ How to Run in Eclipse

* Import project as **Maven Project**.
* Configure **JDK 17+**.
* Add Gmail credentials and file in `resources`.
* Run `EmailRunner`.
* Check console and verify email.

## ✅ You Have Learned

* Sending rich content emails with attachments using `MimeMessage`.
* Handling attachments and dynamic mail body with Spring Boot.
* Building clean service structure for reusable mail utilities.



## 🚨 Troubleshooting

* **Authentication Failed:** Use a Google App Password with 2-Step Verification enabled.
* **Port Issues:** Ensure port `587` is open for outbound traffic.
* **No Email Received:** Check your spam folder.
* **Gmail Security Restrictions:** App Passwords bypass less secure app restrictions.

---

**Happy Coding! 🚀**

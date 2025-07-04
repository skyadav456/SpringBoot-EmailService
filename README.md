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

## 🚨 Troubleshooting

* **Authentication Failed:** Use a Google App Password with 2-Step Verification enabled.
* **Port Issues:** Ensure port `587` is open for outbound traffic.
* **No Email Received:** Check your spam folder.
* **Gmail Security Restrictions:** App Passwords bypass less secure app restrictions.

---



## ✉️ Contact

For learning Spring Boot email integration with REST, Spring Security, or advanced mail handling, feel free to reach out anytime.

---

**Happy Coding! 🚀**

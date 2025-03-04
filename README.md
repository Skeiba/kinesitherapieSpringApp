﻿# Mini-Project: Management of a Physiotherapy Center

## Authors
- **Salaheddine Bahlaouane**
- **Yassin Zaher**
- **Hamza Moustansir**
- **Yassine Chbani**

## Project Description

## Key Features

## Technologies Used
- **Programming Language**: Java Spring
- **Database**: MySQL
- **Graphical Interface**: React JS

---

### 🔧 Configuration  

This project requires a custom configuration file for sensitive credentials.  

#### 1️⃣ Create `application-secret.properties`  

After cloning the repository, create a file at:  
```
src/main/resources/application-secret.properties
```
This file is ignored by Git and should contain:  

```properties
# Gmail SMTP Configuration
spring.mail.username=your-email@gmail.com
spring.mail.password=your-email-password

# MySQL Database Credentials
spring.datasource.username=your-db-username
spring.datasource.password=your-db-password
```

#### 2️⃣ Alternative: Ignore the file  
Or just add the properties in `application.properties` directly if the project will run locally.  

---

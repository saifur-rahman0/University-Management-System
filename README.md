# 🎓 University Management System (UMS)

A desktop-based enterprise **University & College Management System (CMS/UMS)** built in **Java (Swing/AWT)** with a **MySQL** database backend. The system streamlines academic administration through dedicated portals for **Administrators**, **Teachers/Faculty**, and **Students**, featuring attendance tracking, examination grading, PDF marksheet generation, roll number allocation, and audit logging.

---

## 📑 Table of Contents

- [Key Features](#-key-features)
  - [👑 Administrator Portal](#-administrator-portal)
  - [👨‍🏫 Teacher / Faculty Portal](#-teacher--faculty-portal)
  - [🎓 Student Portal](#-student-portal)
  - [🔐 Authentication & Security](#-authentication--security)
- [System Architecture](#-system-architecture)
- [Database Design & Schema](#-database-design--schema)
- [Project Directory Structure](#-project-directory-structure)
- [Prerequisites](#-prerequisites)
- [Installation & Setup Guide](#-installation--setup-guide)
  - [1. Clone / Open the Repository](#1-clone--open-the-repository)
  - [2. Database Setup](#2-database-setup)
  - [3. Configure Database Connection](#3-configure-database-connection)
  - [4. Add JAR Dependencies to Classpath](#4-add-jar-dependencies-to-classpath)
  - [5. Run the Application](#5-run-the-application)
- [Default Login Credentials](#-default-login-credentials)
- [User ID & Password Conventions](#-user-id--password-conventions)
- [Technologies & Libraries](#-technologies--libraries)
- [License & Contributions](#-license--contributions)

---

## ✨ Key Features

### 👑 Administrator Portal
- **Dashboard Overview**: Quick statistics, active notifications, real-time clock, and navigation shortcuts.
- **Institute Profile & Branding**: Customize college name, address, contact numbers, official website, social links (Facebook, Instagram, Twitter, LinkedIn), and university logo.
- **Department & Course Management**:
  - Add, edit, and organize academic departments.
  - Define courses, semesters/years, and total semester duration.
- **Subject Management**:
  - Register subject codes, names, types (Core/Elective), and allocate maximum theory and practical marks.
- **Faculty Management**:
  - Register teachers with personal bio, photo, qualification, and experience.
  - Assign teachers to specific courses, semesters, and subjects.
  - Activate or deactivate faculty accounts.
- **Student Admissions & Roll Generator**:
  - Student admission form with photo upload, parental details, and contact info.
  - Automated roll number and User ID generator (`dept-sem-roll`).
  - Optional/elective subject assignments.
- **Attendance Management**:
  - Mark daily student attendance per subject and date.
  - Generate comprehensive attendance reports with percentage calculations.
- **Examination & Marksheet Engine**:
  - Input, update, and manage theory and practical marks.
  - Result declaration toggle per semester.
  - Generate official marksheets and export/print to PDF via `printMarksheetPDF`.
- **Global Search System**: Search students, faculty members, and courses by name, ID, or department.
- **User Activity Audit**: Monitor user logins, active sessions, and timestamps.

### 👨‍🏫 Teacher / Faculty Portal
- **Teacher Dashboard & Profile**: View personal credentials, contact info, and assigned courses.
- **Assigned Classes & Subjects**: Quick view of assigned academic syllabus and enrolled student lists.
- **Daily Attendance Marking**: Checkbox-based daily attendance registry with instant database persistence.
- **Attendance Analytics**: View and generate subject-wise student attendance reports.
- **Marks Evaluation**: Enter and edit theory and practical examination scores.
- **Student Performance Reports**: Generate and review student marksheets and pass/fail statuses.

### 🎓 Student Portal
- **Student Profile**: View personal academic records, admission details, and contact information.
- **Enrolled Course & Subject Directory**: Overview of enrolled subjects, syllabi, and credit breakdown.
- **Faculty Directory**: Access details and contacts of assigned subject professors.
- **Marksheet & Result Viewer**:
  - View published examination scores (Theory + Practical + Total).
  - Calculate percentage, pass status, and grade.
  - Export and print printable PDF marksheets.
- **Attendance Monitor**: Live attendance percentage tracker with subject-wise breakdown.
- **Institute Contact Information**: Direct access to administration contact details and social handles.

### 🔐 Authentication & Security
- Role-based access control with 3 distinct user tabs: **Admin**, **Teacher**, and **Student**.
- Animated panel transitions with smooth UI effects.
- Dynamic background slideshow.
- Interactive Show/Hide password toggle.
- Database connection health checker upon application launch.

---

## 🏗 System Architecture

The project follows a modular, package-driven MVC-like architecture for Java Swing desktop applications:

```
[UI Layer (Swing / AWT)]
       │
       ├── LoginPageFrame / LoginPanel (Authentication)
       ├── AdminMain (Admin Panels & Dialogs)
       ├── TeacherMain (Teacher Panels & Dialogs)
       └── StudentMain (Student Panels & Dialogs)
       │
[Business Logic & Data Access Layer]
       │
       ├── AdminData / UserData
       ├── TeacherData
       ├── StudentData
       ├── CourseData / DepartmentData / RollNumberData
       └── Common Utilities (TimeUtil, ImageUtil, printMarksheetPDF)
       │
[Database Layer (JDBC)]
       │
       └── DataBaseConnection (MySQL Connection Pool / Driver)
              └── MySQL Database (university_mng_sys)
```

---

## 🗄 Database Design & Schema

The application connects to a MySQL database named `university_mng_sys`. The relational schema comprises the following core tables:

| Table Name | Description |
|---|---|
| `admin` | Stores college profile, contact info, admin password, social media handles, and logo (BLOB). |
| `faculties` | Faculty profile, qualifications, experience, photo, assigned course/sem/subject, and login credentials. |
| `students` | Student admission records, roll number, parental info, address, photo, and login credentials. |
| `cources` | Academic courses, semester/year duration, and course codes. |
| `subject` | Subject catalog with course code, semester, subject type, theory marks, and practical marks. |
| `attandance` | Daily student attendance records (present/absent) linked by subject code, date, and roll number. |
| `marks` | Theory and practical exam marks per student, course, semester, and subject. |
| `result` | Result declaration flags (`isdeclared`) per course and semester. |
| `rollgenerator` | Roll number tracking and sequence generation per course and semester. |
| `users` | User login session history, login timestamps, and user role profile. |
| `chat` | Internal messaging and communication log. |
| `notification`| Broadcast announcements and targeted notifications. |

> 💡 Two SQL dump files are provided in the root directory:
> - `collegedata.sql`: Clean database schema and table structures with admin bootstrap data.
> - `collegedata with info.sql`: Full database dump pre-populated with sample departments, courses, teachers, students, attendance, and exam marks.

---

## 📂 Project Directory Structure

```plaintext
UMS (Group)/
├── assets/                       # UI assets, icons, background images, and button graphics
│   ├── buttons/
│   ├── default/
│   ├── home/
│   ├── icons/
│   └── login_Panal/
├── jar files/                    # External Java library dependencies
│   ├── mysql-connector-java-5.1.38.jar  # MySQL JDBC Driver
│   └── rs2xml.jar                       # ResultSet to TableModel converter
├── src/                          # Java source code
│   ├── META-INF/
│   └── application/
│       ├── admin/                # Admin portal UI, dialogs, and data handlers
│       │   ├── Admin.java
│       │   ├── AdminData.java
│       │   ├── AdminMain.java
│       │   ├── AdminProfilePanel.java
│       │   ├── EditAdminDetailsDialog.java
│       │   └── EditAdminLinksDialog.java
│       ├── common/               # Shared utilities, DB connector, base models, PDF export
│       │   ├── ChangePasswordDialog.java
│       │   ├── DataBaseConnection.java
│       │   ├── HintPasswordField.java
│       │   ├── HintTextField.java
│       │   ├── HomePanel.java
│       │   ├── ImageUtil.java
│       │   ├── Person.java
│       │   ├── PhotoViewPanel.java
│       │   ├── PrintMarksheetDialog.java
│       │   ├── ScrollPaneUtil.java
│       │   ├── SearchPanel.java
│       │   ├── TimeUtil.java
│       │   ├── User.java
│       │   ├── UserData.java
│       │   ├── UsersPanel.java
│       │   └── printMarksheetPDF.java
│       ├── course/               # Course catalog, subject configuration, and course assignments
│       │   ├── AddCourseDialog.java
│       │   ├── AssignCourseDialog.java
│       │   ├── AssignCoursePanel.java
│       │   ├── Course.java
│       │   ├── CourseData.java
│       │   └── CoursePanel.java
│       ├── department/           # Department management and roll number generator
│       │   ├── AddDepartmentDialog.java
│       │   ├── Department.java
│       │   ├── DepartmentData.java
│       │   ├── DepartmentPanel.java
│       │   ├── RollGeneratorDialog.java
│       │   ├── RollGeneratorPanel.java
│       │   └── RollNumberData.java
│       ├── login/                # Authentication frames, login panels, and role switchers
│       │   ├── LoginPageFrame.java
│       │   └── LoginPanel.java
│       ├── student/              # Student portal, admission dialogs, attendance, and marksheet UI
│       │   ├── AddStudentDialog.java
│       │   ├── Attandance.java
│       │   ├── AttandanceReportPanel.java
│       │   ├── EnterMarksPanel.java
│       │   ├── HeaderRendererForCheckBox.java
│       │   ├── MarkAttandancePanel.java
│       │   ├── MarkSheetPanel.java
│       │   ├── MarkSheetReportPanel.java
│       │   ├── Marks.java
│       │   ├── Student.java
│       │   ├── StudentData.java
│       │   ├── StudentMain.java
│       │   ├── StudentPanel.java
│       │   ├── ViewStudentDialog.java
│       │   └── ViewStudentPanel.java
│       └── teacher/              # Teacher portal, faculty management, and view panels
│           ├── AddTeacherDialog.java
│           ├── Teacher.java
│           ├── TeacherData.java
│           ├── TeacherMain.java
│           ├── TeacherPanel.java
│           └── ViewTeacherPanel.java
├── Students Profile pic/         # Student photo storage
├── Teachers Profile pic/         # Faculty photo storage
├── collegedata.sql               # Base database schema
├── collegedata with info.sql     # Full database schema + sample dataset
├── build.xml                     # Apache Ant build script
├── CMS.iml                       # IntelliJ IDEA module configuration
├── manifest.mf                   # JAR Manifest configuration
└── info.txt                      # Quick identifier & convention notes
```

---

## ⚙️ Prerequisites

Before running the project, ensure you have the following installed on your system:

1. **Java Development Kit (JDK)**: JDK 8 or higher (JDK 11 / 17 / 19+ supported).
2. **MySQL Database Server**: MySQL Server 5.7+ / 8.0+ or **XAMPP / WampServer / MariaDB**.
3. **IDE (Optional)**: IntelliJ IDEA, NetBeans, or Eclipse.

---

## 🚀 Installation & Setup Guide

### 1. Clone / Open the Repository
Clone the repository or open the project folder in your preferred Java IDE:
```bash
git clone https://github.com/saifur-rahman0/University-Management-System.git
```

### 2. Database Setup
1. Start your **MySQL Server** (e.g., via XAMPP Control Panel or MySQL service).
2. Open **phpMyAdmin** (`http://localhost/phpmyadmin`) or MySQL Workbench / Command Line.
3. Create a new database named `university_mng_sys`:
   ```sql
   CREATE DATABASE university_mng_sys;
   ```
4. Import the SQL file into `university_mng_sys`:
   - To start with a **clean schema**: import `collegedata.sql`.
   - To start with **rich sample data (students, faculty, courses, marks)**: import `collegedata with info.sql`.

   *Command line import example:*
   ```bash
   mysql -u root -p university_mng_sys < "collegedata with info.sql"
   ```

### 3. Configure Database Connection
Verify the database credentials in [`src/application/common/DataBaseConnection.java`](file:///e:/Programs/Projects/UMS%20(Group)/src/application/common/DataBaseConnection.java):

```java
// Local Database Configuration
static final String url = "jdbc:mysql://localhost:3306/university_mng_sys";
static final String uname = "root";
static final String password = ""; // Enter your MySQL root password if set
```

### 4. Add JAR Dependencies to Classpath
Ensure the external JAR libraries located in the `jar files/` folder are added to your project build path / classpath:
- `jar files/mysql-connector-java-5.1.38.jar`
- `jar files/rs2xml.jar`

**In IntelliJ IDEA:**
> File ➔ Project Structure ➔ Modules ➔ Dependencies ➔ `+` (JARs or Directories) ➔ Select files in `jar files/` ➔ Apply.

**In NetBeans:**
> Right-click project ➔ Properties ➔ Libraries ➔ Add JAR/Folder ➔ Select files in `jar files/`.

### 5. Run the Application
Run the main entry class:
- **Main Class**: [`application.login.LoginPageFrame`](file:///e:/Programs/Projects/UMS%20(Group)/src/application/login/LoginPageFrame.java)

#### Running via Terminal / CLI:
```bash
# Compile (Windows PowerShell example)
javac -cp "jar files/*;src" -d out/production/CMS src/application/login/LoginPageFrame.java src/application/*/*.java

# Run
java -cp "jar files/*;out/production/CMS;." application.login.LoginPageFrame
```

---

## 🔑 Default Login Credentials

If you imported `collegedata.sql` or `collegedata with info.sql`, use the following credentials to test each portal:

| Portal | User ID | Default Password | Notes |
|---|---|---|---|
| **Administrator** | `Admin` | `admin` | Full system control & configuration |
| **Teacher / Faculty** | `101` *(or Teacher ID)* | *Teacher's Date of Birth* (e.g., `12-May-1985`) | User ID is the allocated Faculty ID |
| **Student** | `CE-1-1001` *(or `Dept-Sem-Roll`)* | *Student's Date of Birth* (e.g., `05-Aug-2001`) | Format: `<Course/Dept>-<Sem>-<Roll>` |

---

## 📌 User ID & Password Conventions

- **Student User ID**: `<DepartmentCode>-<Semester>-<RollNumber>` (e.g., `IT-1-1001`, `CE-2-1002`).
- **Student Password**: The student's date of birth as registered during admission (e.g., `15-Jan-2002`).
- **Faculty User ID**: Faculty registration ID (e.g., `101`, `102`).
- **Faculty Password**: The teacher's date of birth registered in profile.
- **Subject Code**: `<CourseCode><Semester/Year><Sequence>` (e.g., `CE101`, `IT302`).

---

## 🛠 Technologies & Libraries

- **Programming Language**: Java (JDK 8 / 11 / 17 / 19)
- **GUI Framework**: Java Swing (`JFrame`, `JPanel`, `JTable`, `JScrollPane`, `Custom UI Components`) & AWT
- **Database Engine**: MySQL / MariaDB
- **Database Driver**: MySQL Connector/J (`mysql-connector-java-5.1.38.jar` / `mysql-connector-j-8.1.0`)
- **Table Data Binding**: `rs2xml.jar` (ResultSet to TableModel rendering)
- **Document / Marksheet Export**: Java Printing API (`java.awt.print.Printable`, `PrinterJob` -> Microsoft Print to PDF)
- **IDE Support**: IntelliJ IDEA, NetBeans (Apache Ant `build.xml`), Eclipse

---

## 📄 License & Contributions

This project was developed as an academic management solution. Feel free to fork, customize, and extend the features for institutional and educational use. Pull requests and feature enhancements are welcome!

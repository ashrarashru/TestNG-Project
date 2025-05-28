# Automation Testing Project (TestNG)
## Project Description
This project automates end-to-end testing for the [**Daily Finance**](https://dailyfinance.roadtocareer.net) web application to ensure essential features like user registration, password reset, item management, profile updates, and admin controls work correctly. It includes both positive and negative test cases to ensure the application's stability and reliability.

## Prerequisites
Before running the tests, ensure you have the following set up:

**System Requirements:**
- Operating System: Windows/Mac/Linux
- JDK (Java Development Kit) installed (Version 8 or above)
- Browser drivers installed (e.g., ChromeDriver for Chrome, FirefoxDriver for Firefox)

**Tools and Frameworks:**
- Selenium WebDriver
- JUnit or TestNG for test management
- Maven/Gradle for dependency management

**Credentials:**
- A valid Gmail account to perform and verify email-based actions
- Admin credentials for the application, securely provided via the terminal at runtime.

**Dependencies:**
- Update the ```build.gradle``` file with required libraries such as Selenium, JUnit/TestNG, and any email API libraries.

## What I Have Done
The following steps were automated and tested successfully:

**User Registration:**
1. Add all input field with valid data.
2. The email ID must be unregistered and use Gmail as the service provider.
3. After registration, the user receives a 'Congratulations' message in their Gmail inbox.

**Reset Password:**
1. Input unregistered email id and input blank data to reset password.
2. Add registered email id to reset data.
3. The reset link is sent to the email address.
4. Go to the email inbox and reset password via reset link.
5. After successfully resetting the password, log in with the new password to confirm successful login.
6. Log in using the old password to verify that access is denied.


**Add Items:**
1. Log in with a user account.
2. Click the "Add Cost" button to display two lists:
  • One for all items
  • One for mandatory items
3. Both lists should appear under the user's daily costs.

**User Gmail Update:**
1. Log in using the user's Gmail account and navigate to the user profile.
2. Enter an unregistered email in the email field, update the profile, and log out.
3. Log in using the new Gmail address to confirm successful login.
4. Attempt to log in with the old Gmail address to verify that access is denied.

**Admin Login**
1. Log in as an admin by securely passing credentials via the terminal.
2. After logging in, search for the updated Gmail ID from the dashboard.

## How to Run the Tests
Follow these steps to execute the automated tests:

- Clone the Repository
- Set Up the Environment
    - Install dependencies using Maven or Gradle
- Configure Credentials
    - Update the config.properties file with Gmail credentials and other necessary details.
    - Pass admin credentials securely when prompted by the terminal during execution.
- Run the Tests
- View Test Reports
    - After execution, access the test reports in the ```target/surefire-reports``` directory (for Maven) or ```build/reports/tests``` directory (for Gradle).

## Test Case Link
https://docs.google.com/spreadsheets/d/1PewZH0b5kE2DgxC-PQuKhy4wLRKfPYah/edit?usp=sharing&ouid=107234482738036161269&rtpof=true&sd=true

## Reports:

![01](https://github.com/user-attachments/assets/d516aa67-bf99-4804-8601-eb13777bc8a0)


![02](https://github.com/user-attachments/assets/57aa5c14-6877-4a0d-b11a-d37737806e71)


## Sample Video of Automation Process

https://github.com/user-attachments/assets/2cac5959-85c9-485e-aaac-ecc19b061083




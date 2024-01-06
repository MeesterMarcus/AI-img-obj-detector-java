# Image Object Detector - Java

This API allows a user to store image data as well as objects detected within the images while leveraging the Imagga
API.

## Installation

Follow these steps to get the service up and running:

1. **Install dependencies via Maven:**
    - Navigate to the project directory and run `mvn clean install`.

2. **Create Run Configuration (IntelliJ):**
    - Open the project in IntelliJ IDEA.
    - Go to `Run` > `Edit Configurations...`.
    - Add a new Java Application configuration.
    - Set `ImageObjectDetectorApplication` as the main class.

3. **Select Java 18:**
    - Within the Run Configuration, make sure to set the JDK to Java 18.

4. **Run the Application:**
    - Click the run icon, or press `Shift + F10` (or `Control + R` on Mac).

## How to Use

Here are some ways to interact with the service:

- **Postman Collection:**
    - A Postman collection is available in the `resources` folder for quick and easy testing.

- **Swagger Documentation:**
    - Access the Swagger UI documentation at http://localhost:8080/swagger-ui/index.html to interact with the API and understand the available endpoints.

### Note

Ensure that your environment is correctly set up with Java 18 and Maven before attempting to run the application. Enjoy detecting objects in images!


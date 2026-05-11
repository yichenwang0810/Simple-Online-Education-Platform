# Simple-Online-Education-Platform
This platform represents a Learning Management System (LMS) designed for scalability.

Technology Stack Frontend: Vue.js 3 (with Vite), Element UI (for interface components), Axios (for API requests). Backend: Java Spring Boot (for business logic), MyBatis Plus (for database interaction). Database: MySQL (for relational data like users and courses). Storage: Local storage or Cloud OSS (for video/document hosting).

Project Structure:
online-education-platform/
├── backend/                  # Spring Boot Application
│   ├── src/main/java/com/edu/
│   │   ├── controller/       # API Endpoints (Course, User, Order)
│   │   ├── service/          # Business Logic
│   │   ├── entity/           # Database Models
│   │   └── config/           # Security & CORS Config
│   └── pom.xml
└── frontend/                 # Vue.js Application
    ├── src/
    │   ├── api/              # API definitions
    │   ├── components/       # Reusable UI components (VideoPlayer, Navbar)
    │   ├── views/            # Pages (Home, CourseDetail, StudentDashboard)
    │   └── App.vue
    └── package.json
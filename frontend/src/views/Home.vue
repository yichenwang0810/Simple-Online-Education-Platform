<template>
  <div class="home-container">
    <header class="navbar">
      <h1>EduPlatform</h1>
      <nav>
        <a href="#">Courses</a>
        <a href="#">Mentors</a>
        <a href="/login">Login</a>
      </nav>
    </header>

    <main class="course-grid">
      <h2>Featured Courses</h2>
      <div class="grid">
        <div v-for="course in courses" :key="course.id" class="course-card">
          <img :src="course.coverImage" :alt="course.title" />
          <div class="card-body">
            <h3>{{ course.title }}</h3>
            <p class="instructor">By {{ course.instructorName }}</p>
            <div class="price-row">
              <span class="price">¥{{ course.price }}</span>
              <button @click="viewDetails(course.id)">View Details</button>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      courses: []
    };
  },
  mounted() {
    // Fetch courses from the Spring Boot backend
    axios.get('http://localhost:8080/api/courses')
      .then(response => {
        this.courses = response.data;
      })
      .catch(error => {
        console.error("Error fetching courses:", error);
      });
  },
  methods: {
    viewDetails(id) {
      this.$router.push(`/course/${id}`);
    }
  }
};
</script>

<style scoped>
/* Simple CSS for layout */
.course-grid { padding: 20px; }
.grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); gap: 20px; }
.course-card { border: 1px solid #ddd; border-radius: 8px; overflow: hidden; transition: transform 0.2s; }
.course-card:hover { transform: translateY(-5px); box-shadow: 0 4px 12px rgba(0,0,0,0.1); }
.card-body { padding: 15px; }
.price { font-weight: bold; color: #ff6b35; font-size: 1.2em; }
button { background: #007bff; color: white; border: none; padding: 8px 12px; border-radius: 4px; cursor: pointer; }
</style>
<template>
  <div class="dashboard-container">
    <h1>My Learning Dashboard</h1>
    <div class="stats-cards">
      <div class="card">
        <h3>Courses Enrolled</h3>
        <p class="number">{{ enrolledCount }}</p>
      </div>
      <div class="card">
        <h3>Certificates</h3>
        <p class="number">{{ certificatesCount }}</p>
      </div>
    </div>

    <h2>My Courses</h2>
    <div v-if="myCourses.length === 0" class="empty-state">
      <p>You haven't enrolled in any courses yet.</p>
      <button @click="$router.push('/')">Browse Courses</button>
    </div>
    <div v-else class="course-list">
      <div v-for="course in myCourses" :key="course.id" class="course-item">
        <h3>{{ course.title }}</h3>
        <div class="progress-bar">
          <div class="progress" :style="{ width: course.progress + '%' }"></div>
        </div>
        <p>{{ course.progress }}% Completed</p>
        <button @click="continueCourse(course.id)">Continue Learning</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      enrolledCount: 0,
      certificatesCount: 0,
      myCourses: []
    };
  },
  mounted() {
    this.loadDashboardData();
  },
  methods: {
    async loadDashboardData() {
      try {
        // Get current user ID from localStorage or auth context
        const userId = localStorage.getItem('userId') || 1; // Default to 1 for demo

        // Fetch enrolled courses
        const response = await axios.get(`http://localhost:8080/api/enrollment/my-courses/${userId}`);
        this.myCourses = response.data.map(item => ({
          id: item.enrollment.courseId,
          title: `Course ${item.enrollment.courseId}`, // In real app, fetch course details
          progress: item.progress
        }));
        this.enrolledCount = this.myCourses.length;
      } catch (error) {
        console.error('Error loading dashboard data:', error);
        // Fallback to mock data
        this.myCourses = [
          { id: 1, title: 'Java Masterclass', progress: 45 },
          { id: 2, title: 'Vue.js for Beginners', progress: 10 }
        ];
        this.enrolledCount = 2;
      }
    },
    continueCourse(courseId) {
      this.$router.push(`/course/${courseId}`);
    }
  }
};
</script>

<style scoped>
.dashboard-container { padding: 40px; max-width: 1000px; margin: 0 auto; }
.stats-cards { display: flex; gap: 20px; margin-bottom: 40px; }
.card { flex: 1; background: #f8f9fa; padding: 20px; border-radius: 8px; text-align: center; }
.number { font-size: 2em; font-weight: bold; color: #007bff; }
.course-item { border: 1px solid #eee; padding: 20px; margin-bottom: 15px; border-radius: 8px; }
.progress-bar { background: #eee; height: 10px; border-radius: 5px; margin: 10px 0; }
.progress { background: #28a745; height: 100%; border-radius: 5px; }
.empty-state { text-align: center; padding: 50px; color: #666; }
</style>
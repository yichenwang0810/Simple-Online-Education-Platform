<template>
  <div class="course-detail-container">
    <header class="course-header">
      <h1>{{ course.title }}</h1>
      <p class="instructor">Instructor: {{ course.instructorName }}</p>
    </header>

    <main class="course-content">
      <section class="course-info">
        <img :src="course.cover" :alt="course.title" class="course-cover" />
        <div class="course-meta">
          <h2>Course Details</h2>
          <p><strong>Price:</strong> ¥{{ course.price }}</p>
          <p><strong>Views:</strong> {{ course.viewCount }}</p>
          <button class="buy-button">Enroll Now</button>
        </div>
      </section>

      <section class="course-comments">
        <h2>Student Reviews</h2>
        <div v-for="comment in comments" :key="comment.id" class="comment-card">
          <p><strong>{{ comment.userName }}</strong> <span>(Rating: {{ comment.rating }}/5)</span></p>
          <p>{{ comment.content }}</p>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      course: {},
      comments: []
    };
  },
  mounted() {
    const courseId = this.$route.params.id;
    // Fetch course details
    axios.get(`http://localhost:8080/api/courses/${courseId}`)
      .then(response => {
        this.course = response.data;
      });
    // In a real app, you'd fetch comments from a CommentController
    this.comments = [
      { id: 1, userName: 'Alice', rating: 5, content: 'Excellent course!' },
      { id: 2, userName: 'Bob', rating: 4, content: 'Very informative.' }
    ];
  }
};
</script>

<style scoped>
.course-detail-container { padding: 20px; max-width: 900px; margin: 0 auto; }
.course-header { text-align: center; margin-bottom: 40px; }
.course-info { display: flex; gap: 30px; }
.course-cover { width: 300px; border-radius: 8px; }
.course-comments { margin-top: 50px; }
.comment-card { border-bottom: 1px solid #eee; padding: 15px 0; }
.buy-button { background-color: #28a745; color: white; padding: 12px 25px; font-size: 1.1em; border: none; border-radius: 5px; cursor: pointer; margin-top: 20px; }
</style>
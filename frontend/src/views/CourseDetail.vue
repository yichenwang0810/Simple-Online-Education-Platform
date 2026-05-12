<template>
  <div class="course-detail-container">
    <header class="course-header">
      <h1>{{ course.title }}</h1>
      <p class="instructor">Instructor: {{ course.instructorName }}</p>
    </header>

    <main class="course-content">
      <!-- Tabs -->
      <div class="tabs">
        <button :class="{ active: activeTab === 'overview' }" @click="activeTab = 'overview'">Overview</button>
        <button :class="{ active: activeTab === 'content' }" @click="activeTab = 'content'">Content</button>
      </div>

      <!-- Tab Content -->
      <div v-show="activeTab === 'overview'" class="tab-pane">
        <section class="course-info">
          <img :src="course.cover" :alt="course.title" class="course-cover" />
          <div class="course-meta">
            <h2>About this course</h2>
            <p><strong>Price:</strong> ¥{{ course.price }}</p>
            <p><strong>Views:</strong> {{ course.viewCount }}</p>
            <button @click="addToCart" class="buy-button">Add to Cart</button>
          </div>
        </section>

        <section class="course-comments">
          <h2>Student Reviews</h2>
          <div v-for="comment in comments" :key="comment.id" class="comment-card">
            <p><strong>{{ comment.userName }}</strong> <span>(Rating: {{ comment.rating }}/5)</span></p>
            <p>{{ comment.content }}</p>
          </div>
        </section>
      </div>

      <div v-show="activeTab === 'content'" class="tab-pane">
        <h2>Course Content</h2>
        <div v-if="chapters.length > 0">
          <div v-for="chapter in chapters" :key="chapter.id" class="chapter-item">
            <h3>{{ chapter.title }}</h3>
            <ul class="lessons-list">
              <li v-for="lesson in chapter.lessons" :key="lesson.id" class="lesson-item">
                <a href="#" @click.prevent="playLesson(lesson)">{{ lesson.title }}</a>
              </li>
            </ul>
          </div>
        </div>
        <p v-else>No content available yet.</p>
      </div>
    </main>

    <!-- Modal for playing lesson video -->
    <div v-if="currentLesson" class="modal" @click="closeModal">
      <div class="modal-content" @click.stop>
        <span class="close" @click="closeModal">&times;</span>
        <h3>{{ currentLesson.title }}</h3>
        <VideoPlayer :video-url="currentLesson.videoUrl" />
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import VideoPlayer from '../components/VideoPlayer.vue'; // Import the component
import { getCourseDetail } from '../api/course'; // Use the API utility

export default {
  components: {
    VideoPlayer
  },
  data() {
    return {
      course: {},
      comments: [],
      chapters: [],
      activeTab: 'overview',
      currentLesson: null
    };
  },
  mounted() {
    const courseId = this.$route.params.id;
    this.fetchCourseDetails(courseId);
    this.fetchCourseChapters(courseId);
    // Mock comments
    this.comments = [
      { id: 1, userName: 'Alice', rating: 5, content: 'Excellent course!' },
      { id: 2, userName: 'Bob', rating: 4, content: 'Very informative.' }
    ];
  },
  methods: {
    async fetchCourseDetails(id) {
      // Use the centralized API function
      this.course = await getCourseDetail(id);
    },
    async fetchCourseChapters(courseId) {
      try {
        const response = await axios.get(`http://localhost:8080/api/content/chapters/${courseId}`);
        this.chapters = response.data.map(chapter => {
          // Fetch lessons for each chapter
          chapter.lessons = [];
          this.fetchLessonsForChapter(chapter.id).then(lessons => {
            chapter.lessons = lessons;
          });
          return chapter;
        });
      } catch (error) {
        console.error("Failed to load chapters:", error);
      }
    },
    async fetchLessonsForChapter(chapterId) {
      const response = await axios.get(`http://localhost:8080/api/content/lessons/chapter/${chapterId}`);
      return response.data;
    },
    addToCart() {
      const userId = 1; // In a real app, get this from logged-in user context
      const cartItem = {
        userId: userId,
        courseId: this.course.id,
        courseTitle: this.course.title,
        price: this.course.price,
        quantity: 1
      };
      axios.post('http://localhost:8080/api/cart/add', cartItem)
        .then(() => alert('Added to cart!'))
        .catch(err => console.error(err));
    },
    playLesson(lesson) {
      this.currentLesson = lesson;
    },
    closeModal() {
      this.currentLesson = null;
    }
  }
};
</script>

<style scoped>
/* ... (existing styles remain) ... */
.tabs { margin-bottom: 20px; }
.tabs button { padding: 10px 20px; margin-right: 10px; border: 1px solid #ccc; background-color: #f0f0f0; cursor: pointer; }
.tabs button.active { background-color: #007bff; color: white; }
.tab-pane { display: block; }
.chapter-item { border-bottom: 1px solid #eee; padding: 15px 0; }
.lessons-list { margin-left: 20px; }
.lesson-item { list-style-type: disc; margin: 5px 0; }
.modal { display: block; position: fixed; z-index: 1000; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.5); }
.modal-content { background-color: white; margin: 5% auto; padding: 20px; width: 80%; max-width: 800px; border-radius: 5px; position: relative; }
.close { position: absolute; right: 10px; top: 10px; font-size: 28px; font-weight: bold; cursor: pointer; }
</style>
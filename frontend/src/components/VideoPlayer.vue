<template>
  <div class="video-player-container">
    <video
      ref="videoRef"
      :src="videoUrl"
      controls
      width="100%"
      height="auto"
      @timeupdate="onTimeUpdate"
      @ended="onVideoEnd"
    >
      Your browser does not support the video tag.
    </video>
    <div class="progress-info">
      <p>Current Time: {{ currentTime }} / Duration: {{ duration }}</p>
      <p>Progress: {{ Math.round(progressPercentage) }}%</p>
    </div>
  </div>
</template>

<script>
import { ref, onMounted, onUnmounted } from 'vue';
import axios from 'axios';

export default {
  name: 'VideoPlayer',
  props: {
    videoUrl: {
      type: String,
      required: true
    },
    initialTime: {
      type: Number,
      default: 0
    },
    lessonId: {
      type: Number,
      required: true
    }
  },
  setup(props) {
    const videoRef = ref(null);
    const currentTime = ref(0);
    const duration = ref(0);
    const progressPercentage = ref(0);
    let progressUpdateTimer = null;

    const onTimeUpdate = () => {
      if (videoRef.value) {
        currentTime.value = videoRef.value.currentTime;
        duration.value = videoRef.value.duration || 0;
        progressPercentage.value = (currentTime.value / duration.value) * 100;

        // Throttle progress updates to avoid too many API calls
        if (!progressUpdateTimer) {
          progressUpdateTimer = setTimeout(() => {
            updateProgress();
            progressUpdateTimer = null;
          }, 5000); // Update every 5 seconds
        }
      }
    };

    const updateProgress = async () => {
      try {
        const userId = localStorage.getItem('userId') || 1;
        await axios.post('http://localhost:8080/api/progress/lesson', {
          studentId: userId,
          lessonId: props.lessonId,
          progressPercentage: Math.round(progressPercentage.value),
          lastWatchedTime: Math.round(currentTime.value)
        });
      } catch (error) {
        console.error('Error updating progress:', error);
      }
    };

    const onVideoEnd = async () => {
      console.log("Video completed!");
      // Mark as completed
      try {
        const userId = localStorage.getItem('userId') || 1;
        await axios.post(`http://localhost:8080/api/progress/lesson/${userId}/${props.lessonId}/complete`);
      } catch (error) {
        console.error('Error marking lesson complete:', error);
      }
    };

    // Restore play position when component mounts
    onMounted(() => {
      if (videoRef.value) {
        videoRef.value.currentTime = props.initialTime;
      }
    });

    onUnmounted(() => {
      if (progressUpdateTimer) {
        clearTimeout(progressUpdateTimer);
      }
    });

    return {
      videoRef,
      currentTime,
      duration,
      progressPercentage,
      onTimeUpdate,
      onVideoEnd
    };
  }
};
</script>

<style scoped>
.video-player-container { margin: 20px 0; }
.progress-info { margin-top: 10px; font-size: 0.9em; color: #666; }
</style>
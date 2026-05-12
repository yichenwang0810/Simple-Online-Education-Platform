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
    }
  },
  setup(props) {
    const videoRef = ref(null);
    const currentTime = ref(0);
    const duration = ref(0);
    const progressPercentage = ref(0);

    const onTimeUpdate = () => {
      if (videoRef.value) {
        currentTime.value = videoRef.value.currentTime;
        duration.value = videoRef.value.duration || 0;
        progressPercentage.value = (currentTime.value / duration.value) * 100;
      }
    };

    const onVideoEnd = () => {
      console.log("Video completed!");
      // Here you could trigger a notification or move to the next lesson
    };

    // Restore play position when component mounts
    onMounted(() => {
      if (videoRef.value) {
        videoRef.value.currentTime = props.initialTime;
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
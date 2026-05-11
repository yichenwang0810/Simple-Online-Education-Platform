<template>
  <div class="login-container">
    <div class="login-box">
      <h2>Welcome Back</h2>
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label>Username</label>
          <input type="text" v-model="username" placeholder="Enter username" required />
        </div>
        <div class="form-group">
          <label>Password</label>
          <input type="password" v-model="password" placeholder="Enter password" required />
        </div>
        <button type="submit" class="login-btn">Login</button>
        <p v-if="error" class="error-msg">{{ error }}</p>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
      error: ''
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post('http://localhost:8080/api/auth/login', {
          username: this.username,
          password: this.password
        });

        if (response.data.success) {
          // Save token to local storage
          localStorage.setItem('token', response.data.token);
          localStorage.setItem('user', JSON.stringify(response.data.user));
          alert('Login Successful!');
          this.$router.push('/'); // Redirect to home
        } else {
          this.error = response.data.message;
        }
      } catch (err) {
        this.error = 'Server connection error';
      }
    }
  }
};
</script>

<style scoped>
.login-container { display: flex; justify-content: center; align-items: center; height: 80vh; background-color: #f4f7f6; }
.login-box { background: white; padding: 40px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); width: 350px; }
.form-group { margin-bottom: 15px; }
.form-group label { display: block; margin-bottom: 5px; color: #333; }
.form-group input { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; box-sizing: border-box; }
.login-btn { width: 100%; padding: 12px; background: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; }
.login-btn:hover { background: #0056b3; }
.error-msg { color: red; text-align: center; margin-top: 15px; font-size: 14px; }
</style>
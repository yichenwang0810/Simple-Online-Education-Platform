import axios from 'axios';

// Create an axios instance
const service = axios.create({
  baseURL: 'http://localhost:8080', // Backend URL
  timeout: 5000 // Request timeout
});

// Request Interceptor (adds token to headers)
service.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = token;
    }
    return config;
  },
  error => {
    console.error('Request Error:', error);
    return Promise.reject(error);
  }
);

// Response Interceptor (handles errors globally)
service.interceptors.response.use(
  response => response.data,
  error => {
    console.error('Response Error:', error);
    if (error.response && error.response.status === 401) {
      alert('Session expired, please login again');
      // Redirect to login logic here
    }
    return Promise.reject(error);
  }
);

export default service;
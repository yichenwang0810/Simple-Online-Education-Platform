import request from './config';

// Centralized API calls for Courses
export function getCourseList(params) {
  return request({
    url: '/api/courses',
    method: 'get',
    params
  });
}

export function getCourseDetail(id) {
  return request({
    url: `/api/courses/${id}`,
    method: 'get'
  });
}
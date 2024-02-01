<template>
  <div>
    <h1>Dashboard</h1>
    <button @click="logout">Logout</button>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export default defineComponent({
  name: 'Dashboard',
  setup() {
    const router = useRouter();
    const logout = async () => {
      const token = localStorage.getItem('token');
      if (!token) {
        console.error('No token found');
        return;
      }

      try {
        await axios.post('http://localhost:8080/user/logout', {}, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        localStorage.removeItem('token');
        router.push('/');
        console.log('Logged out successfully');
      } catch (error) {
        console.error('Logout failed', error);
      }
    };
    return { logout };
  }
});
</script>

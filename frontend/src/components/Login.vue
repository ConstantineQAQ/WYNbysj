<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="login">
      <div>
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username">
        <span v-if="!isUsernameValid">Username is required</span>
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password">
        <span v-if="!isPasswordValid">Password is required</span>
      </div>
      <button type="submit">Login</button>
      <p v-if="loginError">{{ loginErrorMessage }}</p>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export default defineComponent({
  name: 'Login',
  setup() {
    const username = ref('');
    const password = ref('');
    const loginError = ref(false);
    const loginErrorMessage = ref('');
    const router = useRouter();

    const isUsernameValid = computed(() => username.value.trim().length > 0);
    const isPasswordValid = computed(() => password.value.trim().length > 0);

    const login = async () => {
      if (!isUsernameValid.value || !isPasswordValid.value) {
        loginError.value = true;
        loginErrorMessage.value = 'Username and password are required.';
        return;
      }
        const response = await axios.post('http://localhost:8080/user/login', {
          userName: username.value,
          password: password.value
        });
        console.log(response.data.code);
        if (response.data.code === 200) {
          console.log(response.data.data.token)
          localStorage.setItem('token', response.data.data.token);
          router.push('/dashboard');
        } else {
          alert(response.data.message);
        }
    };

    return { username, password, login, isUsernameValid, isPasswordValid, loginError, loginErrorMessage };
  }
});
</script>

<style>
/* Add your styles here */
</style>

<template>
  <v-app-bar app color="primary" class="header-page">
    <v-app-bar-nav-icon @click="showHeaderMenu"></v-app-bar-nav-icon>
    <v-toolbar-title>My App</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn text>Home</v-btn>
    <v-btn text>About</v-btn>
    <v-btn text>Contact</v-btn>
  </v-app-bar>
</template>

<script>

export default {
  name: 'HeaderContent',

  methods: {
    showHeaderMenu() {
      const clickTagInfo = {                                                                                            // clickTagInfo - HeaderContent 에서 클릭한 태그 정보 HeaderMenu hideHeaderMenu() 메서드 실행 조건
        tagName: 'v-app-bar-icon',
      };
      this.$emit('showHeaderMenu', clickTagInfo);
    },
  },
}
</script>

<style scoped>
/* CSS styles for Header.vue */
</style>

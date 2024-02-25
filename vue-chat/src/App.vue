<template>
  <v-app>
    <HeaderContent @showHeaderMenu="showHeaderMenu" />
    <MainContent />
    <HeaderMenu @hideHeaderMenu="hideHeaderMenu" @initClickTagInfo="initClickTagInfo"
                :isHeaderMenuOpen="isHeaderMenuOpen" :headerMenuHeight="headerMenuHeight" :clickTagInfo="clickTagInfo"/>
  </v-app>
</template>

<script>
import HeaderContent  from './components/HeaderContent.vue';
import HeaderMenu     from './components/HeaderMenu.vue';
import MainContent    from './components/MainContent.vue';

export default {
  name: 'App',

  components: {
    HeaderContent,
    MainContent,
    HeaderMenu,
  },

  data() {
    return {
      isHeaderMenuOpen        : false,
      headerMenuHeight  : 0,
      clickTagInfo      : null,
    };
  },

  methods: {
    showHeaderMenu(clickTagInfo) {                                                                                      // showHeaderMenu() - HeaderMenu 를 보여줄 지 결정하는 메서드
      const headerPageElement = document.querySelector('.header-page');                                                 // HeaderMenu 의 top 값 조정
      const headerPageHeight  = headerPageElement.offsetHeight;                                                         // :
      this.headerMenuHeight = headerPageHeight;                                                                         // :

      this.isHeaderMenuOpen = !this.isHeaderMenuOpen;                                                                   // isHeaderMenuOpen - HeaderMenu 숨김 여부 결정
      this.clickTagInfo     = clickTagInfo;                                                                             // clickTagInfo     - HeaderContent 에서 클릭한 태그 정보 HeaderMenu hideHeaderMenu() 메서드 실행 조건
    },

    hideHeaderMenu() {                                                                                                  // hideHeaderMenu() - HeaderMenu 숨기는 메서드
      this.isHeaderMenuOpen = false;
    },

    initClickTagInfo() {                                                                                                // initClickTagInfo() - HeaderMenu 의 props: clickTagInfo 를 초기화
      this.clickTagInfo = null;
    },
  }
};
</script>
<style>
/* CSS styles for App.vue */
</style>
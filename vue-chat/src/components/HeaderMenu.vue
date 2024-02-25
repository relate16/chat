<template>
  <div class="menu-drawer" :class="{ 'show': isHeaderMenuOpen }" :style="{ top: headerMenuHeight + 'px' }"
                           v-click-outside="handleOutsideClick">
    <ul>
      <li>Menu Item 1</li>
      <li>Menu Item 2</li>
      <li>Menu Item 3</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'HeaderMenu',

  props: {
    isHeaderMenuOpen  : Boolean,                                                                                        // isHeaderMenuOpen - 메뉴 내용 표시 여부
    headerMenuHeight  : Number,                                                                                         // headerMenuHeight - HeaderMenu 의 top 높이
    clickTagInfo      : Object,                                                                                         // clickTagInfo     - HeaderContent 에서 클릭한 태그 정보 hideHeaderMenu() 메서드 실행 조건
  },

  methods: {
    hideHeaderMenu() {
      this.$emit('hideHeaderMenu');
    },

    initClickTagInfo() {                                                                                                // initClickTagInfo - clickTagInfo 값 초기화
      this.$emit('initClickTagInfo');
    },

    handleOutsideClick() {                                                                                              // handleOutsideClick - HeaderMenu 컴포넌트 바깥을 클릭했을 경우, 발동하는 메서드
      if (this.$props?.clickTagInfo?.tagName != 'v-app-bar-icon') this.hideHeaderMenu();                                // 클릭된 요소(=clickTagInfo)가 HeaderContent.vue 의 v-app-bar-icon 인 경우에만 hideHeaderMenu()를 호출합니다.

      this.initClickTagInfo();                                                                                          // 재클릭할 때 정상동작을 위해 clickTagInfo 값 초기화
    },
  },

}
</script>

<style scoped>
.menu-drawer {
  position: fixed;
  top: 0;
  left: 0;
  width: 200px;
  height: 100vh;
  background-color: #f0f0f0;
  transform: translateX(-100%);
  transition: transform 0.3s ease;
}

.menu-drawer.show {
  transform: translateX(0);
}

ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

li {
  padding: 10px;
  border-bottom: 1px solid #ccc;
}
</style>
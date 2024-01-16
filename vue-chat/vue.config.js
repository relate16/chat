const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  outputDir: "../src/main/resources/static",                                                                            // 빌드 타겟 경로
  devServer: {
    proxy: {
      '/proxy': {                                                                                                       // '~:8081/api' 로 들어오면 스프링 서버('~:8080/api')로 보냄
        target: 'http://localhost:8080',                                                                                // target - 스프링 서버
        changeOrigin: true                                                                                              // 서로 다른 포트의 리소스 공유(spring: 8080 포트, vue: 8081 포트)
      }
    }
  }
})

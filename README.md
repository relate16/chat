#프로젝트 실행 방법
## 아래 순서를 따라 하나씩 실행하시면 됩니다. 
### 1. node.js 가 설치되어 있지 않은 경우(node -v 명령어로 확인할 수 있습니다)
```
https://nodejs.org 에서 노드를 설치합니다.
```
### 2. vue_cli 가 설치되어 있지 않은 경우(vue --version 명령어로 확인할 수 있습니다)
```
터미널에서 
  npm install -g @vue/cli 
명령어를 실행합니다.
```
#### 2-1. intellij 터미널에서 npm 명령어 오류가 날 경우
```
1. Intellij를 관리자 권한으로 실행합니다.
2. Terminal에서 get-executionpolicy 명령어로 실행정책을 확인합니다.("Restricted(제한된)"인 지 확인)
3. RemoteSigned(Windows server 컴퓨터에 대 한 기본 실행 정책)로 되어 있지 않다면 set-executionpolicy remotesigned 명령어로 실행정책을 변경합니다.
4. get-executionpolicy 명령어로 현재 실행정책을 확인하고 RemoteSigned로 변경되어 있다면 다시 npm 명령어를 실행해 봅니다.
```
### 3. Vue 프로젝트 실행
```
터미널에서
  cd vue-chat
  npm run serve
명령어를 실행합니다.
```
### 기타. querydsl 사용 방법 
```
Q파일 생성 :
Tasks>build 혹은 Tasks>clean>build 혹은 ChatApplication.class 실행
```
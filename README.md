# TodoList
투두앱 백엔드 서버 만들기
할일을 등록하고 상태를 관리할 수 있다.
- 할일카드를 작성, 수정, 삭제, 조회 할 수 있다.
- 할일 카드를 완료상태로 변경할 수 있다.
- 댓글을 작성 할 수 있으며 작성 시 아이디와 비밀번호를 입력받는다.
- 아이디와 비밀번호 일치시에만 수정, 삭제가 가능하다.
- 오름차순 내림차순으로 정렬이 가능하다.
<br>

<!--
# Why?
## STEP 1
1. 수정, 삭제 API의 request를 어떤 방식으로 사용 하셨나요? (param, query, body)
2. RESTful한 API를 설계하셨나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
3. 적절한 관심사 분리를 적용하셨나요? (Controller, Service, Repository)
4. API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!

## STEP 2
1. 처음 설계한 API 명세서에 변경사항이 있었나요? 
변경 되었다면 어떤 점 때문 일까요? 첫 설계의 중요성에 대해 작성해 주세요!
2. ERD를 먼저 설계한 후 Entity를 개발했을 때 어떤 점이 도움이 되셨나요?
3. 만약 댓글이 여러개 달려있는 할일을 삭제하려고 한다면 무슨 문제가 발생할까요? Database 테이블 관점에서 해결방법이 무엇일까요?
4. IoC / DI 에 대해 간략하게 설명해 주세요!

<br>
-->

# 설계
## ERD 
![image](https://github.com/taeaeaeae/TodoList_kotlin/assets/46617216/1c6acb8c-45ae-4dcc-adaa-fda195760752)
<br>

## 유스케이스 다이어그램
<br>

## API 명세서
### TODO
기능|method|URL|response
--|--|--|--
목록조회|`GET`|/todos|등록된 할일들 정보
할일조회|`GET`|/todo/{todoId}|등록된 할일 정보
할일등록|`POST`|/todos|할일 등록 결과
할일수정|`PUT`|/todos/{todoId}|할일 수정 결과
할일삭제|`DELETE`|/todo/{todoId}|할일 삭제 결과

### REPLY
기능|method|URL|response
--|--|--|--
댓글조회|`GET`|/todo/{todoId}/reply|등록된 댓글 내용
댓글등록|`POST`|/todo/{todoId}/reply|댓글 등록 결과
댓글수정|`PUT`|/todos/{todoId}/reply/{replyId}|댓글 수정 결과
댓글삭제|`DELETE`|/todo/{todoId}/reply/{replyId}|댓글 삭제 결과

<br>

# 패키지 구조

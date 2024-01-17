<div align="center">
<h1>Matching-Service</h1>
<h3> 기술, 시간, 장소를 고려하여 자동으로 멘토링을 신청하는 서비스

매칭 서비스</h3>
</div>

<br>
<br>


## Architecture
<img width="9116" alt="아키텍처" src="https://github.com/Dokcer-DevLink/matching-service/assets/80077569/af6712a4-0f52-44b5-9720-6d028a216e57">

<br>
<br>

## Description

### - 자동 멘토링 신청 기능 ( 매칭 서비스 )
유저가 입력한 기술, 시간, 장소와 가장 부합하는 게시글을 찾고 게시글 작성자에게 멘토링 신청하는 기능이다. 

1) 프로필 서비스에서 요청한 유저의 기술스택 조회
2) 포스트 서비스에서 유저의 기술스택과 유저가 입력한 장소에 부합한 포스트(게시글) 리스트 조회
3) 프로필 서비스에서 조회된 포스트 리스트 작성자 중 유저가 원하는 시간대에 스케줄이 비어있는 유저 필터링
4) 멘토링 서비스에서 필터링 된 유저 리스트 중 우선순위가 가장 높은 유저에게 멘토링 신청

### - Kakao Address API 사용
유저가 입력한 장소와 포스트 장소의 거리를 비교할 때, 위도 경도를 이용한 하버사인 공식을 사용하므로 위도, 경도 데이터를 얻기 위해 kakao Address API를 사용하였다. 

<br>
<br>
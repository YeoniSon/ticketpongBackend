# 🎟️ TicketPong (Upgrade project)
> 이 프로젝트는 기존 Node.js로 개발되었던 팀 프로젝트 TicketPong을 Java와 Spring Boot 기반으로 재구축하는 마이그레이션 프로젝트입니다. 
> 
>단순히 언어를 전환하는 것에 그치지 않고, 기존 시스템의 한계를 극복하고 운영 안정성을 확보하는 데 목적이 있습니다.
> 
---
## 🖥️ 실습 url 


---

# 🎯업그레이드 핵심 목표

1. 기술 스택 전환 및 안정성 강화 (Language & Logic)

   Java 17 & Spring Boot 3.x 마이그레이션: 
    - 정적 타입 언어와 Spring의 강력한 생태계를 활용하여 런타임 에러를 줄이고, 대규모 트래픽 처리에 적합한 멀티 스레드 환경을 구축합니다.

    - 외부 API 통신 결함 해결: 이전 프로젝트에서 발생했던 외부 API 연동 오류를 분석하여, 예외 처리 로직을 강화하고 데이터 매핑의 정확도를 높입니다.


2. 데이터 가용성 및 성능 최적화 (Redis)

   Redis 기반 데이터 캐싱: 
   - 외부 API 의존성을 낮추기 위해 수신 데이터를 Redis에 전략적으로 저장합니다. 이를 통해 외부 API 서버 장애 시에도 끊김 없는 서비스를 제공(Fallback)하고 응답 속도를 비약적으로 향상시킵니다.
    
   실시간 데이터 관리: 
   - 빈번하게 변하는 공연 잔여석 정보나 실시간 랭킹 데이터를 메모리 내에서 처리하여 DB 부하를 줄입니다.


3. 인프라 표준화 및 격리 (Docker)

   컨테이너 기반 환경 구축: 
    - MySQL, Redis 등 필수 인프라를 Docker 컨테이너로 추상화하여 로컬 개발 환경과 실제 배포 환경의 일관성을 100% 유지합니다.
    
   배포 유연성 확보: 
    - docker-compose를 활용하여 복잡한 인프라 설정을 코드 한 줄로 실행 가능하게 만들고, 향후 클라우드(AWS) 확장을 용이하게 합니다.


4. 확장 가능한 아키텍처 설계 (Multi-Module)

   모듈별 책임 분리: 
    - member, performance, reservation, review로 도메인을 완전히 분리하여 유지보수 효율을 높입니다.
    
   독립적 확장성: 
   - 향후 특정 도메인(예: 예매)의 트래픽 급증 시 해당 모듈만 마이크로서비스(MSA)로 분리할 수 있는 기반을 다집니다.

# 🛠 Tech Stack
- Backend: Java 17, Spring Boot 3.x, Spring Data JPA

- Architecture: Multi-Module, RESTful API

- Database: MySQL 8.0, Redis (for Caching/Lock)

- Security: Spring Security, JWT, .env 기반 환경 변수 관리

# ⭐️ 기능
1. Member Module (회원 및 인증)
- [ ] 보안 로그인/회원가입: Spring Security와 JWT를 활용한 인증 체계.
- [ ] 이메일 인증: Java Mail Sender를 이용한 회원가입 승인 및 비밀번호 재설정.
- [ ] 권한 관리: 일반 사용자(User)와 공연 등록자(Admin) 권한 분리.
- [ ] 마이페이지: 예매 내역 확인 및 회원 정보 수정.

2. Performance Module (공연 및 전시)
- [ ] 외부 API 연동 (Key Upgrade): 외부 티켓팅 API 데이터를 수집하고 안정적으로 매핑.

- [ ] Redis 캐싱: 빈번하게 조회되는 공연 상세 정보 및 목록을 Redis에 캐싱하여 외부 API 의존도 감소.

- [ ] 공연 검색/필터링: Querydsl을 활용하여 장르, 지역, 날짜별 최적화된 검색 제공.

- [ ] 실시간 랭킹: Redis의 Sorted Set을 활용한 실시간 인기 공연 순위 집계.

3. Reservation Module (예매 핵심 로직)
- [ ] 실시간 잔여석 관리: Redis를 활용하여 DB 부하 없이 실시간 좌석 상태 업데이트.

- [ ] 동시성 제어: 인기 공연 예매 시 발생하는 동시성 문제를 Redis 분산 락(Redisson) 등을 통해 해결.

- [ ] 예매 프로세스: 티켓 선택 → 결제 가상 프로세스 → 예매 확정으로 이어지는 트랜잭션 관리.

4. Review Module (커뮤니티)
- [ ] 관람평 작성: 공연 관람 후 별점 및 리뷰 등록.

- [ ] 리뷰 통계: 공연별 평균 별점 계산 및 베스트 리뷰 노출.
---

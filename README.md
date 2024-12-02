## 우아한 테크코스 7기: 프리코스 과정 저장소

---

### 목차

|  Project   |                              Mission                              |   Term    |                         Repository                          |                                 Revision                                 |
|:----------:|:-----------------------------------------------------------------:|:---------:|:-----------------------------------------------------------:|:------------------------------------------------------------------------:|
| 문자열 덧셈 계산기 |    https://github.com/woowacourse-precourse/java-calculator-7     | 15~21.OCT |  https://github.com/awrion3/java-calculator-7/tree/awrion3  | https://github.com/awrion3/reflectJava_woowa-precourse-7/tree/calculator |
| 자동차 경주 게임  |     https://github.com/woowacourse-precourse/java-racingcar-7     | 22~28.OCT |  https://github.com/awrion3/java-racingcar-7/tree/awrion3   | https://github.com/awrion3/reflectJava_woowa-precourse-7/tree/racingcar  |
|   로또 게임    |       https://github.com/woowacourse-precourse/java-lotto-7       | 29~04.NOV |    https://github.com/awrion3/java-lotto-7/tree/awrion3     |   https://github.com/awrion3/reflectJava_woowa-precourse-7/tree/lotto    |
|    편의점     | https://github.com/woowacourse-precourse/java-convenience-store-7 | 05~11.NOV | https://github.com/awrion3/java-convenience-store-7-awrion3 |   https://github.com/awrion3/reflectJava_woowa-precourse-7/tree/store    |

---

## 미션4: 편의점

### 개요

* 구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템을 구현한다.

---

### 프로그래밍 요구 사항

* indent(인덴트, 들여쓰기) depth를 2까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
* `Java Enum`을 적용하여 프로그램을 구현한다.
* 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.

* `camp.nextstep.edu.missionutils`에서 제공하는 `DateTimes` 및 `Console API`를 사용하여 구현해야 한다.
    - 현재 날짜와 시간을 가져오려면 `camp.nextstep.edu.missionutils.DateTimes`의 `now()`를 활용한다.
    - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

---

### 기능 목록

- [ ] 제어
    - 프로그램 흐름
        - 파일 입력 > 환영 인사 및 재고 상태 출력
        - 주문 입력 > 증정 추가 및 정가 결제 입력 > 재고 상태 갱신
        - 멤버십 할인 입력 > 금액 계산 > 영수증 출력
        - 추가 구매 입력 > 전체 흐름 반복
    - 예외 입력 처리
        * 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생
            - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등 명확한 유형
        * "[ERROR]"로 시작하는 에러 메시지를 출력
            - 구매할 상품과 수량 형식이 올바르지 않은 경우: `[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.`
            - 존재하지 않는 상품을 입력한 경우: `[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.`
            - 구매 수량이 재고 수량을 초과한 경우: `[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.`
            - 기타 잘못된 입력의 경우: `[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.`
        * 해당 부분부터 입력 다시 받기


- [ ] 모델: 상품 및 행사 목록
    - 프로그램 시작 시 상품 및 행사 목록을 파일로부터 입력
    - 파일 형식 준수를 전제로 내용 변동 가능


- [ ] 출력
    - 환영 인사
        - `안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.`
    - 상품명, 가격, 프로모션 이름, 재고 상태 안내
        - 재고 0일 시 `재고 없음` 출력


- [ ] 입력: 구매 상품 및 수량 주문
    - `구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])`


- [ ] 모델: 주문 목록
    - 주문 상품 및 수량, 상품 아이디 저장
    - 주문 형식 및 상품명과 수량 오더 검증


- [ ] 모델: 매니저
    * 증정 추가 확인
        - 프로모션 기간 내 상품인 경우, 증정 상품을 추가로 get 가능한지 확인
        - 고객 응답 검증 후 Y이면, 주문 수량에다 합산하기
    * 정가 결제 확인
        - 프로모션 기간 내 상품의 재고 수량이 주문 수량보다 부족한지 확인
        - 정가로 결제되는 상품 수량 계산하기
        - 고객 응답 검증 후 N이면, 주문 수량에서 감산하기
    * 상품별 증정 추가 및 정가 결제 수량을 저장하기
    * 실제 주문을 반영해 재고 차감하여 갱신하기


- [ ] 모델: 고객
    - 고객 응답 형식이 Y 또는 N인지 검증
    - 응답 값 참 또는 거짓으로 반환하기


- [ ] 입력
    - 증정 상품 추가 여부
        - `현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)`
    - 정가 상품 결제 여부
        - `현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)`


- [ ] 입력: 멤버십 할인 적용 여부
    - `멤버십 할인을 받으시겠습니까? (Y/N)`


- [ ] 모델: 캐셔
    - 고객 응답 검증 후 Y일 시, 멤버십 할인 적용:
        - 프로모션 미적용 금액의 30%를 할인
        - 멤버십 할인의 최대 한도는 8,000원이다.
    - 행사 할인: 상품별 가격 * 증정 상품 수량의 총합으로 계산
    - 총 구매액: 상품별 가격 * 주문 수량을 곱하여 계산
    - 내실돈: 총 구매액 - 행사 할인 - 멤버십 할인


- [ ] 출력: 영수증
    - 구매 상품, 증정 상품, 금액 정보 내역 출력하기
    - 구매 상품 내역: 주문 상품명, 수량, 가격
    - 증정 상품 내역: 증정 추가 상품명, 수량
    - 금액 정보 내역:
        - 총구매액: 전체 주문 수량의 총합 및 총액
        - 행사할인: 증정 추가 상품들의 총액
        - 멤버십할인: 프로모션 미적용 상품들 총액의 30%
        - 내실돈: 할인들이 적용된 후의 최종 금액
    - 영수증의 구성 요소를 정렬하기


- [ ] 압력: 추가 구매 여부
    - `감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)`


- [ ] 제어: 전체 반복 확인
    - 추가 구매 여부에 대한 고객 응답 검증 후 Y이면, 전체 흐름 다시 반복
    - N이면 프로그램 종료하기

### 테스트 목록

- 모델
    - [ ] 상품 목록 (기능)
        - 파일 입력에 따른 생성 크기 확인
        - 상품의 행사 및 정가 총량 계산 확인

    - [ ] 행사 목록 (기능)
        - 파일 입력에 따른 생성 크기 확인
        - 행사 기간 판별 확인

    - [ ] 주문 목록 (예외)
        - 주문 입력 형식 검증

    - [ ] 고객 (예외)
        - 응답 입력 형식 검증

    - [ ] 매니저 (기능)
        - 증정 상품 목록 크기 확인
        - 정가 결제 상품 수량 확인

    - [ ] 캐셔 (기능)
        - 멤버십 할인 계산 확인
        - 총 구매량 계산 확인
        - 내실 돈 계산 확인

---

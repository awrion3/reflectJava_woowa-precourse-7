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

## 미션3: 문자열 덧셈 계산기

### 개요

* 간단한 로또 발매기를 구현한다.

---

### 기능 요구 사항

* 로또 번호의 숫자 범위는 1~45까지이다.
* 1개의 로또를 발행할 때 중복되지 않는 6개의 숫자를 뽑는다.
* 당첨 번호 추첨 시 중복되지 않는 숫자 6개와 보너스 번호 1개를 뽑는다.

* 당첨은 1등부터 5등까지 있다. 당첨 기준과 금액은 아래와 같다.
    - 1등: 6개 번호 일치 / 2,000,000,000원
    - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
    - 3등: 5개 번호 일치 / 1,500,000원
    - 4등: 4개 번호 일치 / 50,000원
    - 5등: 3개 번호 일치 / 5,000원

* 로또 구입 금액을 입력하면 구입 금액에 해당하는 만큼 로또를 발행해야 한다.
* 로또 1장의 가격은 1,000원이다.

* 당첨 번호와 보너스 번호를 입력받는다.
* 사용자가 구매한 로또 번호와 당첨 번호를 비교하여 당첨 내역 및 수익률을 출력하고 로또 게임을 종료한다.

* 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨다.
    - "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받도록 한다.
    - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.

### 입출력 요구 사항

* 로또 구입 금액을 입력 받는다.
    - 구입 금액은 1,000원 단위로 입력 받으며 1,000원으로 나누어 떨어지지 않는 경우 예외 처리한다.
    - > 구입금액을 입력해 주세요.
    - `14000`

* 발행한 로또 수량 및 번호를 출력한다. 로또 번호는 오름차순으로 정렬하여 보여준다.
    - `8개를 구매했습니다.`
    - `[8, 21, 23, 41, 42, 43]
       [3, 5, 11, 16, 32, 38]
       [7, 11, 16, 35, 36, 44]
       [1, 8, 11, 31, 41, 42]
       [13, 14, 16, 38, 42, 45]
       [7, 11, 30, 40, 42, 43]
       [2, 13, 22, 32, 38, 45]
       [1, 3, 5, 14, 22, 45]`

* 당첨 번호를 입력 받는다. 번호는 쉼표(,)를 기준으로 구분한다.
    - > 당첨 번호를 입력해 주세요.
    - `1,2,3,4,5,6`

* 예외 상황 시 에러 문구를 출력해야 한다. 단, 에러 문구는 "[ERROR]"로 시작해야 한다.
    - `[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.`

* 보너스 번호를 입력 받는다.
    - > 보너스 번호를 입력해 주세요.
    - `7`

* 당첨 내역을 출력한다.
    - `당첨 통계`
    - `---`
    - `3개 일치 (5,000원) - 1개
       4개 일치 (50,000원) - 0개
       5개 일치 (1,500,000원) - 0개
       5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
       6개 일치 (2,000,000,000원) - 0개`

* 수익률은 소수점 둘째 자리에서 반올림한다. (ex. 100.0%, 51.5%, 1,000,000.0%)
    - `총 수익률은 62.5%입니다.`

### 프로그래밍 요구 사항

* 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
* `Java Enum`을 적용하여 프로그램을 구현한다.

* `camp.nextstep.edu.missionutils`에서 제공하는`Randoms` 및 `Console API`를 사용하여 구현해야 한다.
    - Random 값 추출은 `camp.nextstep.edu.missionutils.Randoms`의 `pickUniqueNumbersInRange()`를 활용한다.
        - 사용 예시) 1에서 45 사이의 중복되지 않은 정수 6개 반환: `Randoms.pickUniqueNumbersInRange(1, 45, 6);`
    - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

* 제공된 `Lotto` 클래스를 사용하여 구현해야 한다.
    - `Lotto`에 `numbers` 이외의 필드(인스턴스 변수)를 추가할 수 없다.
    - `numbers` 의 접근 제어자인 `private`은 변경할 수 없다.
    - `Lotto`의 패키지를 변경할 수 있다.

---

### 기능 목록

- [x] 제어
    - 입력 > 모델 > 출력 흐름 제어
    - 예외 `IllegalArgumentException` 발생 및 "[ERROR]"로 시작하는 에러 메시지 출력
        * 해당 부분부터 입력 다시 받기

- [x] 입력
    - 로또 구입 금액을 입력
    - 당첨 번호와 보너스 번호를 입력
        * `Console API` 사용해 입력 받기

- [x] 모델
    - [x] lotto
        - numbers 외 필드 추가 금지, numbers 접근 제어자 변경 금지
        - 당첨 번호는 ,를 기준으로 구분, 중복 불가, 숫자 6개인지 검증
        - 로또 번호의 숫자 범위가 1부터 45 사이인지 검증
            * `[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.` 에러 메시지 출력

    - [x] lottoMachine
        - 로또 1장의 가격은 1,000원이며, 1,000원 단위이고, 최소 1000원 이상인지 검증
        - 1개의 로또를 발행할 때, 중복되지 않는 6개의 숫자를 `Randoms API` 사용해 생성
            * `Randoms.pickUniqueNumbersInRange(1, 45, 6);`
        - 로또 구입 금액을 입력하면 구입 금액에 해당하는 만큼 로또를 발행

    - [x] lottoPrize
        - 당첨은 1등부터 5등까지 존재, 당첨 기준과 금액을 다음과 같이 초기화
            - 1등: 6개 번호 일치 / 2,000,000,000원
            - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
            - 3등: 5개 번호 일치 / 1,500,000원
            - 4등: 4개 번호 일치 / 50,000원
            - 5등: 3개 번호 일치 / 5,000원
        - 각 당첨 등수의 횟수를 업데이트 갱신
        - 각 당첨 등수의 횟수를 참고해 총 당첨 금액을 계산

    - [x] lottoGame
        - 당첨 번호 추첨 시 중복되지 않는 숫자 6개와 보너스 번호 1개를 뽑기
            * 보너스 번호는 당첨 번호와 중복 불가, 1 ~ 45 사이 정수값인지 검증
        - 사용자가 구매한 로또 번호와 당첨 번호를 비교하여 당첨 내역 및 수익률을 계산

- [x] 출력
    - 발행한 로또 수량 및 번호를 출력
        - 로또 번호는 오름차순으로 정렬
    - 당첨 내역 및 수익률을 출력하고 로또 게임을 종료
        - 당첨 통계: 당첨 내역을 출력
        - 총 수익률: 수익률은 소수점 둘째 자리에서 반올림, 쉼표 표기
            * 출력 예시 `100.0%, 51.5%, 1,000,000.0%`

### 테스트 목록

* 모델
    - [x] lottoTest
        - 예외: 로또 당첨 번호가 잘못된 경우

    - [x] lottoMachineTest
        - 기능: 티켓 수량 계산 및 생성 크기 확인
        - 예외: 구입 금액이 잘못된 경우

    - [x] lottoPrizeTest
        - 기능: 로또 당첨 카운트 갱신 및 총 금액 계산 확인

    - [x] lottoGameTest
        - 기능: 로또 당첨 수익률 계산 확인
        - 예외: 보너스 번호가 잘못된 경우

---

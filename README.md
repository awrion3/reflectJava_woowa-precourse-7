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

## 미션2: 자동차 경주 게임

### 개요

* 초간단 자동차 경주 게임을 구현한다.

---

### 기능 요구 사항

- 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.
- 각 자동차에 이름을 부여할 수 있다. 전진하는 자동차를 출력할 때 자동차 이름을 같이 출력한다.
- 자동차 이름은 쉼표(,)를 기준으로 구분하며 이름은 5자 이하만 가능하다.
- 사용자는 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다.
- 전진하는 조건은 0에서 9 사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우이다.
- 우승자가 여러 명일 경우 쉼표(,)를 이용하여 구분한다.
- 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한 명 이상일 수 있다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.

### 입출력 요구 사항

* 입력
    - 경주할 자동차 이름(이름은 쉼표(,) 기준으로 구분)
    - 시도할 횟수
      ```
      경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
      pobi,woni,jun
      시도할 횟수는 몇 회인가요?
      2
   
      ```

* 출력
    - 차수별 실행 결과
      ```
      실행 결과
      pobi : -
      woni :
      jun : -
    
      pobi : --
      woni : -
      jun : --
      
      ```
    - 단독 우승자 안내 문구
      ```
      최종 우승자 : pobi
      ```
    - 공동 우승자 안내 문구
      ```
      최종 우승자 : pobi, jun
      ```

### 프로그래밍 요구 사항

* indent(인덴트, 들여쓰기) depth는 2까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.

* `camp.nextstep.edu.missionutils`에서 제공하는`Randoms`및`Console API`를 사용하여 구현해야 한다.
    - Random 값 추출은`camp.nextstep.edu.missionutils.Randoms`의`pickNumberInRange()`를 활용한다.
    - 사용자가 입력하는 값은`camp.nextstep.edu.missionutils.Console`의`readLine()`을 활용한다.

---

### 기능 목록

- [x] 제어
    - 입력 > 모델 > 출력 흐름 제어
    - 예외 IllegalArgumentException 발생 후 종료
    - 시도할 횟수만큼 경주 반복 수행

- [x] 입력
    - 자동차 이름
    - 시도할 횟수

- [x] 모델
    - [x] 자동차들
        - 자동차 이름 목록: 빈 문자열 X, 최소 하나 이상, 중복 확인 검증
        - 자동차들: 차수별 상태를 저장하는 자동차 리스트 생성
    - [x] 자동차
        - 자동차 이름: 구분자 쉼표, 5문자 이하, 숫자 혹은 특수문자 사용 불가 검증
        - 전진 조건: 0부터 9사이 랜덤값 중 4이상인지 평가
        - 자동차 위치: 자동차별 전진 누적 합산 계산 및 갱신
    - [x] 경주
        - 시도할 횟수: 최소 1회 이상, 정수 값인지 검증
        - 최종 우승자 목록: 자동차별 최종 위치 비교, 동점자 포함 평가

- [x] 출력
    - 차수별 결과: 자동차 이름과 함께 형식에 맞게 출력
    - 최종 우승자: 여러 명일 경우 쉼표(,)를 이용하여 구분해 출력

### 테스트 목록

- [x] 모델
    - [x] 자동차
        - 예외: 자동차 이름 검증
        - 기능: 자동차 위치 갱신 확인
    - [x] 자동차들
        - 예외: 자동차 이름 목록 검증
        - 기능: 자동차들 리스트 크기 확인
    - [x] 경주
        - 예외: 시도할 횟수 검증
        - 기능: 최종 우승자 명단 생성 및 동점자 반영 확인

---

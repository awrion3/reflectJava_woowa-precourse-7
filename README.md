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

## 미션1: 문자열 덧셈 계산기

### 개요

* 입력한 문자열에서 숫자를 추출하여 더하는 계산기를 구현한다.

---

### 기능 요구 사항

* 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
    - 예: "" => 0, "1,2" => 3, "1,2,3" => 6, "1,2:3" => 6

* 앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
    - 예를 들어 "//;\n1;2;3"과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.

* 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.

### 입출력 요구 사항

* 입력
    - 구분자와 양수로 구성된 문자열

* 출력
    - 덧셈 결과

### 프로그래밍 요구 사항

* `camp.nextstep.edu.missionutils`에서 제공하는 `Console API`를 사용하여 구현해야 한다.
    - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

---

### 기능 목록

- [x] 제어
    - 입력 > 표현식 > 계산기 > 출력 흐름 제어
    - 예외 `IllegalArgumentException` 발생 후 종료

- [x] 입력
    - 문자열 입력

- [x] 모델
    - [x] 표현식
        - [x] , 혹은 : 디폴트 구분자 형식 검증
        - [x] //와 \n 사이 커스텀 구분자 형식 검증
        - [x] 구분자 및 숫자 문자열 추출
    - [x] 계산기
        - [x] 숫자 문자열 형식 검증
        - [x] 숫자 합산 계산

- [x] 출력
    - 합산 값 출력

### 테스트 목록

- [x] 모델
    - [x] 표현식
        - [x] 예외
            - 빈 문자열: null, empty 검증
            - 디폴트 구분자: , 혹은 : 중 하나만 포함하도록 검증
            - 커스텀 구분자: //와 \n 사이 단일 문자만 허용 및 메타 문자 고려하도록 검증
        - [x] 기능
            - 문자열에서 디폴트 혹은 커스텀 구분자 추출 확인
            - 문자열에서 숫자 문자열 추출 확인

    - [x] 계산기
        - [x] 예외
            - 숫자 문자열에 음수를 포함하지 않도록 검증
            - 숫자 문자열에 실수는 허용하지 않는다고 가정하여 검증
        - [x] 기능
            - 디폴트 및 커스텀 구분자: 숫자 합산 계산 확인

---

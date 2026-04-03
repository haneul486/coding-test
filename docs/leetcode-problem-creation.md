# LeetCode 문제 생성 가이드

이 문서는 이 저장소에 새로운 LeetCode 연습 문제 파일을 추가할 때 따르는 규칙을 정리합니다.

## 목적

아직 직접 풀어야 하는 문제는 정답 구현 없이, 테스트 가능한 Java 연습 파일 형태로 추가합니다.

## 생성 규칙

- 새 연습 문제는 `leetcode/inprogress` 아래에 생성합니다.
- 패키지는 `leetcode.inprogress`를 사용합니다.
- 파일명은 `LeetCode번호_문제명.java` 형식을 사용합니다.
- 파일 상단 주석에 원본 문제 URL을 `Source: ...` 형식으로 기록합니다.
- 문제 설명은 원문을 그대로 복사하지 말고 요약해서 작성합니다.
- `main` 메서드에서 바로 실행 가능한 테스트를 넣습니다.
- 가능하면 예제 테스트만 넣지 말고 경계 케이스와 검증용 유틸도 함께 추가합니다.
- 사용자가 직접 풀 문제라면 정답 구현은 작성하지 않습니다.
- 연습용 파일은 `throw new UnsupportedOperationException(...)` 같은 형태로 풀이 스켈레톤만 남깁니다.
- LeetCode 구독이 필요한 문제라면 파일을 만들지 않고, 구독이 필요한 문제라서 생성할 수 없다고 안내합니다.

## 풀이 완료 후

- 파일을 `leetcode/solved`로 이동합니다.
- 패키지를 `leetcode.solved`로 변경합니다.
- 상단의 원본 URL 주석은 유지합니다.

## 컴파일 예시

```powershell
javac -encoding UTF-8 leetcode\inprogress\LeetCode40_CombinationSumII.java
java -classpath . leetcode.inprogress.LeetCode40_CombinationSumII
```

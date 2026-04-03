---
name: leetcode-java-problem-setup
description: 이 저장소 규칙에 맞춰 LeetCode Java 문제 파일을 생성하거나 정리하는 스킬입니다. 새 LeetCode 문제를 추가해야 하거나, 풀이 없이 테스트만 준비해야 하거나, solved와 inprogress 패키지로 파일을 나눠야 하거나, 출처 URL 주석과 파일명 규칙을 적용해야 할 때 사용합니다.
---

# LeetCode Java 문제 설정

이 저장소의 규칙에 맞춰 LeetCode Java 파일을 만들거나 정리합니다.

## 기본 구조

- 아직 풀지 않은 연습 문제는 `leetcode/inprogress`에 만들고 패키지는 `leetcode.inprogress`를 사용합니다.
- 풀이가 끝난 문제는 `leetcode/solved`에 두고 패키지는 `leetcode.solved`를 사용합니다.
- 파일명은 `LeetCode번호_문제명.java` 형식을 사용합니다.

## 새 연습 문제 파일을 만들 때

- 상단 주석에 문제 제목과 `Source: <leetcode-url>`을 넣습니다.
- 문제 설명은 원문 전체를 복사하지 말고 직접 요약해서 작성합니다.
- `main` 메서드에서 바로 실행 가능한 테스트를 넣습니다.
- 가능하면 예제 테스트만 넣지 말고 경계 케이스나 검증 유틸도 함께 추가합니다.
- 사용자가 직접 풀어야 하는 문제라면 정답 구현은 작성하지 않습니다.
- 필요하면 `UnsupportedOperationException`을 던지는 스켈레톤 메서드를 남깁니다.

## 풀이 완료 문제를 옮길 때

- 파일을 `leetcode/inprogress`에서 `leetcode/solved`로 이동합니다.
- 패키지 선언을 함께 변경합니다.
- 사용자가 따로 요청하지 않는 한 출처 URL 주석과 기존 테스트는 유지합니다.

## 참고

- 정확한 저장소 규칙이나 컴파일 예시가 필요하면 [references/repo-conventions.md](references/repo-conventions.md)를 먼저 확인합니다.

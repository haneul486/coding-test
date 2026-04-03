# 저장소 규칙

## 디렉터리 구분

- `leetcode/inprogress`: 현재 연습 중인 문제
- `leetcode/solved`: 풀이를 완료한 문제

## Java 패키지 매핑

- `leetcode.inprogress` -> `leetcode/inprogress`
- `leetcode.solved` -> `leetcode/solved`

## 문제 파일 규칙

- 파일명 형식: `LeetCode번호_문제명.java`
- 상단 주석에 `Source: https://leetcode.com/problems/.../description/` 추가
- 문제 설명은 원문 전체 복사가 아니라 요약 형태로 작성
- `main`에 실행 가능한 테스트 추가
- 연습용 문제는 사용자가 명시적으로 요청하지 않는 한 정답 구현을 작성하지 않음

## 컴파일 명령 예시

```powershell
javac -encoding UTF-8 leetcode\inprogress\SomeFile.java
java -classpath . leetcode.inprogress.SomeFile
```

```powershell
javac -encoding UTF-8 leetcode\solved\SomeFile.java
java -classpath . leetcode.solved.SomeFile
```
